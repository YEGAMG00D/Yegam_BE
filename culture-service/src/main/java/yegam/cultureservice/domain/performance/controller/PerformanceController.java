package yegam.cultureservice.domain.performance.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yegam.cultureservice.domain.performance.dto.response.*;
import yegam.cultureservice.domain.performance.service.PerformanceService;

import java.util.List;

@RestController
@RequestMapping("/api/cultures")
@RequiredArgsConstructor
public class PerformanceController {

  private final PerformanceService performanceService;

  /** 공연 전체 목록 (카테고리, 인기순/최신순, 페이지네이션) */
  @GetMapping
  public ResponseEntity<List<PerformanceResponseDto>> getAllPerformances(
      @RequestParam(required = false) String category,
      @RequestParam(defaultValue = "popular") String sort,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "12") int size
  ) {
    return ResponseEntity.ok(performanceService.getAllPerformances(category, sort, page, size));
  }

  /** 공연 검색 (카테고리 + 키워드) */
  @GetMapping("/search")
  public ResponseEntity<List<PerformanceResponseDto>> searchPerformances(
      @RequestParam(required = false) String category,
      @RequestParam String keyword,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "12") int size
  ) {
    return ResponseEntity.ok(performanceService.searchPerformances(category, keyword, page, size));
  }

  /** 공연 상세 조회 (내부 id 기준) */
  @GetMapping("/{id}")
  public ResponseEntity<PerformanceDetailResponseDto> getPerformanceDetail(
      @PathVariable Long id
  ) {
    return ResponseEntity.ok(performanceService.getPerformanceDetailById(id));
  }
}
