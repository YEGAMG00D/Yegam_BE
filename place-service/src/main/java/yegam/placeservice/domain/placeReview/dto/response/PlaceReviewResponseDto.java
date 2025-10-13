package yegam.placeservice.domain.placeReview.dto.response;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaceReviewResponseDto {
  private Long reviewId;
  private Long userId;
  private String contents;
  private Integer rating;
  private LocalDateTime createdAt;

}
