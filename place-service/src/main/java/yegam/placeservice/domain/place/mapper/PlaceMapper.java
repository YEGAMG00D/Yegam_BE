package yegam.placeservice.domain.place.mapper;

import org.springframework.stereotype.Component;
import yegam.placeservice.domain.place.dto.response.PlaceResponseDto;
import yegam.placeservice.domain.place.entity.Place;

@Component
public class PlaceMapper {

  public PlaceResponseDto toPlaceResponseDto(Place place) {
    if (place == null) return null;

    return PlaceResponseDto.builder()
        .placeId(place.getPlaceId())
        .name(place.getName())
        .type(place.getType())
        .address(place.getAddress())
        .homepage(place.getHomepage())
        .openingYear(place.getOpeningYear())
        .totalSeat(place.getTotalSeat())
        .lat(place.getLat())
        .lng(place.getLng())
        .facilities(place.getFacilities())
        .reviewCount(place.getReviewCount())
        .createdAt(place.getCreatedAt())
        .updatedAt(place.getUpdatedAt())
        .build();
  }



}
