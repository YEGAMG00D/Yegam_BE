package yegam.placeservice.domain.place.mapper;

import org.springframework.stereotype.Component;
import yegam.placeservice.domain.place.dto.response.*;
import yegam.placeservice.domain.place.entity.*;

import java.util.stream.Collectors;

@Component
public class PlaceMapper {

  /** 공연장 목록 요약 정보 매핑 */
  public PlaceSummaryResponseDto toPlaceSummaryResponseDto(PlaceSummary entity) {
    if (entity == null) return null;

    return PlaceSummaryResponseDto.builder()
        .id(entity.getId())
        .mt10id(entity.getMt10id())
        .name(entity.getName())
        .type(entity.getType())
        .address(entity.getAddress())
        .area(entity.getAreaSido())
        .gugun(entity.getAreaGungu())
        .seatCount(entity.getSeatCount())
        .reviewCount(0) // 리뷰 연동 전 임시값
        .avgRating(0.0)
        .homepage(entity.getHomepage())
        .latitude(entity.getLatitude())
        .longitude(entity.getLongitude())
        .hasParking(entity.getHasParking())
        .hasCafe(entity.getHasCafe())
        .hasRestaurant(entity.getHasRestaurant())
        .createdAt(entity.getCreatedAt())
        .build();
  }

  /** 공연장 상세 정보 매핑 */
  public PlaceDetailResponseDto toPlaceDetailResponseDto(PlaceDetail entity) {
    if (entity == null) return null;

    return PlaceDetailResponseDto.builder()
        .id(entity.getId())
        .mt10id(entity.getMt10id())
        .name(entity.getPerformanceHallName())
        .type("공연장")
        .address(null)
        .area(null)
        .gugun(null)
        .seatCount(entity.getSeatScale())
        .stageCount(1)
        .openingYear(null)
        .tel(null)
        .homepage(null)
        .latitude(null)
        .longitude(null)
        .hasRestaurant(false)
        .hasCafe(false)
        .hasStore(false)
        .hasParking(entity.getHasBackstage())
        .hasElevator(false)
        .hasWheelchairSeat(entity.getHasDisabledSeat())
        .facilityNote(entity.getStageArea())
        .stages(
            java.util.List.of(
                StageInfoDto.builder()
                    .name(entity.getHallName())
                    .seatScale(entity.getSeatScale())
                    .hasOrchestraPit(entity.getHasOrchestraPit())
                    .hasRehearsalRoom(entity.getHasStageEquip())
                    .stageArea(entity.getStageArea())
                    .build()
            )
        )
        .updatedAt(entity.getUpdatedAt())
        .build();
  }
}
