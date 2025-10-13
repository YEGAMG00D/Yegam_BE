package yegam.cultureservice.domain.performanceReviewLike.dto.request;

import lombok.*;
import jakarta.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PerformanceReviewLikeRequestDto {
  @NotNull(message = "회원 ID는 필수입니다.")
  private Long userId;
}
