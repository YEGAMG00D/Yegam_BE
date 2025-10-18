package yegam.cultureservice.domain.performanceReview.mapper;

import org.springframework.stereotype.Component;
import yegam.cultureservice.domain.performanceReview.dto.request.PerformanceReviewRequestDto;
import yegam.cultureservice.domain.performanceReview.dto.response.PerformanceReviewResponseDto;
import yegam.cultureservice.domain.performanceReview.dto.response.UserReviewListResponseDto;
import yegam.cultureservice.domain.performanceReview.entity.PerformanceReview;

@Component
public class PerformanceReviewMapper {

  /** Request DTO → Entity */
  public PerformanceReview toEntity(PerformanceReviewRequestDto dto, Long userId) {
    if (dto == null) return null;

    return PerformanceReview.builder()
        .userId(userId)
        .title(dto.getTitle())
        .content(dto.getContent())
        .rating(dto.getRating())
        .likeCount(0)
        .isDeleted(false)
        .build();
  }

  /** Entity → Response DTO (좋아요 여부 없이 단순 변환) */
  public PerformanceReviewResponseDto toPerformanceReviewResponseDto(PerformanceReview entity) {
    if (entity == null) return null;

    return PerformanceReviewResponseDto.builder()
        .reviewId(entity.getId())
        .userId(entity.getUserId())
        .performanceId(entity.getPerformanceId())
        .title(entity.getTitle())
        .content(entity.getContent())
        .rating(entity.getRating())
        .likeCount(entity.getLikeCount())
        .createdAt(entity.getCreatedAt())
        .build();
  }

  /** 마이페이지용 DTO */
  public UserReviewListResponseDto toUserReviewListResponseDto(PerformanceReview entity) {
    if (entity == null) return null;

    return UserReviewListResponseDto.builder()
        .reviewId(entity.getId())
        .performanceId(entity.getPerformanceId())
        .title(entity.getTitle())
        .rating(entity.getRating())
        .createdAt(entity.getCreatedAt())
        .build();
  }
}
