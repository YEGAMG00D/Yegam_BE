package yegam.favoriteservice.domain.performance.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yegam.favoriteservice.domain.performance.dto.response.PerformanceFavoriteListResponseDto;
import yegam.favoriteservice.domain.performance.dto.response.PerformanceFavoriteResponseDto;
import yegam.favoriteservice.domain.performance.entity.PerformanceFavorite;
import yegam.favoriteservice.domain.performance.exception.PerformanceFavoriteErrorCode;
import yegam.favoriteservice.domain.performance.mapper.PerformanceFavoriteMapper;
import yegam.favoriteservice.domain.performance.repository.PerformanceFavoriteRepository;
import yegam.favoriteservice.global.exception.CustomException;
import yegam.favoriteservice.global.exception.GlobalErrorCode;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PerformanceFavoriteService {

  private final PerformanceFavoriteRepository repository;
  private final PerformanceFavoriteMapper mapper;

  /** 관심 공연 등록/해제 (논리적 토글) */
  @Transactional
  public PerformanceFavoriteResponseDto toggleFavorite(Long userId, Long performanceId) {
    if (userId == null || performanceId == null) {
      throw new CustomException(GlobalErrorCode.INVALID_INPUT_VALUE);
    }

    try {
      PerformanceFavorite favorite = repository
          .findByUserIdAndPerformanceId(userId, performanceId)
          .orElseGet(() -> {
            PerformanceFavorite newFav = PerformanceFavorite.builder()
                .userId(userId)
                .performanceId(performanceId)
                .favorite(true)
                .build();
            repository.save(newFav);
            log.info("관심 공연 최초 등록: userId={}, performanceId={}", userId, performanceId);
            return newFav;
          });

      // 이미 존재하면 상태 토글
      if (favorite.getId() != null) {
        favorite.setFavorite(!favorite.getFavorite());
        repository.save(favorite);
        log.info("관심 공연 토글: userId={}, performanceId={}, favorite={}",
            userId, performanceId, favorite.getFavorite());
      }

      return mapper.toPerformanceFavoriteResponseDto(
          favorite,
          Boolean.TRUE.equals(favorite.getFavorite()),
          Boolean.TRUE.equals(favorite.getFavorite())
              ? "관심 공연으로 등록되었습니다."
              : "관심 공연이 해제되었습니다."
      );

    } catch (Exception e) {
      log.error("관심 공연 토글 처리 중 오류 발생", e);
      throw new CustomException(PerformanceFavoriteErrorCode.FAVORITE_SAVE_FAILED);
    }
  }

  /** 특정 공연 찜 여부 확인 */
  public PerformanceFavoriteResponseDto checkFavorite(Long userId, Long performanceId) {
    if (userId == null || performanceId == null) {
      throw new CustomException(GlobalErrorCode.INVALID_INPUT_VALUE);
    }

    PerformanceFavorite favorite = repository
        .findByUserIdAndPerformanceId(userId, performanceId)
        .orElse(null);

    boolean isFav = favorite != null && Boolean.TRUE.equals(favorite.getFavorite());
    return mapper.toPerformanceFavoriteResponseDto(
        favorite != null ? favorite : PerformanceFavorite.builder()
            .userId(userId)
            .performanceId(performanceId)
            .build(),
        isFav,
        isFav ? "관심 공연입니다." : "관심 공연이 아닙니다."
    );
  }

  /** 나의 관심 공연 목록 (favorite=true만) */
  public PerformanceFavoriteListResponseDto getMyFavorites(Long userId) {
    if (userId == null) throw new CustomException(GlobalErrorCode.INVALID_INPUT_VALUE);

    List<PerformanceFavorite> favorites = repository.findAllByUserIdAndFavoriteTrue(userId);
    return mapper.toPerformanceFavoriteListResponseDto(userId, favorites);
  }

  /** 운영자 전용: 특정 유저의 전체 히스토리 (true/false 모두) */
  public PerformanceFavoriteListResponseDto getUserFavoritesAll(Long userId) {
    if (userId == null) throw new CustomException(GlobalErrorCode.INVALID_INPUT_VALUE);

    List<PerformanceFavorite> favorites = repository.findAllByUserId(userId);
    return mapper.toPerformanceFavoriteListResponseDto(userId, favorites);
  }
}
