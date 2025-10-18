package yegam.cultureservice.domain.performanceReviewLike.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yegam.cultureservice.domain.performanceReview.entity.PerformanceReview;
import yegam.cultureservice.domain.performanceReview.repository.PerformanceReviewRepository;
import yegam.cultureservice.domain.performanceReviewLike.dto.response.PerformanceReviewLikeResponseDto;
import yegam.cultureservice.domain.performanceReviewLike.entity.PerformanceReviewLike;
import yegam.cultureservice.domain.performanceReviewLike.mapper.PerformanceReviewLikeMapper;
import yegam.cultureservice.domain.performanceReviewLike.repository.PerformanceReviewLikeRepository;
import yegam.cultureservice.global.exception.CustomException;
import yegam.cultureservice.global.exception.GlobalErrorCode;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PerformanceReviewLikeService {

  private final PerformanceReviewRepository performanceReviewRepository;
  private final PerformanceReviewLikeRepository performanceReviewLikeRepository;
  private final PerformanceReviewLikeMapper performanceReviewLikeMapper;

  /**
   * 공연 후기 좋아요 토글 (로그인 필수)
   */
  @Transactional
  public PerformanceReviewLikeResponseDto toggleLike(Long cultureId, Long reviewId, Long userId) {
    if (userId == null) {
      throw new CustomException(GlobalErrorCode.JWT_INVALID);
    }

    // 공연 ID 일치 확인
    PerformanceReview review = performanceReviewRepository.findById(reviewId)
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 리뷰입니다."));
    if (!review.getPerformanceId().equals(cultureId)) {
      throw new IllegalArgumentException("해당 공연에 속하지 않은 후기입니다.");
    }

    // 기존 좋아요 여부 확인
    PerformanceReviewLike existing = performanceReviewLikeRepository
        .findByUserIdAndReview_Id(userId, reviewId)
        .orElse(null);

    boolean liked;

    if (existing != null) {
      // 이미 좋아요 상태 → 취소
      performanceReviewLikeRepository.delete(existing);
      review.setLikeCount(Math.max(0, review.getLikeCount() - 1));
      liked = false;
    } else {
      // 새 좋아요 추가
      PerformanceReviewLike like = PerformanceReviewLike.builder()
          .userId(userId)
          .review(review)
          .build();
      performanceReviewLikeRepository.save(like);
      review.setLikeCount(review.getLikeCount() + 1);
      liked = true;
    }

    // 변경된 좋아요 수 저장
    performanceReviewRepository.save(review);

    // Mapper로 DTO 변환
    return performanceReviewLikeMapper.toPerformanceReviewLikeResponseDto(review, liked);
  }
}
