package yegam.favoriteservice.domain.performance.mapper;

import org.springframework.stereotype.Component;
import yegam.favoriteservice.domain.performance.dto.response.PerformanceFavoriteResponseDto;
import yegam.favoriteservice.domain.performance.entity.Performance;

@Component
public class PerformanceMapper {

  public PerformanceFavoriteResponseDto toPerformanceFavoriteResponseDto(Performance entity, boolean isFavorite, String message) {
    if (entity == null) return null;

    return PerformanceFavoriteResponseDto.builder()
        .performanceId(entity.getPerformanceId())
        .userId(entity.getUserId())
        .isFavorite(isFavorite)
        .message(message)
        .updatedAt(entity.getUpdatedAt())
        .build();
  }
}
