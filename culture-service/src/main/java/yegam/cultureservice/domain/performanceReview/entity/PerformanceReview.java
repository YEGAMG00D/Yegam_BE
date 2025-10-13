package yegam.cultureservice.domain.performanceReview.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(
    name = "performance_reviews",
    indexes = {
        @Index(name = "idx_user_id", columnList = "user_id"),
        @Index(name = "idx_performance_id", columnList = "performance_id")
    }
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PerformanceReview {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "performance_review_id")
  private Long id;

  @Column(nullable = false)
  private Long userId;

  @Lob
  @Column(nullable = false)
  private String content;

  @Column(nullable = false)
  private Integer rating;

  private Integer likeCount = 0;

  @CreationTimestamp
  private LocalDateTime createdAt;

  @Column(nullable = false)
  private Long performanceId;

  @Column(nullable = false)
  private Boolean isDeleted = false;

  private LocalDateTime deletedAt;

  @Column(nullable = false, unique = true, length = 36)
  private String uuid = UUID.randomUUID().toString();
}
