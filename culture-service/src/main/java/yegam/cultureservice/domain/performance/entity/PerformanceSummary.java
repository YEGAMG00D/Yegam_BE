package yegam.cultureservice.domain.performance.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import yegam.cultureservice.global.common.BaseTimeEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "performance_summary")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PerformanceSummary extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "performance_id")
  private Long id;

  @Column(name = "mt20id", nullable = false, unique = true)
  private String mt20id; // 공연 고유 ID (KOPIS)

  @Column(name = "title", nullable = false)
  private String title; // 공연명

  @Column(name = "category", nullable = false)
  private String category; // 장르명 (뮤지컬, 연극 등)

  @Column(name = "area")
  private String area; // 공연 지역

  @Column(name = "place_name")
  private String placeName; // 공연장명

  @Column(name = "start_date")
  private LocalDate startDate; // 공연 시작일

  @Column(name = "end_date")
  private LocalDate endDate; // 공연 종료일

  @Column(name = "state")
  private String state; // 공연 상태 (공연중, 예정, 종료)

  @Column(name = "openrun")
  private String openrun; // 오픈런 여부 (Y/N)

  @Lob
  @Column(name = "poster_url")
  private String posterUrl; // 포스터 이미지

  @Column(name = "is_deleted", nullable = false)
  private Boolean isDeleted = false;

}
