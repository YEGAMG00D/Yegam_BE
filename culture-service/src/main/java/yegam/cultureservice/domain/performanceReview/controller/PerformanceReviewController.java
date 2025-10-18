package yegam.cultureservice.domain.performanceReview.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import yegam.cultureservice.domain.performanceReview.dto.request.PerformanceReviewRequestDto;
import yegam.cultureservice.domain.performanceReview.dto.response.PerformanceReviewResponseDto;
import yegam.cultureservice.domain.performanceReview.service.PerformanceReviewService;
import yegam.cultureservice.global.response.BaseResponse;

import java.util.List;

@RestController
@RequestMapping("/api/cultures")
@Tag(name = "PerformanceReview", description = "공연 후기 API")
@RequiredArgsConstructor
public class PerformanceReviewController {

  private final PerformanceReviewService performanceReviewService;

  /** 공연 후기 목록 조회 (비로그인 가능) */
  @Operation(summary = "공연 후기 목록 조회", description = "특정 공연의 후기 목록을 조회합니다.")
  @GetMapping("/{cultureId}/reviews")
  public ResponseEntity<BaseResponse<List<PerformanceReviewResponseDto>>> getReviews(
      @PathVariable Long cultureId
  ) {
    List<PerformanceReviewResponseDto> reviews =
        performanceReviewService.getReviewsByPerformanceId(cultureId);
    return ResponseEntity.ok(BaseResponse.success("공연 후기 목록 조회 성공", reviews));
  }

  /** 공연 후기 작성 (로그인 필요) */
  @Operation(summary = "공연 후기 작성", description = "로그인한 사용자가 공연 후기를 작성합니다.")
  @PostMapping("/{cultureId}/reviews")
  public ResponseEntity<BaseResponse<PerformanceReviewResponseDto>> createReview(
      @PathVariable("cultureId") Long performanceId,
      @AuthenticationPrincipal Long userId,
      @RequestBody PerformanceReviewRequestDto requestDto
  ) {
    PerformanceReviewResponseDto response =
        performanceReviewService.createReview(performanceId, userId, requestDto);
    return ResponseEntity.ok(BaseResponse.success("공연 후기 작성 성공", response));
  }

  /** 공연 후기 수정 */
  @Operation(summary = "공연 후기 수정", description = "작성한 공연 후기를 수정합니다.")
  @PutMapping("/{cultureId}/reviews/{reviewId}")
  public ResponseEntity<BaseResponse<PerformanceReviewResponseDto>> updateReview(
      @PathVariable("cultureId") Long performanceId,
      @PathVariable Long reviewId,
      @AuthenticationPrincipal Long userId,
      @RequestBody PerformanceReviewRequestDto requestDto
  ) {
    PerformanceReviewResponseDto response =
        performanceReviewService.updateReview(performanceId, reviewId, userId, requestDto);
    return ResponseEntity.ok(BaseResponse.success("공연 후기 수정 성공", response));
  }

  /** 공연 후기 삭제 */
  @Operation(summary = "공연 후기 삭제", description = "작성한 공연 후기를 삭제합니다.")
  @DeleteMapping("/{cultureId}/reviews/{reviewId}")
  public ResponseEntity<BaseResponse<Void>> deleteReview(
      @PathVariable("cultureId") Long performanceId,
      @PathVariable Long reviewId,
      @AuthenticationPrincipal Long userId
  ) {
    performanceReviewService.deleteReview(performanceId, reviewId, userId);
    return ResponseEntity.ok(BaseResponse.success("공연 후기 삭제 성공", null));
  }
}
