package yegam.cultureservice.domain.performanceReview.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yegam.cultureservice.domain.performanceReview.dto.request.PerformanceReviewRequestDto;
import yegam.cultureservice.domain.performanceReview.dto.response.PerformanceReviewResponseDto;
import yegam.cultureservice.domain.performanceReview.entity.PerformanceReview;
import yegam.cultureservice.domain.performanceReview.exception.PerformanceReviewErrorCode;
import yegam.cultureservice.domain.performanceReview.mapper.PerformanceReviewMapper;
import yegam.cultureservice.domain.performanceReview.repository.PerformanceReviewRepository;
import yegam.cultureservice.global.exception.CustomException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PerformanceReviewService {

  private final PerformanceReviewRepository performanceReviewRepository;
  private final PerformanceReviewMapper performanceReviewMapper;

  /** 공연 후기 목록 조회 */
  public List<PerformanceReviewResponseDto> getReviewsByPerformanceId(Long performanceId) {
    List<PerformanceReview> reviews =
        performanceReviewRepository.findByPerformanceIdAndIsDeletedFalse(performanceId);
    return reviews.stream()
        .map(performanceReviewMapper::toPerformanceReviewResponseDto)
        .collect(Collectors.toList());
  }

  /** 공연 후기 작성 */
  @Transactional
  public PerformanceReviewResponseDto createReview(
      Long performanceId, Long userId, PerformanceReviewRequestDto requestDto) {

    if (userId == null) {
      throw new CustomException(PerformanceReviewErrorCode.UNAUTHORIZED_USER);
    }

    PerformanceReview review = performanceReviewMapper.toEntity(requestDto, userId);
    review.setPerformanceId(performanceId);
    performanceReviewRepository.save(review);

    return performanceReviewMapper.toPerformanceReviewResponseDto(review);
  }

  /** 공연 후기 수정 */
  @Transactional
  public PerformanceReviewResponseDto updateReview(
      Long performanceId, Long reviewId, Long userId, PerformanceReviewRequestDto requestDto) {

    if (userId == null) {
      throw new CustomException(PerformanceReviewErrorCode.UNAUTHORIZED_USER);
    }

    PerformanceReview review =
        performanceReviewRepository.findByIdAndPerformanceId(reviewId, performanceId)
            .orElseThrow(() ->
                new CustomException(PerformanceReviewErrorCode.PERFORMANCE_REVIEW_NOT_FOUND));

    if (!review.getUserId().equals(userId)) {
      throw new CustomException(PerformanceReviewErrorCode.UNAUTHORIZED_USER);
    }

    review.setTitle(requestDto.getTitle());
    review.setContent(requestDto.getContent());
    review.setRating(requestDto.getRating());
    performanceReviewRepository.save(review);

    return performanceReviewMapper.toPerformanceReviewResponseDto(review);
  }

  /** 공연 후기 삭제 */
  @Transactional
  public void deleteReview(Long performanceId, Long reviewId, Long userId) {
    if (userId == null) {
      throw new CustomException(PerformanceReviewErrorCode.UNAUTHORIZED_USER);
    }

    PerformanceReview review =
        performanceReviewRepository.findByIdAndPerformanceId(reviewId, performanceId)
            .orElseThrow(() ->
                new CustomException(PerformanceReviewErrorCode.PERFORMANCE_REVIEW_NOT_FOUND));

    if (!review.getUserId().equals(userId)) {
      throw new CustomException(PerformanceReviewErrorCode.UNAUTHORIZED_USER);
    }

    review.setIsDeleted(true);
    review.setDeletedAt(LocalDateTime.now());
    performanceReviewRepository.save(review);
  }
}
