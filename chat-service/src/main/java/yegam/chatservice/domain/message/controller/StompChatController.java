package yegam.chatservice.domain.message.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import yegam.chatservice.domain.message.dto.request.MessageSendRequestDto;
import yegam.chatservice.domain.message.dto.response.MessageResponseDto;
import yegam.chatservice.domain.message.entity.Message;
import yegam.chatservice.domain.message.mapper.MessageMapper;
import yegam.chatservice.domain.message.repository.MessageRepository;
import yegam.chatservice.domain.room.repository.RoomRepository;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
public class StompChatController {

  private final SimpMessagingTemplate messagingTemplate;
  private final MessageRepository messageRepository;
  private final RoomRepository roomRepository;
  private final MessageMapper messageMapper;

  @MessageMapping("/chat/{roomId}")
  public void sendMessage(@DestinationVariable Long roomId, MessageSendRequestDto dto) {
    // DB 저장
    Message message = Message.builder()
        .userId(dto.getUserId())
        .content(dto.getContent())
        .room(roomRepository.findById(roomId).orElseThrow())
        .createdAt(LocalDateTime.now())
        .build();

    Message saved = messageRepository.save(message);

    // 구독자에게 전송
    MessageResponseDto response = messageMapper.toMessageResponseDto(saved);
    messagingTemplate.convertAndSend("/topic/chat/" + roomId, response);
  }
}
