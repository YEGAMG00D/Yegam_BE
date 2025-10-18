package yegam.cultureservice.domain.performanceReview.dto.request;

import lombok.*;
import jakarta.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PerformanceReviewRequestDto {

  @NotBlank(message = "제목을 입력해주세요.")
  private String title;

  @NotBlank(message = "내용을 입력해주세요.")
  private String content;

  @NotNull(message = "평점을 입력해주세요.")
  @Min(1)
  @Max(5)
  private Integer rating;

}
