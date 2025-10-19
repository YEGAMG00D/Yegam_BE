package yegam.chatservice.domain.message.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageSendRequestDto {
  private Long roomId;    // 채팅방 ID
  private String content; // 메시지 내용
}
