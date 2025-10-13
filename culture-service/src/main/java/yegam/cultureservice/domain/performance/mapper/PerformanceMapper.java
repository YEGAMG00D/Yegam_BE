package yegam.cultureservice.domain.performance.mapper;

import org.springframework.stereotype.Component;
import yegam.cultureservice.domain.performance.entity.Performance;
import yegam.cultureservice.domain.performance.dto.response.PerformanceResponseDto;
import yegam.cultureservice.domain.performance.dto.response.PerformanceDetailResponseDto;

@Component
public class PerformanceMapper {

  public PerformanceResponseDto toListDto(Performance entity) {
    return PerformanceResponseDto.builder()
        .id(entity.getId())
        .title(entity.getTitle())
        .category(entity.getCategory())
        .local(entity.getLocal())
        .startDate(entity.getStartDate())
        .endDate(entity.getEndDate())
        .posterUrl(entity.getPosterUrl())
        .avgRating(entity.getAvgRating())
        .reviewCount(entity.getReviewCount())
        .build();
  }

  public PerformanceDetailResponseDto toDetailDto(Performance entity) {
    return PerformanceDetailResponseDto.builder()
        .id(entity.getId())
        .title(entity.getTitle())
        .category(entity.getCategory())
        .local(entity.getLocal())
        .startDate(entity.getStartDate())
        .endDate(entity.getEndDate())
        .runtime(entity.getRuntime())
        .ageLimit(entity.getAgeLimit())
        .cast(entity.getCast())
        .crew(entity.getCrew())
        .placeId(entity.getPlaceId())
        .avgRating(entity.getAvgRating())
        .reviewCount(entity.getReviewCount())
        .aiSummary(entity.getAiSummary())
        .build();
  }
}
