package yegam.placeservice.domain.placeReview.dto.response;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserPlaceReviewResponseDto {
  private Long reviewId;
  private String placeName;
  private String contents;
  private Integer rating;
  private LocalDateTime createdAt;
}
