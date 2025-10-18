package yegam.placeservice.domain.place.dto.response;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaceSummaryResponseDto {
  private Long id;               // 내부 PK
  private String mt10id;         // 외부 공연장 ID (KOPIS)
  private String name;           // 공연장명
  private String type;           // 시설특성 (공연장, 문예회관 등)
  private String address;        // 주소
  private String area;           // 지역 (시/도)
  private String gugun;          // 구/군
  private Integer seatCount;     // 총 좌석 수
  private Integer reviewCount;   // 리뷰 수
  private Double avgRating;      // 평균 평점
  private String homepage;       // 홈페이지 URL
  private Double latitude;       // 위도
  private Double longitude;      // 경도
  private Boolean hasParking;    // 주차장 여부
  private Boolean hasCafe;       // 카페 여부
  private Boolean hasRestaurant; // 레스토랑 여부
  private LocalDateTime createdAt;
}
