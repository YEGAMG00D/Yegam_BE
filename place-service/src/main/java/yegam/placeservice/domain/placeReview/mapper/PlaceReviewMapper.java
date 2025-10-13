package yegam.placeservice.domain.placeReview.mapper;

import org.springframework.stereotype.Component;
import yegam.placeservice.domain.placeReview.dto.response.PlaceReviewResponseDto;
import yegam.placeservice.domain.placeReview.dto.response.UserPlaceReviewResponseDto;
import yegam.placeservice.domain.placeReview.entity.PlaceReview;

@Component
public class PlaceReviewMapper {

  public PlaceReviewResponseDto toPlaceReviewResponseDto(PlaceReview review) {
    if (review == null) return null;

    return PlaceReviewResponseDto.builder()
        .reviewId(review.getPlaceReviewId())
        .userId(review.getUserId())
        .contents(review.getContents())
        .rating(review.getRating())
        .createdAt(review.getCreatedAt())
        .build();
  }

  public UserPlaceReviewResponseDto toUserPlaceReviewResponseDto(PlaceReview review) {
    if (review == null || review.getPlace() == null) return null;

    return UserPlaceReviewResponseDto.builder()
        .reviewId(review.getPlaceReviewId())
        .placeName(review.getPlace().getName())
        .contents(review.getContents())
        .rating(review.getRating())
        .createdAt(review.getCreatedAt())
        .build();
  }

}
