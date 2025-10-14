package yegam.placeservice.domain.place.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaceSearchRequestDto {
  private String local;       // 지역명 (예: 서울)
  private String keyword;     // 검색어
  private String sort;        // popular / latest
  private int page;
  private int size;
}
