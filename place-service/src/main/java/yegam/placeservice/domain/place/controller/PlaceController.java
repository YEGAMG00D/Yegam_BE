package yegam.placeservice.domain.place.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yegam.placeservice.domain.place.dto.response.PlaceSummaryResponseDto;
import yegam.placeservice.domain.place.dto.response.PlaceDetailResponseDto;
import yegam.placeservice.domain.place.service.PlaceService;

import java.util.List;

@RestController
@RequestMapping("/api/places")
@Tag(name = "Place", description = "공연장 정보 API")
@RequiredArgsConstructor
public class PlaceController {

  private final PlaceService placeService;

  /** 공연장 전체 목록 조회 (카테고리, 정렬, 페이지네이션) */
  @Operation(summary = "공연장 전체 목록 조회", description = "모든 공연장의 정보를 카테고리, 정렬, 페이지별로 조회합니다.")
  @GetMapping
  public ResponseEntity<List<PlaceSummaryResponseDto>> getAllPlaces(
      @RequestParam(required = false) String type,
      @RequestParam(defaultValue = "latest") String sort,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "12") int size
  ) {
    return ResponseEntity.ok(placeService.getAllPlaces(type, sort, page, size));
  }

  /** 공연장 상세 조회 */
  @Operation(summary = "공연장 상세 조회", description = "공연장 ID를 이용해 상세 정보를 조회합니다.")
  @GetMapping("/{placeId}")
  public ResponseEntity<PlaceDetailResponseDto> getPlaceDetail(
      @PathVariable Long placeId
  ) {
    return ResponseEntity.ok(placeService.getPlaceDetail(placeId));
  }

  /** 공연장 이름/주소 검색 */
  @Operation(summary = "공연장 검색", description = "이름 또는 주소 키워드로 공연장을 검색합니다.")
  @GetMapping("/search")
  public ResponseEntity<List<PlaceSummaryResponseDto>> searchPlaces(
      @RequestParam String keyword,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "12") int size
  ) {
    return ResponseEntity.ok(placeService.searchPlaces(keyword, page, size));
  }

  /** 특정 반경 내 공연장 목록 */
  @Operation(summary = "주변 공연장 조회", description = "현재 위치 기준으로 반경 내 공연장을 조회합니다.")
  @GetMapping("/nearby")
  public ResponseEntity<List<PlaceSummaryResponseDto>> getNearbyPlaces(
      @RequestParam Double lat,
      @RequestParam Double lng,
      @RequestParam(defaultValue = "5") Double radiusKm
  ) {
    return ResponseEntity.ok(placeService.getNearbyPlaces(lat, lng, radiusKm));
  }
}
