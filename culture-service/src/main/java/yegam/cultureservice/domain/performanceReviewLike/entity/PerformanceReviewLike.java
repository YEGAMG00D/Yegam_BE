package yegam.cultureservice.domain.performanceReviewLike.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;
import yegam.cultureservice.domain.performanceReview.entity.PerformanceReview;
import yegam.cultureservice.global.common.BaseTimeEntity;

@Entity
@Table(
    name = "performance_reviews_likes",
    indexes = {
        @Index(name = "idx_user_id", columnList = "user_id"),
        @Index(name = "idx_review_id", columnList = "performance_review_id")
    }
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PerformanceReviewLike extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "performance_review_like_id")
  private Long id;

  @Column(nullable = false)
  private Long userId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "performance_review_id", nullable = false)
  private PerformanceReview review;


}
