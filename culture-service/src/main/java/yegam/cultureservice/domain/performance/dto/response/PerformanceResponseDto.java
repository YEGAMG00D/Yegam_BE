package yegam.cultureservice.domain.performance.dto.response;

import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PerformanceResponseDto {
  private Long id;
  private String mt20id;
  private String title;
  private String category;
  private String area;
  private String placeName;
  private LocalDate startDate;
  private LocalDate endDate;
  private String state;
  private String posterUrl;
  private Double avgRating;
  private Integer reviewCount;
}
