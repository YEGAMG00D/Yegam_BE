package yegam.placeservice.domain.placeReview.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yegam.placeservice.domain.place.entity.PlaceSummary;
import yegam.placeservice.domain.place.exception.PlaceErrorCode;
import yegam.placeservice.domain.place.repository.PlaceSummaryRepository;
import yegam.placeservice.domain.placeReview.dto.request.PlaceReviewRequestDto;
import yegam.placeservice.domain.placeReview.dto.response.PlaceReviewResponseDto;
import yegam.placeservice.domain.placeReview.dto.response.UserPlaceReviewResponseDto;
import yegam.placeservice.domain.placeReview.entity.PlaceReview;
import yegam.placeservice.domain.placeReview.exception.PlaceReviewErrorCode;
import yegam.placeservice.domain.placeReview.mapper.PlaceReviewMapper;
import yegam.placeservice.domain.placeReview.repository.PlaceReviewRepository;
import yegam.placeservice.global.exception.CustomException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlaceReviewService {

  private final PlaceReviewRepository placeReviewRepository;
  private final PlaceSummaryRepository placeSummaryRepository;
  private final PlaceReviewMapper placeReviewMapper;

  /** 공연장 리뷰 목록 조회 */
  public List<PlaceReviewResponseDto> getReviewsByPlaceId(Long placeId) {
    List<PlaceReview> reviews = placeReviewRepository.findByPlace_IdAndIsDeletedFalse(placeId);
    return reviews.stream()
        .map(placeReviewMapper::toPlaceReviewResponseDto)
        .collect(Collectors.toList());
  }

  /** 공연장 리뷰 작성 */
  @Transactional
  public PlaceReviewResponseDto createReview(Long placeId, Long userId, PlaceReviewRequestDto dto) {
    if (userId == null) {
      throw new CustomException(PlaceReviewErrorCode.ACCESS_DENIED);
    }

    PlaceSummary place = placeSummaryRepository.findById(placeId)
        .orElseThrow(() -> new CustomException(PlaceErrorCode.PLACE_NOT_FOUND));

    PlaceReview review = PlaceReview.builder()
        .userId(userId)
        .place(place)
        .contents(dto.getContents())
        .rating(dto.getRating())
        .isDeleted(false)
        .build();

    placeReviewRepository.save(review);
    return placeReviewMapper.toPlaceReviewResponseDto(review);
  }

  /** 공연장 리뷰 수정 */
  @Transactional
  public PlaceReviewResponseDto updateReview(Long placeId, Long reviewId, Long userId, PlaceReviewRequestDto dto) {
    if (userId == null) {
      throw new CustomException(PlaceReviewErrorCode.ACCESS_DENIED);
    }

    PlaceReview review = placeReviewRepository.findByPlaceReviewIdAndIsDeletedFalse(reviewId)
        .orElseThrow(() -> new CustomException(PlaceReviewErrorCode.PLACE_REVIEW_NOT_FOUND));

    if (!review.getUserId().equals(userId)) {
      throw new CustomException(PlaceReviewErrorCode.ACCESS_DENIED);
    }

    if (!review.getPlace().getId().equals(placeId)) {
      throw new CustomException(PlaceErrorCode.PLACE_NOT_FOUND);
    }

    review.setContents(dto.getContents());
    review.setRating(dto.getRating());
    placeReviewRepository.save(review);

    return placeReviewMapper.toPlaceReviewResponseDto(review);
  }

  /** 공연장 리뷰 삭제 (Soft Delete) */
  @Transactional
  public void deleteReview(Long placeId, Long reviewId, Long userId) {
    if (userId == null) {
      throw new CustomException(PlaceReviewErrorCode.ACCESS_DENIED);
    }

    PlaceReview review = placeReviewRepository.findByPlaceReviewIdAndIsDeletedFalse(reviewId)
        .orElseThrow(() -> new CustomException(PlaceReviewErrorCode.PLACE_REVIEW_NOT_FOUND));

    if (!review.getUserId().equals(userId)) {
      throw new CustomException(PlaceReviewErrorCode.ACCESS_DENIED);
    }

    if (!review.getPlace().getId().equals(placeId)) {
      throw new CustomException(PlaceErrorCode.PLACE_NOT_FOUND);
    }

    review.setIsDeleted(true);
    review.setDeletedAt(LocalDateTime.now());
    placeReviewRepository.save(review);
  }

  /** 특정 사용자의 공연장 리뷰 목록 조회 */
  public List<UserPlaceReviewResponseDto> getReviewsByUserId(Long userId) {
    List<PlaceReview> reviews = placeReviewRepository.findByUserIdAndIsDeletedFalse(userId);
    return reviews.stream()
        .map(placeReviewMapper::toUserPlaceReviewResponseDto)
        .collect(Collectors.toList());
  }
}
