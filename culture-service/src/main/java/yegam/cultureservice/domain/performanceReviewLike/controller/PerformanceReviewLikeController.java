package yegam.cultureservice.domain.performanceReviewLike.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import yegam.cultureservice.domain.performanceReviewLike.dto.response.PerformanceReviewLikeResponseDto;
import yegam.cultureservice.domain.performanceReviewLike.service.PerformanceReviewLikeService;

import java.util.Map;
import yegam.cultureservice.global.exception.CustomException;
import yegam.cultureservice.global.exception.GlobalErrorCode;
import yegam.cultureservice.global.response.BaseResponse;

@RestController
@RequestMapping("/api/cultures")
@Tag(name = "PerformanceReviewLike", description = "공연 후기 좋아요 API")
@RequiredArgsConstructor
public class PerformanceReviewLikeController {

  private final PerformanceReviewLikeService performanceReviewLikeService;

  /** 공연 후기 좋아요 토글 (로그인 필요) */
  @Operation(summary = "공연 후기 좋아요 토글", description = "공연 후기 좋아요를 누르거나 취소합니다.")
  @PostMapping("/{cultureId}/reviews/{reviewId}/likes")
  public ResponseEntity<BaseResponse<PerformanceReviewLikeResponseDto>> toggleLike(
      @PathVariable Long cultureId,
      @PathVariable Long reviewId,
      @AuthenticationPrincipal Long userId
  ) {
    if (userId == null) {
      throw new CustomException(GlobalErrorCode.JWT_INVALID);
    }

    PerformanceReviewLikeResponseDto responseDto =
        performanceReviewLikeService.toggleLike(cultureId, reviewId, userId);

    return ResponseEntity.ok(BaseResponse.success("공연 후기 좋아요 성공", responseDto));
  }


}
