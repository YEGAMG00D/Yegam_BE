package yegam.placeservice.domain.place.entity;

import jakarta.persistence.*;
import lombok.*;
import yegam.placeservice.global.common.BaseTimeEntity;

@Entity
@Table(name = "place_detail")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlaceDetail extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "detail_id")
  private Long id;

  @Column(nullable = false)
  private String mt10id; // 공연시설 ID (KOPIS) - FK 역할

  @Column(nullable = false)
  private String mt13id; // 공연장(홀) ID

  @Column(nullable = false)
  private String hallName; // 공연장명

  @Column
  private Integer seatScale; // 좌석규모

  @Column
  private String stageType; // 무대형식(Y/N/Z/0)

  @Column
  private Boolean hasStageEquip; // 무대시설_음향

  @Column
  private Boolean hasLighting; // 무대시설_조명

  @Column
  private Boolean hasBackstage; // 무대시설_보장실

  @Column
  private Boolean hasDisabledSeat; // 장애인석 여부

  @Column
  private Integer disabledSeatCount; // 장애인석 개수

  @Column
  private String stageArea; // 무대면적

  @Column
  private String performanceHallName; // 공연장명 (예: KSPO DOME)

  @Column
  private Boolean hasOrchestraPit; // 오케스트라피트 여부

  @Column
  private String parkingInfo; // 주차 관련 정보

  @Column(nullable = false)
  private Boolean isDeleted = false;
}
