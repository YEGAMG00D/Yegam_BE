package yegam.cultureservice.domain.performanceReview.dto.response;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PerformanceReviewResponseDto {
  private Long reviewId;
  private Long userId;
  private String content;
  private Integer rating;
  private Integer likeCount;
  private LocalDateTime createdAt;
}
