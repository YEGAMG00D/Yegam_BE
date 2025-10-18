package yegam.cultureservice.domain.performanceReviewLike.mapper;

import org.springframework.stereotype.Component;
import yegam.cultureservice.domain.performanceReviewLike.dto.response.PerformanceReviewLikeResponseDto;
import yegam.cultureservice.domain.performanceReview.entity.PerformanceReview;

@Component
public class PerformanceReviewLikeMapper {

  public PerformanceReviewLikeResponseDto toPerformanceReviewLikeResponseDto(PerformanceReview review, boolean liked) {
    if (review == null) return null;

    return PerformanceReviewLikeResponseDto.builder()
        .reviewId(review.getId())
        .totalLikes(review.getLikeCount())
        .liked(liked)
        .build();
  }
}
