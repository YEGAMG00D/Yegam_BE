package yegam.cultureservice.global.security;

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
import yegam.cultureservice.global.exception.CustomException;
import yegam.cultureservice.global.exception.GlobalErrorCode;
import yegam.cultureservice.global.response.BaseResponse;
import yegam.cultureservice.global.jwt.JwtProvider;
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

    // 공개 API (비로그인 허용)
    if (isPublicPath(uri, request.getMethod())) {
      filterChain.doFilter(request, response);
      return;
    }

    try {
      String token = resolveToken(request);

      if (token == null || token.isEmpty()) {
        throw new CustomException(GlobalErrorCode.UNAUTHORIZED_ACCESS);
      }

      if (!jwtProvider.validateToken(token)) {
        throw new CustomException(GlobalErrorCode.JWT_INVALID);
      }

      Long userId = jwtProvider.extractUserId(token);
      UsernamePasswordAuthenticationToken authentication =
          new UsernamePasswordAuthenticationToken(userId, null, null);
      SecurityContextHolder.getContext().setAuthentication(authentication);
      log.debug("JWT 인증 성공: userId={}", userId);

      filterChain.doFilter(request, response);

    } catch (CustomException e) {
      log.warn("JWT Custom 예외 발생: {}", e.getMessage());
      writeJsonError(response, e.getErrorCode().getStatus().value(), e.getMessage());

    } catch (JwtException | IllegalArgumentException e) {
      log.warn("JWT 검증 실패: {}", e.getMessage());
      writeJsonError(response, HttpServletResponse.SC_UNAUTHORIZED, "유효하지 않은 JWT 토큰입니다.");
    }
  }

  /** 공개 경로 지정 */
  private boolean isPublicPath(String uri, String method) {
    return uri.startsWith("/swagger")
        || uri.startsWith("/v3/api-docs")
        || uri.startsWith("/swagger-ui")
        || (uri.startsWith("/api/cultures") && method.equals("GET")); // 조회는 허용
  }

  /** 헤더에서 토큰 추출 */
  private String resolveToken(HttpServletRequest request) {
    String header = request.getHeader(AUTHORIZATION_HEADER);
    if (header == null) return null;
    if (header.startsWith(BEARER_PREFIX)) {
      header = header.substring(BEARER_PREFIX.length()).trim();
    }
    return header.isEmpty() ? null : header;
  }

  /** BaseResponse 형식으로 JSON 응답 */
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
