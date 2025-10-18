package yegam.cultureservice.domain.performanceReviewLike.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PerformanceReviewLikeRequestDto {
  private Long reviewId; // 좋아요 누를 후기 ID
}
