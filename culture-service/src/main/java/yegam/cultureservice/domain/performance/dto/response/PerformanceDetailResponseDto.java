package yegam.cultureservice.domain.performance.dto.response;

import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PerformanceDetailResponseDto {
  private Long id;
  private String mt20id;
  private String title;
  private String category;
  private String area;
  private String placeName;
  private LocalDate startDate;
  private LocalDate endDate;
  private String runtime;
  private String ageLimit;
  private String cast;
  private String crew;
  private String ticketPrice;
  private String showTime;
  private String producer;
  private String agency;
  private String original;
  private String posterUrl;
  private String posterSubUrl1;
  private String posterSubUrl2;
  private String posterSubUrl3;
  private String relateUrl1;
  private String relateUrl2;
  private String relateUrl3;
  private String festival;
  private Double avgRating;
  private Integer reviewCount;
}
