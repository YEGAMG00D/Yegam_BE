package yegam.chatservice.domain.message.mapper;

import org.springframework.stereotype.Component;
import yegam.chatservice.domain.message.dto.response.MessageResponseDto;
import yegam.chatservice.domain.message.dto.response.UserMessageListResponseDto;
import yegam.chatservice.domain.message.entity.Message;

@Component
public class MessageMapper {

  // 일반 메시지 응답용 (채팅방 내 메시지 목록)
  public MessageResponseDto toMessageResponseDto(Message message) {
    if (message == null) return null;

    return MessageResponseDto.builder()
        .messageId(message.getMessageId())
        .userId(message.getUserId())
        .content(message.getContent())
        .createdAt(message.getCreatedAt())
        .build();
  }

  // 운영자 페이지용 (특정 유저 메시지 조회)
  public UserMessageListResponseDto toUserMessageListResponseDto(Message message) {
    if (message == null || message.getRoom() == null) return null;

    return UserMessageListResponseDto.builder()
        .messageId(message.getMessageId())
        .roomId(message.getRoom().getId())
        .content(message.getContent())
        .createdAt(message.getCreatedAt())
        .build();
  }
}
