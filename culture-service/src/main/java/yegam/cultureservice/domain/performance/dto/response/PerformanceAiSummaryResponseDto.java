package yegam.cultureservice.domain.performance.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PerformanceAiSummaryResponseDto {
  private Long performanceId;
  private String aiSummary;
}
