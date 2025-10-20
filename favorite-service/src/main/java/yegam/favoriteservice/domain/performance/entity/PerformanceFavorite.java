package yegam.favoriteservice.domain.performance.entity;

import jakarta.persistence.*;
import lombok.*;
import yegam.favoriteservice.global.common.BaseTimeEntity;

@Entity
@Table(name = "favorite_performances")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PerformanceFavorite extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "favorite_performance_id")
  private Long id;

  @Column(name = "user_id", nullable = false)
  private Long userId;

  @Column(name = "performance_id", nullable = false)
  private Long performanceId;

  /** 논리적 토글용 상태 컬럼 */
  @Column(nullable = false)
  private Boolean favorite = true;
}
