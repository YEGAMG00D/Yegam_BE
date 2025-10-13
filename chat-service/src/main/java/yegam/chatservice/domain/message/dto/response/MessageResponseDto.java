package yegam.chatservice.domain.message.dto.response;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageResponseDto {
  private Long messageId;
  private Long userId;
  private String content;
  private LocalDateTime createdAt;
}
