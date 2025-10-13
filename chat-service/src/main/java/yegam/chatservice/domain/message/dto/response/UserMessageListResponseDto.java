package yegam.chatservice.domain.message.dto.response;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserMessageListResponseDto {
  private Long messageId;
  private Long roomId;
  private String content;
  private LocalDateTime createdAt;
}
