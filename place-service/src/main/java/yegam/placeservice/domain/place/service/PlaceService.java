package yegam.placeservice.domain.place.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yegam.placeservice.domain.place.dto.response.PlaceSummaryResponseDto;
import yegam.placeservice.domain.place.dto.response.PlaceDetailResponseDto;
import yegam.placeservice.domain.place.entity.PlaceSummary;
import yegam.placeservice.domain.place.entity.PlaceDetail;
import yegam.placeservice.domain.place.exception.PlaceErrorCode;
import yegam.placeservice.domain.place.mapper.PlaceMapper;
import yegam.placeservice.domain.place.repository.PlaceSummaryRepository;
import yegam.placeservice.domain.place.repository.PlaceDetailRepository;
import yegam.placeservice.global.exception.CustomException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlaceService {

  private final PlaceSummaryRepository placeSummaryRepository;
  private final PlaceDetailRepository placeDetailRepository;
  private final PlaceMapper placeMapper;

  /** 공연장 전체 목록 */
  public List<PlaceSummaryResponseDto> getAllPlaces(String type, String sort, int page, int size) {
    Pageable pageable = switch (sort.toLowerCase()) {
      case "latest" -> PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
      default -> PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "reviewCount"));
    };

    Page<PlaceSummary> places = (type == null || type.isEmpty())
        ? placeSummaryRepository.findByIsDeletedFalse(pageable)
        : placeSummaryRepository.findByTypeAndIsDeletedFalse(type, pageable);

    return places.getContent().stream()
        .map(placeMapper::toPlaceSummaryResponseDto)
        .collect(Collectors.toList());
  }

  /** 공연장 상세 조회 */
  public PlaceDetailResponseDto getPlaceDetail(Long id) {
    PlaceDetail placeDetail = placeDetailRepository.findById(id)
        .orElseThrow(() -> new CustomException(PlaceErrorCode.PLACE_NOT_FOUND));

    return placeMapper.toPlaceDetailResponseDto(placeDetail);
  }

  /** 공연장 검색 */
  public List<PlaceSummaryResponseDto> searchPlaces(String keyword, int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
    Page<PlaceSummary> places =
        placeSummaryRepository.findByNameContainingOrAddressContainingAndIsDeletedFalse(keyword, keyword, pageable);

    return places.getContent().stream()
        .map(placeMapper::toPlaceSummaryResponseDto)
        .collect(Collectors.toList());
  }

  /** 반경 내 공연장 검색 (위치 기반) */
  public List<PlaceSummaryResponseDto> getNearbyPlaces(Double lat, Double lng, Double radiusKm) {
    List<PlaceSummary> places = placeSummaryRepository.findNearbyPlaces(lat, lng, radiusKm);
    return places.stream()
        .map(placeMapper::toPlaceSummaryResponseDto)
        .collect(Collectors.toList());
  }
}
