package yegam.chatservice.domain.message.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yegam.chatservice.domain.message.dto.request.MessageSendRequestDto;
import yegam.chatservice.domain.message.dto.response.MessageResponseDto;
import yegam.chatservice.domain.message.dto.response.UserMessageListResponseDto;
import yegam.chatservice.domain.message.entity.Message;
import yegam.chatservice.domain.message.exception.MessageErrorCode;
import yegam.chatservice.domain.message.mapper.MessageMapper;
import yegam.chatservice.domain.message.repository.MessageRepository;
import yegam.chatservice.domain.room.entity.Room;
import yegam.chatservice.domain.room.repository.RoomRepository;
import yegam.chatservice.global.exception.CustomException;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class MessageService {

  private final MessageRepository messageRepository;
  private final RoomRepository roomRepository;
  private final MessageMapper messageMapper;

  /** 메시지 전송 (roomId 기준) */
  public MessageResponseDto sendMessage(Long userId, Long roomId, MessageSendRequestDto dto) {
    Room room = roomRepository.findById(roomId)
        .orElseThrow(() -> new CustomException(MessageErrorCode.ROOM_NOT_FOUND));

    if (dto.getContent() == null || dto.getContent().trim().isEmpty()) {
      throw new CustomException(MessageErrorCode.MESSAGE_NOT_FOUND);
    }

    Message message = Message.builder()
        .userId(userId)
        .room(room)
        .content(dto.getContent())
        .isDeleted(false)
        .build();

    Message saved = messageRepository.save(message);
    return messageMapper.toMessageResponseDto(saved);
  }

  /** 특정 채팅방(roomId) 기준 메시지 전체 조회 (최신순 정렬) */
  @Transactional(readOnly = true)
  public List<MessageResponseDto> getMessagesByRoom(Long roomId) {
    Room room = roomRepository.findById(roomId)
        .orElseThrow(() -> new CustomException(MessageErrorCode.ROOM_NOT_FOUND));

    return messageRepository.findAllByRoom(room).stream()
        .sorted(Comparator.comparing(Message::getCreatedAt)) // 오래된 순 → 최신 순으로 자동 정렬됨
        .map(messageMapper::toMessageResponseDto)
        .collect(Collectors.toList());
  }

  /** 특정 유저의 모든 메시지 (운영자용) */
  @Transactional(readOnly = true)
  public List<UserMessageListResponseDto> getMessagesByUser(Long userId) {
    List<Message> messages = messageRepository.findAllByUserId(userId);
    if (messages.isEmpty()) {
      throw new CustomException(MessageErrorCode.MESSAGE_NOT_FOUND);
    }

    return messages.stream()
        .sorted(Comparator.comparing(Message::getCreatedAt).reversed()) // 최신순
        .map(messageMapper::toUserMessageListResponseDto)
        .collect(Collectors.toList());
  }
}
