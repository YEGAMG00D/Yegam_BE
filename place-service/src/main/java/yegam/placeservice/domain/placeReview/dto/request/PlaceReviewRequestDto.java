package yegam.placeservice.domain.placeReview.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaceReviewRequestDto {
  private Long userId;
  private String contents;
  private Integer rating;
}
