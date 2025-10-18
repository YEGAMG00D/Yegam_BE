package yegam.placeservice.domain.placeReview.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import yegam.placeservice.domain.placeReview.dto.request.PlaceReviewRequestDto;
import yegam.placeservice.domain.placeReview.dto.response.PlaceReviewResponseDto;
import yegam.placeservice.domain.placeReview.dto.response.UserPlaceReviewResponseDto;
import yegam.placeservice.domain.placeReview.service.PlaceReviewService;
import yegam.placeservice.global.response.BaseResponse;

import java.util.List;

@RestController
@RequestMapping("/api/places")
@Tag(name = "PlaceReview", description = "공연장 리뷰 정보 API")
@RequiredArgsConstructor
public class PlaceReviewController {

  private final PlaceReviewService placeReviewService;

  /** 공연장 리뷰 목록 조회 */
  @Operation(summary = "공연장 리뷰 목록 조회", description = "특정 공연장의 리뷰 목록을 조회합니다.")
  @GetMapping("/{placeId}/reviews")
  public ResponseEntity<BaseResponse<List<PlaceReviewResponseDto>>> getPlaceReviews(
      @PathVariable Long placeId
  ) {
    List<PlaceReviewResponseDto> reviews = placeReviewService.getReviewsByPlaceId(placeId);
    return ResponseEntity.ok(BaseResponse.success("공연장 리뷰 목록 조회 성공", reviews));
  }

  /** 공연장 리뷰 작성 (로그인 필요) */
  @Operation(summary = "공연장 리뷰 작성", description = "로그인한 사용자가 공연장 리뷰를 작성합니다.")
  @PostMapping("/{placeId}/reviews")
  public ResponseEntity<BaseResponse<PlaceReviewResponseDto>> createPlaceReview(
      @PathVariable Long placeId,
      @AuthenticationPrincipal Long userId,
      @RequestBody PlaceReviewRequestDto requestDto
  ) {
    PlaceReviewResponseDto response = placeReviewService.createReview(placeId, userId, requestDto);
    return ResponseEntity.ok(BaseResponse.success("공연장 리뷰 작성 성공", response));
  }

  /** 공연장 리뷰 수정 */
  @Operation(summary = "공연장 리뷰 수정", description = "로그인한 사용자가 자신이 작성한 공연장 리뷰를 수정합니다.")
  @PutMapping("/{placeId}/reviews/{reviewId}")
  public ResponseEntity<BaseResponse<PlaceReviewResponseDto>> updatePlaceReview(
      @PathVariable Long placeId,
      @PathVariable Long reviewId,
      @AuthenticationPrincipal Long userId,
      @RequestBody PlaceReviewRequestDto requestDto
  ) {
    PlaceReviewResponseDto response =
        placeReviewService.updateReview(placeId, reviewId, userId, requestDto);
    return ResponseEntity.ok(BaseResponse.success("공연장 리뷰 수정 성공", response));
  }

  /** 공연장 리뷰 삭제 */
  @Operation(summary = "공연장 리뷰 삭제", description = "로그인한 사용자가 자신이 작성한 공연장 리뷰를 삭제합니다.")
  @DeleteMapping("/{placeId}/reviews/{reviewId}")
  public ResponseEntity<BaseResponse<Void>> deletePlaceReview(
      @PathVariable Long placeId,
      @PathVariable Long reviewId,
      @AuthenticationPrincipal Long userId
  ) {
    placeReviewService.deleteReview(placeId, reviewId, userId);
    return ResponseEntity.ok(BaseResponse.success("공연장 리뷰 삭제 성공", null));
  }

  /** 사용자별 공연장 리뷰 목록 조회 */
  @Operation(summary = "사용자 리뷰 목록 조회", description = "특정 사용자가 작성한 공연장 리뷰 목록을 조회합니다.")
  @GetMapping("/users/{userId}/reviews")
  public ResponseEntity<BaseResponse<List<UserPlaceReviewResponseDto>>> getUserPlaceReviews(
      @PathVariable Long userId
  ) {
    List<UserPlaceReviewResponseDto> reviews = placeReviewService.getReviewsByUserId(userId);
    return ResponseEntity.ok(BaseResponse.success("사용자 리뷰 목록 조회 성공", reviews));
  }
}
