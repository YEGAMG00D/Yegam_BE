package yegam.cultureservice.domain.performanceReview.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;
import java.util.UUID;
import yegam.cultureservice.global.common.BaseTimeEntity;

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
public class PerformanceReview extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "performance_review_id")
  private Long id;

  @Column(nullable = false)
  private Long userId;  // 작성자 (User-service 연동 예정)

  @Column(nullable = false)
  private Long performanceId; // PerformanceSummary 외래키

  @Column(nullable = false, length = 100)
  private String title; // 후기 제목

  @Lob
  @Column(nullable = false)
  private String content; // 후기 내용

  @Column(nullable = false)
  private Integer rating; // 평점 (1~5)

  @Column(nullable = false)
  private Integer likeCount = 0; // 좋아요 수


  private LocalDateTime deletedAt;

  @Column(nullable = false)
  private Boolean isDeleted = false;

  @Column(nullable = false, unique = true, length = 36)
  private String uuid = UUID.randomUUID().toString();


  @PrePersist
  public void prePersist() {
    if (uuid == null) uuid = UUID.randomUUID().toString();
  }

}
