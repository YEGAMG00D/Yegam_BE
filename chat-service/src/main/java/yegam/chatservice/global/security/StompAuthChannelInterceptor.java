package yegam.chatservice.global.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.messaging.MessagingException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import yegam.chatservice.global.jwt.JwtProvider;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class StompAuthChannelInterceptor implements ChannelInterceptor {

  private final JwtProvider jwtProvider;

  @Override
  public Message<?> preSend(Message<?> message, MessageChannel channel) {
    StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
    if (accessor == null) return message;

    StompCommand command = accessor.getCommand();
    if (command == null) return message;

    //  CONNECT 단계: 토큰 있으면 인증, 없으면 익명 허용
    if (StompCommand.CONNECT.equals(command)) {
      String token = extractToken(accessor);
      if (token != null && jwtProvider.validateToken(token)) {
        Long userId = jwtProvider.extractUserId(token);
        accessor.setUser(new UsernamePasswordAuthenticationToken(String.valueOf(userId), null, null));
        log.info("STOMP CONNECT 인증 성공: userId={}", userId);
      } else {
        accessor.setUser(new UsernamePasswordAuthenticationToken("anonymous", null, null));
        log.info("STOMP CONNECT: 익명 사용자 접속 허용");
      }
    }

    //  SUBSCRIBE 단계: 모든 사용자 허용 (익명도 가능)
    if (StompCommand.SUBSCRIBE.equals(command)) {
      return message;
    }

    //  SEND 단계: 토큰 필수 (익명 사용자는 차단)
    if (StompCommand.SEND.equals(command)) {
      String token = extractToken(accessor);
      if (token == null) {
        throw new MessagingException("UNAUTHORIZED: 로그인 후 이용 가능합니다.");
      }
      if (!jwtProvider.validateToken(token)) {
        throw new MessagingException("UNAUTHORIZED: 유효하지 않은 JWT 토큰입니다.");
      }

      Long userId = jwtProvider.extractUserId(token);
      accessor.setUser(new UsernamePasswordAuthenticationToken(String.valueOf(userId), null, null));
      log.debug("STOMP SEND 인증 성공: userId={}", userId);
    }

    return message;
  }

  private String extractToken(StompHeaderAccessor accessor) {
    List<String> authHeader = accessor.getNativeHeader("Authorization");
    if (authHeader == null || authHeader.isEmpty()) return null;

    String token = authHeader.get(0);
    token = token.replace("%20", " ").trim();
    if (token.toLowerCase().startsWith("bearer ")) {
      token = token.substring(7).trim();
    }
    return token.isEmpty() ? null : token;
  }
}
