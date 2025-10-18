package yegam.cultureservice.domain.performanceReviewLike.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yegam.cultureservice.domain.performanceReviewLike.entity.PerformanceReviewLike;

@Repository
public interface PerformanceReviewLikeRepository extends JpaRepository<PerformanceReviewLike, Long> {

  /** 로그인한 사용자가 특정 리뷰에 좋아요 눌렀는지 */
  boolean existsByUserIdAndReview_Id(Long userId, Long reviewId);

  /** 토글용: 해당 사용자-리뷰의 좋아요 엔티티 */
  Optional<PerformanceReviewLike> findByUserIdAndReview_Id(Long userId, Long reviewId);

  /** 리뷰 삭제 시 좋아요 일괄 삭제 */
  void deleteByReview_Id(Long reviewId);

  /** 좋아요 개수 */
  long countByReview_Id(Long reviewId);
}

