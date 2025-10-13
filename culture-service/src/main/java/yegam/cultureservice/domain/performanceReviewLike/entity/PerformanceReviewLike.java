package yegam.cultureservice.domain.performanceReviewLike.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(
    name = "performance_review_likes",
    indexes = {
        @Index(name = "idx_user_id", columnList = "user_id"),
        @Index(name = "idx_performance_review_id", columnList = "performance_review_id")
    }
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PerformanceReviewLike {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "performance_review_like_id")
  private Long id;

  @Column(nullable = false)
  private Long userId;

  @CreationTimestamp
  private LocalDateTime createdAt;

  @Column(nullable = false)
  private Long performanceReviewId;
}
