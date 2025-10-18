package yegam.placeservice.domain.place.dto.response;

import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaceDetailResponseDto {
  private Long id;
  private String mt10id;
  private String name;
  private String type;
  private String address;
  private String area;
  private String gugun;
  private Integer seatCount;
  private Integer stageCount;        // 공연장 수
  private String openingYear;        // 개관연도
  private String tel;                // 전화번호
  private String homepage;
  private Double latitude;
  private Double longitude;
  private Boolean hasRestaurant;
  private Boolean hasCafe;
  private Boolean hasStore;
  private Boolean hasParking;
  private Boolean hasElevator;
  private Boolean hasWheelchairSeat;
  private String facilityNote;       // 시설특이사항
  private List<StageInfoDto> stages; // 세부 공연장 리스트
  private LocalDateTime updatedAt;
}

