package yegam.cultureservice.domain.performance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yegam.cultureservice.domain.performance.entity.PerformanceDetail;

import java.util.Optional;

@Repository
public interface PerformanceDetailRepository extends JpaRepository<PerformanceDetail, Long> {
  Optional<PerformanceDetail> findByMt20id(String mt20id);
}
