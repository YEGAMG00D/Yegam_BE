package yegam.chatservice.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

  @Override
  public void registerStompEndpoints(StompEndpointRegistry registry) {
    registry.addEndpoint("/ws/chat")  // React에서 연결할 엔드포인트
        .setAllowedOriginPatterns("*") // CORS 허용
        .withSockJS(); // SockJS fallback 허용
  }

  @Override
  public void configureMessageBroker(MessageBrokerRegistry registry) {
    // 클라이언트가 구독할 prefix
    registry.enableSimpleBroker("/topic"); // ex) /topic/chat/1
    // 클라이언트가 보낼 prefix
    registry.setApplicationDestinationPrefixes("/app");
  }
}
