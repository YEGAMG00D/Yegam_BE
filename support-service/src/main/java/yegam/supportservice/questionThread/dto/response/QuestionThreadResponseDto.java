package yegam.supportservice.questionThread.dto.response;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionThreadResponseDto {
  private Long senderId;
  private String senderRole;
  private String content;
  private LocalDateTime createdAt;

}
