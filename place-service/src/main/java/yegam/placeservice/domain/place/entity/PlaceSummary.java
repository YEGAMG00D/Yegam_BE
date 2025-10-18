package yegam.placeservice.domain.place.entity;

import jakarta.persistence.*;
import lombok.*;
import yegam.placeservice.global.common.BaseTimeEntity;

@Entity
@Table(name = "place_summary")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlaceSummary extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "place_id")
  private Long id;

  @Column(nullable = false, unique = true)
  private String mt10id; // 공연시설 ID (KOPIS)

  @Column(nullable = false)
  private String name; // 공연시설명

  @Column
  private String type; // 시설특성 (문예회관, 기타 등)

  @Column
  private String areaSido; // 지역(시도)

  @Column
  private String areaGungu; // 지역(구군)

  @Column
  private Integer openYear; // 개관연도

  @Column
  private Integer seatCount; // 총 객석수

  @Column
  private String tel; // 전화번호

  @Column
  private String homepage; // 홈페이지 URL

  @Column
  private String address; // 주소

  @Column
  private Double latitude; // 위도

  @Column
  private Double longitude; // 경도

  @Column
  private Boolean hasRestaurant; // 레스토랑 여부

  @Column
  private Boolean hasCafe; // 카페 여부

  @Column
  private Boolean hasStore; // 편의점 여부

  @Column
  private Boolean hasParking; // 주차장 여부

  @Column(nullable = false)
  private Boolean isDeleted = false;
}
