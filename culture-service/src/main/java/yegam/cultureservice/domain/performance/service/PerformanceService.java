package yegam.cultureservice.domain.performance.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yegam.cultureservice.domain.performance.dto.response.*;
import yegam.cultureservice.domain.performance.entity.*;
import yegam.cultureservice.domain.performance.exception.PerformanceErrorCode;
import yegam.cultureservice.domain.performance.mapper.PerformanceMapper;
import yegam.cultureservice.domain.performance.repository.*;
import yegam.cultureservice.global.exception.CustomException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PerformanceService {

  private final PerformanceSummaryRepository summaryRepository;
  private final PerformanceDetailRepository detailRepository;
  private final PerformanceMapper performanceMapper;

  /** 공연 전체 목록 */
  public List<PerformanceResponseDto> getAllPerformances(String category, String sort, int page, int size) {
    Pageable pageable = switch (sort.toLowerCase()) {
      case "latest" -> PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "startDate"));
      default -> PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "updatedAt"));
    };

    Page<PerformanceSummary> performances = (category == null || category.isEmpty())
        ? summaryRepository.findAll(pageable)
        : summaryRepository.findByCategory(category, pageable);

    return performances.getContent().stream()
        .map(performanceMapper::toPerformanceResponseDto)
        .collect(Collectors.toList());
  }

  /** 공연 검색 (카테고리 + 키워드) */
  public List<PerformanceResponseDto> searchPerformances(String category, String keyword, int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
    Page<PerformanceSummary> performances;

    if (category == null || category.isEmpty()) {
      performances = summaryRepository.findByTitleContaining(keyword, pageable);
    } else {
      performances = summaryRepository.findByCategoryAndTitleContaining(category, keyword, pageable);
    }

    return performances.getContent().stream()
        .map(performanceMapper::toPerformanceResponseDto)
        .collect(Collectors.toList());
  }

  /** 공연 상세 정보 (내부 id → mt20id로 상세 연결) */
  public PerformanceDetailResponseDto getPerformanceDetailById(Long id) {
    PerformanceSummary summary = summaryRepository.findById(id)
        .orElseThrow(() -> new CustomException(PerformanceErrorCode.PERFORMANCE_NOT_FOUND));

    PerformanceDetail detail = detailRepository.findByMt20id(summary.getMt20id())
        .orElseThrow(() -> new CustomException(PerformanceErrorCode.PERFORMANCE_NOT_FOUND));

    return performanceMapper.toPerformanceDetailResponseDto(summary, detail);
  }
}
