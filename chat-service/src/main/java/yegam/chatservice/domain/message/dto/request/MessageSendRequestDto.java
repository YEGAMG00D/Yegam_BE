package yegam.chatservice.domain.message.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageSendRequestDto {
  private Long roomId;
  private Long userId;
  private String content;
}
