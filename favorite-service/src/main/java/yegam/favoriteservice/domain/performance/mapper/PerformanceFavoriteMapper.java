package yegam.favoriteservice.domain.performance.mapper;

import org.springframework.stereotype.Component;
import yegam.favoriteservice.domain.performance.dto.response.PerformanceFavoriteListResponseDto;
import yegam.favoriteservice.domain.performance.dto.response.PerformanceFavoriteResponseDto;
import yegam.favoriteservice.domain.performance.entity.PerformanceFavorite;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PerformanceFavoriteMapper {

  /** 단건 응답 매핑 */
  public PerformanceFavoriteResponseDto toPerformanceFavoriteResponseDto(
      PerformanceFavorite entity, boolean isFavorite, String message) {
    if (entity == null) return null;

    return PerformanceFavoriteResponseDto.builder()
        .performanceId(entity.getPerformanceId())
        .userId(entity.getUserId())
        .isFavorite(isFavorite)
        .message(message)
        .updatedAt(entity.getUpdatedAt())
        .build();
  }

  /** 목록 응답 매핑: PerformanceFavorite -> PerformanceFavoriteResponseDto 리스트로 변환 후 래핑 */
  public PerformanceFavoriteListResponseDto toPerformanceFavoriteListResponseDto(
      Long userId, List<PerformanceFavorite> favorites) {

    List<PerformanceFavoriteResponseDto> favoriteDtos = favorites.stream()
        .map(fav -> PerformanceFavoriteResponseDto.builder()
            .performanceId(fav.getPerformanceId())
            .userId(fav.getUserId())
            .isFavorite(Boolean.TRUE.equals(fav.getFavorite()))
            .message("공연 제목 불러오기 필요") // TODO: culture-service 연동 시 실제 제목 주입
            .updatedAt(fav.getUpdatedAt())
            .build())
        .collect(Collectors.toList());

    return PerformanceFavoriteListResponseDto.builder()
        .userId(userId)
        .favorites(favoriteDtos)
        .build();
  }
}
