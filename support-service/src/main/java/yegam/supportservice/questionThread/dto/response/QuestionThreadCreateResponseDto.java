package yegam.supportservice.questionThread.dto.response;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionThreadCreateResponseDto {
  private String message;
  private LocalDateTime createdAt;
}
