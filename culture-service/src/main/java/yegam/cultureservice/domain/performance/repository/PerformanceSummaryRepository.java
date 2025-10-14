package yegam.cultureservice.domain.performance.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yegam.cultureservice.domain.performance.entity.PerformanceSummary;

import java.util.Optional;

@Repository
public interface PerformanceSummaryRepository extends JpaRepository<PerformanceSummary, Long> {

  Optional<PerformanceSummary> findByMt20id(String mt20id);

  Page<PerformanceSummary> findByCategory(String category, Pageable pageable);

  Page<PerformanceSummary> findByTitleContaining(String keyword, Pageable pageable);

  Page<PerformanceSummary> findByCategoryAndTitleContaining(String category, String keyword, Pageable pageable);
}
