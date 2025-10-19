package yegam.chatservice.global.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import yegam.chatservice.global.security.StompHandshakeInterceptor;
import yegam.chatservice.global.security.StompAuthChannelInterceptor;

@Configuration
@EnableWebSocketMessageBroker
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

  private final StompHandshakeInterceptor stompHandshakeInterceptor;
  private final StompAuthChannelInterceptor stompAuthChannelInterceptor;

  @Override
  public void configureMessageBroker(MessageBrokerRegistry registry) {
    registry.enableSimpleBroker("/topic");
    registry.setApplicationDestinationPrefixes("/app");
  }

  @Override
  public void registerStompEndpoints(StompEndpointRegistry registry) {
    registry.addEndpoint("/ws-chat")
        .addInterceptors(stompHandshakeInterceptor)
        .setAllowedOriginPatterns("*")
        .withSockJS(); // SockJS fallback 허용
  }

  @Override
  public void configureClientInboundChannel(ChannelRegistration registration) {
    // 메시지(INBOUND) 단계에서 Authorization 검사
    registration.interceptors(stompAuthChannelInterceptor);
  }
}
