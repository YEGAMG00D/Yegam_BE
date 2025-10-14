package yegam.cultureservice.domain.performance.entity;

import jakarta.persistence.*;
import lombok.*;
import yegam.cultureservice.global.common.BaseTimeEntity;

import java.time.LocalDateTime;

@Entity
@Table(name = "performance_detail")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PerformanceDetail extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "detail_id")
  private Long id;

  @Column(name = "mt20id", nullable = false)
  private String mt20id; // 공연 ID (KOPIS)

  @Lob
  @Column(name = "cast")
  private String cast; // 출연진

  @Lob
  @Column(name = "crew")
  private String crew; // 제작진

  @Column(name = "runtime")
  private String runtime; // 공연 시간 (예: 120분)

  @Column(name = "age_limit")
  private String ageLimit; // 관람 연령

  @Column(name = "ticket_price")
  private String ticketPrice; // 티켓가 정보

  @Column(name = "show_time")
  private String showTime; // 요일별 공연시간

  @Column(name = "producer")
  private String producer; // 제작사

  @Column(name = "agency")
  private String agency; // 주최/주관

  @Column(name = "original")
  private String original; // 원작자

  @Column(name = "genre")
  private String genre; // 장르명 (뮤지컬, 연극 등)

  @Column(name = "poster_sub_url1")
  private String posterSubUrl1; // 추가 이미지1

  @Column(name = "poster_sub_url2")
  private String posterSubUrl2; // 추가 이미지2

  @Column(name = "poster_sub_url3")
  private String posterSubUrl3; // 추가 이미지3

  @Column(name = "relate_url1")
  private String relateUrl1; // 예매 링크1

  @Column(name = "relate_url2")
  private String relateUrl2; // 예매 링크2

  @Column(name = "relate_url3")
  private String relateUrl3; // 예매 링크3

  @Column(name = "festival")
  private String festival; // 축제 여부 (Y/N)

  @Column(name = "created_at", updatable = false)
  private LocalDateTime createdAt;

  @Column(name = "updated_at")
  private LocalDateTime updatedAt;
}
