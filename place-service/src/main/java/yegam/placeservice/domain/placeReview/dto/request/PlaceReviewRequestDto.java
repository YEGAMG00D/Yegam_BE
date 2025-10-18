package yegam.placeservice.domain.placeReview.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaceReviewRequestDto {

  @NotBlank(message = "리뷰 내용을 입력해주세요.")
  private String contents;

  @NotNull(message = "평점을 입력해주세요.")
  @Min(value = 1, message = "평점은 최소 1점이어야 합니다.")
  @Max(value = 5, message = "평점은 최대 5점이어야 합니다.")
  private Integer rating;
}
