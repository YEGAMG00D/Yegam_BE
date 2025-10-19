package yegam.chatservice.global.security;

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
import yegam.chatservice.global.exception.CustomException;
import yegam.chatservice.global.exception.GlobalErrorCode;
import yegam.chatservice.global.jwt.JwtProvider;
import yegam.chatservice.global.response.BaseResponse;
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

    // Swagger 문서 & 공개 API는 예외로 허용
    if (isPublicPath(uri, method)) {
      filterChain.doFilter(request, response);
      return;
    }

    // JWT 인증 필수 구간
    try {
      String token = resolveToken(request);

      if (token == null || token.isEmpty()) {
        throw new CustomException(GlobalErrorCode.UNAUTHORIZED_ACCESS);
      }

      if (!jwtProvider.validateToken(token)) {
        throw new CustomException(GlobalErrorCode.JWT_INVALID);
      }

      Long userId = jwtProvider.extractUserId(token);

      // SecurityContext에 인증 객체 저장
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

  /**
   * 로그인 없이 접근 가능한 공개 API 목록 (운영 환경에서도 동일)
   * - Swagger 문서
   * - 채팅방 / 메시지 조회 (보기만 가능)
   * - 공연/공연장 조회
   */
  private boolean isPublicPath(String uri, String method) {
    // Swagger
    if (uri.startsWith("/swagger")
        || uri.startsWith("/swagger-ui")
        || uri.startsWith("/v3/api-docs")) {
      return true;
    }

    // 채팅 보기 전용 (GET만)
    if ("GET".equalsIgnoreCase(method)) {
      if (uri.startsWith("/api/rooms")) return true;       // 전체/단건 방 조회
      if (uri.startsWith("/api/messages")) return true;    // 메시지 조회
    }

    // 공연·공연장 조회 (읽기 전용)
    if ("GET".equalsIgnoreCase(method)) {
      if (uri.startsWith("/api/cultures") || uri.startsWith("/api/places")) {
        return true;
      }
    }

    // WebSocket Handshake (SockJS 경로 전부 허용)
    if (uri.startsWith("/ws-chat")) {
      return true;
    }

    return false;
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

  /** 예외 응답 통합 JSON 출력 */
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
