package yegam.favoriteservice.global.security;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import yegam.favoriteservice.global.exception.CustomException;
import yegam.favoriteservice.global.exception.GlobalErrorCode;
import yegam.favoriteservice.global.jwt.JwtProvider;
import yegam.favoriteservice.global.response.BaseResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private static final String AUTHORIZATION_HEADER = "Authorization";
  private static final String BEARER_PREFIX = "Bearer ";

  private final JwtProvider jwtProvider;
  private final ObjectMapper objectMapper = new ObjectMapper();

  @Override
  protected void doFilterInternal(
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain filterChain)
      throws ServletException, IOException {

    String uri = request.getRequestURI();
    String method = request.getMethod();

    // Swagger, 공개 API는 예외 허용
    if (isPublicPath(uri, method)) {
      filterChain.doFilter(request, response);
      return;
    }

    try {
      String token = resolveToken(request);

      if (token == null || token.isEmpty()) {
        throw new CustomException(GlobalErrorCode.UNAUTHORIZED_ACCESS);
      }

      jwtProvider.validateToken(token);
      Long userId = jwtProvider.extractUserId(token);

      // 여기서 UserDetailsService 없이 바로 userId 저장
      UsernamePasswordAuthenticationToken authentication =
          new UsernamePasswordAuthenticationToken(userId, null, null);
      SecurityContextHolder.getContext().setAuthentication(authentication);

      log.debug("[JWT 인증 성공] userId={}", userId);
      filterChain.doFilter(request, response);

    } catch (CustomException e) {
      log.warn("[CustomException] {}", e.getMessage());
      writeJsonError(response, e.getErrorCode().getStatus().value(), e.getMessage());
    } catch (JwtException | IllegalArgumentException e) {
      log.warn("[JWT 검증 실패] {}", e.getMessage());
      writeJsonError(response, HttpServletResponse.SC_UNAUTHORIZED, "유효하지 않은 JWT 토큰입니다.");
    }
  }

  /** swagger, 공연/공연장 GET은 공개 */
  private boolean isPublicPath(String uri, String method) {
    if (uri.startsWith("/swagger")
        || uri.startsWith("/swagger-ui")
        || uri.startsWith("/v3/api-docs")) {
      return true;
    }

    // GET 요청 허용
    if ("GET".equalsIgnoreCase(method)) {
      if (uri.startsWith("/api/cultures") || uri.startsWith("/api/places")) {
        return true;
      }
    }

    return false;
  }

  /** Authorization 헤더에서 토큰 추출 */
  private String resolveToken(HttpServletRequest request) {
    String header = request.getHeader(AUTHORIZATION_HEADER);
    if (header == null) return null;
    if (header.startsWith(BEARER_PREFIX)) {
      return header.substring(BEARER_PREFIX.length()).trim();
    }
    return null;
  }

  /** 공통 에러 JSON 응답 */
  private void writeJsonError(HttpServletResponse response, int status, String message)
      throws IOException {
    response.setStatus(status);
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    response.setCharacterEncoding("UTF-8");

    BaseResponse<Object> errorResponse = BaseResponse.error(status, message);
    String json = objectMapper.writeValueAsString(errorResponse);
    response.getWriter().write(json);
  }
}
