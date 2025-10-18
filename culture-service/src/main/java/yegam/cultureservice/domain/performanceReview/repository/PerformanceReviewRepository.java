package yegam.cultureservice.domain.performanceReview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yegam.cultureservice.domain.performanceReview.entity.PerformanceReview;

import java.util.List;
import java.util.Optional;

@Repository
public interface PerformanceReviewRepository extends JpaRepository<PerformanceReview, Long> {

  /** 공연 ID로 후기 목록 조회 (삭제되지 않은 것만) */
  List<PerformanceReview> findByPerformanceIdAndIsDeletedFalse(Long performanceId);

  /** 특정 공연의 특정 후기 찾기 */
  Optional<PerformanceReview> findByIdAndPerformanceId(Long reviewId, Long performanceId);
}
