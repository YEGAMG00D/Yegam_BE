package yegam.cultureservice.domain.performanceReview.dto.response;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserReviewListResponseDto {
  private Long reviewId;
  private Long performanceId;
  private String title;
  private String content;
  private Integer rating;
  private LocalDateTime createdAt;
}
