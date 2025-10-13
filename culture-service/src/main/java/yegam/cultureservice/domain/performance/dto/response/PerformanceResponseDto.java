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
  private String title;
  private String category;
  private String local;
  private LocalDate startDate;
  private LocalDate endDate;
  private String posterUrl;
  private Double avgRating;
  private Integer reviewCount;
}
