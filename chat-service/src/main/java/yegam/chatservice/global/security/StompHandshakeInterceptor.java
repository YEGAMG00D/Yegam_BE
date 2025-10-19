package yegam.chatservice.global.security;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.web.socket.server.HandshakeInterceptor;
import yegam.chatservice.global.jwt.JwtProvider;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Component
@RequiredArgsConstructor
public class StompHandshakeInterceptor implements HandshakeInterceptor {

  private final JwtProvider jwtProvider;

  /** 현재 접속자 수 */
  private static final AtomicInteger connectedUsers = new AtomicInteger(0);

  /** 최대 접속자 수 */
  private static final AtomicInteger maxConnectedUsers = new AtomicInteger(0);

  /** 활성 세션 목록 (중복 방지용) */
  private static final Map<String, Boolean> activeSessions = new ConcurrentHashMap<>();

  @Override
  public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
      WebSocketHandler wsHandler, Map<String, Object> attributes) {
    if (request instanceof ServletServerHttpRequest servletRequest) {
      HttpServletRequest httpRequest = servletRequest.getServletRequest();

      String token = httpRequest.getParameter("token");
      if (token != null) {
        token = token.replace("Bearer", "").replace("%20", "").trim();
      }

      log.debug("WebSocket Handshake 요청 (token 존재 여부: {})", token != null);
    }
    return true;
  }

  @Override
  public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response,
      WebSocketHandler wsHandler, Exception exception) {
    if (exception != null) {
      log.warn("Handshake 중 예외 발생: {}", exception.getMessage());
    }
  }

  /** 클라이언트 연결 시 */
  @EventListener
  public void handleSessionConnect(SessionConnectEvent event) {
    String sessionId = event.getMessage().getHeaders().get("simpSessionId").toString();

    if (activeSessions.putIfAbsent(sessionId, true) == null) {
      int current = connectedUsers.incrementAndGet();
      maxConnectedUsers.accumulateAndGet(current, Math::max); // 최대 접속자 갱신

      log.info("🔵 WebSocket 연결됨: {} (현재 접속자 수: {}, 최대: {})",
          sessionId, current, maxConnectedUsers.get());
    }
  }

  /** 클라이언트 연결 종료 시 */
  @EventListener
  public void handleSessionDisconnect(SessionDisconnectEvent event) {
    String sessionId = event.getSessionId();

    if (activeSessions.remove(sessionId) != null) {
      int current = connectedUsers.decrementAndGet();
      log.info("🔴 WebSocket 연결 종료: {} (현재 접속자 수: {})", sessionId, current);
    }
  }

  /** 현재 접속자 수 조회용 메서드 (관리자용) */
  public static int getCurrentConnections() {
    return connectedUsers.get();
  }

  /** 최대 동시 접속자 수 조회 */
  public static int getMaxConnections() {
    return maxConnectedUsers.get();
  }
}
