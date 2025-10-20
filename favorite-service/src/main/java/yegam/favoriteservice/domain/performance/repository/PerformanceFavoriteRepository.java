package yegam.favoriteservice.domain.performance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yegam.favoriteservice.domain.performance.entity.PerformanceFavorite;

import java.util.List;
import java.util.Optional;

@Repository
public interface PerformanceFavoriteRepository extends JpaRepository<PerformanceFavorite, Long> {

  Optional<PerformanceFavorite> findByUserIdAndPerformanceId(Long userId, Long performanceId);

  boolean existsByUserIdAndPerformanceId(Long userId, Long performanceId);

  List<PerformanceFavorite> findAllByUserId(Long userId);

  // favorite=true만
  List<PerformanceFavorite> findAllByUserIdAndFavoriteTrue(Long userId);
}
