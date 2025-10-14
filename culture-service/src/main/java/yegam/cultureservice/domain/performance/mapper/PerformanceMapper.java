package yegam.cultureservice.domain.performance.mapper;

import org.springframework.stereotype.Component;
import yegam.cultureservice.domain.performance.dto.response.*;
import yegam.cultureservice.domain.performance.entity.*;

@Component
public class PerformanceMapper {

  /** 공연 목록용 DTO 변환 */
  public PerformanceResponseDto toPerformanceResponseDto(PerformanceSummary entity) {
    return PerformanceResponseDto.builder()
        .id(entity.getId())
        .mt20id(entity.getMt20id())
        .title(entity.getTitle())
        .category(entity.getCategory())
        .area(entity.getArea())
        .placeName(entity.getPlaceName())
        .startDate(entity.getStartDate())
        .endDate(entity.getEndDate())
        .state(entity.getState())
        .posterUrl(entity.getPosterUrl())
        .avgRating(0.0)
        .reviewCount(0)
        .build();
  }

  /** 공연 상세 DTO 변환 (요약 + 상세 결합) */
  public PerformanceDetailResponseDto toPerformanceDetailResponseDto(PerformanceSummary summary, PerformanceDetail detail) {
    return PerformanceDetailResponseDto.builder()
        .id(summary.getId())
        .mt20id(summary.getMt20id())
        .title(summary.getTitle())
        .category(summary.getCategory())
        .area(summary.getArea())
        .placeName(summary.getPlaceName())
        .startDate(summary.getStartDate())
        .endDate(summary.getEndDate())
        .posterUrl(summary.getPosterUrl())
        .runtime(detail.getRuntime())
        .ageLimit(detail.getAgeLimit())
        .cast(detail.getCast())
        .crew(detail.getCrew())
        .ticketPrice(detail.getTicketPrice())
        .showTime(detail.getShowTime())
        .producer(detail.getProducer())
        .agency(detail.getAgency())
        .original(detail.getOriginal())
        .posterSubUrl1(detail.getPosterSubUrl1())
        .posterSubUrl2(detail.getPosterSubUrl2())
        .posterSubUrl3(detail.getPosterSubUrl3())
        .relateUrl1(detail.getRelateUrl1())
        .relateUrl2(detail.getRelateUrl2())
        .relateUrl3(detail.getRelateUrl3())
        .festival(detail.getFestival())
        .avgRating(0.0)
        .reviewCount(0)
        .build();
  }
}
