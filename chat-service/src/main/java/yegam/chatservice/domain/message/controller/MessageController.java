package yegam.chatservice.domain.message.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import yegam.chatservice.domain.message.dto.request.MessageSendRequestDto;
import yegam.chatservice.domain.message.dto.response.MessageResponseDto;
import yegam.chatservice.domain.message.dto.response.UserMessageListResponseDto;
import yegam.chatservice.domain.message.service.MessageService;
import yegam.chatservice.global.exception.CustomException;
import yegam.chatservice.global.exception.GlobalErrorCode;
import yegam.chatservice.global.response.BaseResponse;

import java.security.Principal;
import java.util.List;

@Tag(name = "Message API", description = "채팅 메시지 조회/발행 API")
@Controller
@RequiredArgsConstructor
@RequestMapping("/api/messages")
public class MessageController {

  private final MessageService messageService;
  private final SimpMessagingTemplate messagingTemplate;

  /** WebSocket 메시지 실시간 송신 */
  @MessageMapping("/chat.sendMessage")
  public void sendMessage(MessageSendRequestDto messageDto, Principal principal) {
    if (principal == null) {
      throw new CustomException(GlobalErrorCode.UNAUTHORIZED_ACCESS);
    }

    Long userId = Long.valueOf(principal.getName());
    Long roomId = messageDto.getRoomId();

    MessageResponseDto result = messageService.sendMessage(userId, roomId, messageDto);
    BaseResponse<MessageResponseDto> payload = BaseResponse.success("메시지 전송 성공", result);
    messagingTemplate.convertAndSend("/topic/room." + roomId, payload);
  }

  /** roomId 기준 메시지 조회 */
  @Operation(summary = "채팅방 ID 기준 메시지 목록 조회 (DB 전체)", description = "roomId를 기준으로 과거 모든 메시지를 불러옵니다.")
  @GetMapping("/room/{roomId}")
  public ResponseEntity<BaseResponse<List<MessageResponseDto>>> getMessagesByRoomId(@PathVariable Long roomId) {
    List<MessageResponseDto> result = messageService.getMessagesByRoom(roomId);
    return ResponseEntity.ok(BaseResponse.success("채팅방 메시지 목록 조회 성공", result));
  }

  @Operation(summary = "특정 유저의 메시지 목록 조회 (운영자용)")
  @GetMapping("/user/{userId}")
  public ResponseEntity<BaseResponse<List<UserMessageListResponseDto>>> getMessagesByUser(@PathVariable Long userId) {
    List<UserMessageListResponseDto> result = messageService.getMessagesByUser(userId);
    return ResponseEntity.ok(BaseResponse.success("유저 메시지 조회 성공", result));
  }
}
