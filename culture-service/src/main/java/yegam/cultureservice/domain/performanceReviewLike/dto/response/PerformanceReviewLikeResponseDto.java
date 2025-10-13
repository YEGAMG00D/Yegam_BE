package yegam.cultureservice.domain.performanceReviewLike.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PerformanceReviewLikeResponseDto {
  private Long reviewId;
  private Integer likeCount;
  private Boolean liked;
}
