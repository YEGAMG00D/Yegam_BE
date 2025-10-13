package yegam.supportservice.question.dto.response;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionResponseDto {
  private Long questionId;
  private String title;
  private String content;
  private String status;
  private LocalDateTime createdAt;

}
