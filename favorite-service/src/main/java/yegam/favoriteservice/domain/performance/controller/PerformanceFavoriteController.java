package yegam.favoriteservice.domain.performance.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import yegam.favoriteservice.domain.performance.dto.response.PerformanceFavoriteListResponseDto;
import yegam.favoriteservice.domain.performance.dto.response.PerformanceFavoriteResponseDto;
import yegam.favoriteservice.domain.performance.service.PerformanceFavoriteService;
import yegam.favoriteservice.global.response.BaseResponse;

@RestController
@RequestMapping("/api/favorites/cultures")
@Tag(name = "Favorite", description = "공연 관심(찜) 관리 API")
@RequiredArgsConstructor
public class PerformanceFavoriteController {

  private final PerformanceFavoriteService favoriteService;

  /** 공연 찜 등록/해제 (토글) */
  @Operation(summary = "공연 찜 등록/해제", description = "사용자가 공연을 찜 또는 해제합니다.")
  @PostMapping("/{performanceId}")
  public ResponseEntity<BaseResponse<PerformanceFavoriteResponseDto>> toggleFavorite(
      @PathVariable Long performanceId,
      @AuthenticationPrincipal Long userId) {

    PerformanceFavoriteResponseDto response = favoriteService.toggleFavorite(userId, performanceId);
    return ResponseEntity.ok(BaseResponse.success("관심 공연 상태가 변경되었습니다.", response));
  }

  /** 공연 찜 여부 확인 */
  @Operation(summary = "공연 찜 여부 확인")
  @GetMapping("/{performanceId}")
  public ResponseEntity<BaseResponse<PerformanceFavoriteResponseDto>> checkFavorite(
      @PathVariable Long performanceId,
      @AuthenticationPrincipal Long userId) {

    PerformanceFavoriteResponseDto response = favoriteService.checkFavorite(userId, performanceId);
    return ResponseEntity.ok(BaseResponse.success("관심 공연 여부 조회 성공", response));
  }

  /** 로그인 유저의 관심 공연 목록 (favorite=true만) */
  @Operation(summary = "나의 찜 목록 조회 (favorite=true만)")
  @GetMapping("/me")
  public ResponseEntity<BaseResponse<PerformanceFavoriteListResponseDto>> getMyFavorites(
      @AuthenticationPrincipal Long userId) {

    PerformanceFavoriteListResponseDto response = favoriteService.getMyFavorites(userId);
    return ResponseEntity.ok(BaseResponse.success("나의 관심 공연 목록 조회 성공", response));
  }

  /** 운영자용 전체 찜 내역 조회 (true/false 모두) */
  @Operation(summary = "특정 유저의 찜 내역 전체 조회 (운영자용)")
  @GetMapping("/admin/{userId}")
  public ResponseEntity<BaseResponse<PerformanceFavoriteListResponseDto>> getUserFavoritesAll(
      @PathVariable Long userId) {

    PerformanceFavoriteListResponseDto response = favoriteService.getUserFavoritesAll(userId);
    return ResponseEntity.ok(BaseResponse.success("특정 유저의 전체 관심 공연 내역 조회 성공", response));
  }
}
