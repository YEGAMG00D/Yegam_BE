package yegam.cultureservice.domain.performanceReview.dto.request;

import lombok.*;
import jakarta.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PerformanceReviewRequestDto {

  @NotNull(message = "회원 ID는 필수입니다.")
  private Long userId;

  @NotBlank(message = "후기 내용을 입력해주세요.")
  private String content;

  @Min(1)
  @Max(5)
  private Integer rating;
}
