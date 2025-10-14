package yegam.cultureservice.domain.performance.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yegam.cultureservice.domain.performance.dto.response.*;
import yegam.cultureservice.domain.performance.service.PerformanceService;

import java.util.List;

@RestController
@RequestMapping("/api/cultures")
@Tag(name = "Performance", description = "공연 정보 조회 API")
@RequiredArgsConstructor
public class PerformanceController {

  private final PerformanceService performanceService;

  /** 공연 전체 목록 */
  @Operation(summary = "공연 전체 목록 조회", description = "카테고리, 정렬(인기순/최신순), 페이지네이션으로 공연 목록을 조회합니다.")
  @GetMapping
  public ResponseEntity<List<PerformanceResponseDto>> getAllPerformances(
      @RequestParam(required = false) String category,
      @RequestParam(defaultValue = "popular") String sort,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "12") int size
  ) {
    return ResponseEntity.ok(performanceService.getAllPerformances(category, sort, page, size));
  }

  /** 공연 검색 */
  @Operation(summary = "공연 검색", description = "키워드와 카테고리를 기준으로 공연을 검색합니다.")
  @GetMapping("/search")
  public ResponseEntity<List<PerformanceResponseDto>> searchPerformances(
      @RequestParam(required = false) String category,
      @RequestParam String keyword,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "12") int size
  ) {
    return ResponseEntity.ok(performanceService.searchPerformances(category, keyword, page, size));
  }

  /** 공연 상세 조회 */
  @Operation(summary = "공연 상세 조회", description = "공연 ID를 기준으로 상세 정보를 조회합니다.")
  @GetMapping("/{id}")
  public ResponseEntity<PerformanceDetailResponseDto> getPerformanceDetail(
      @PathVariable Long id
  ) {
    return ResponseEntity.ok(performanceService.getPerformanceDetailById(id));
  }
}
