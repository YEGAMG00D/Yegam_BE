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
  private String title;
  private String category;
  private String local;
  private LocalDate startDate;
  private LocalDate endDate;
  private String runtime;
  private String ageLimit;
  private String cast;
  private String crew;
  private Long placeId;
  private Double avgRating;
  private Integer reviewCount;
  private String aiSummary;
}
