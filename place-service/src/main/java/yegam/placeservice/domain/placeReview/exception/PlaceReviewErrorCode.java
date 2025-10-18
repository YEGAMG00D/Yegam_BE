package yegam.placeservice.domain.placeReview.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import yegam.placeservice.global.exception.model.BaseErrorCode;


@Getter
@AllArgsConstructor
public enum PlaceReviewErrorCode implements BaseErrorCode {

  PLACE_REVIEW_NOT_FOUND("PLACE_REVIEW_404", "공연장 리뷰를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
  ACCESS_DENIED("PLACE_REVIEW_403", "본인이 작성한 리뷰만 수정 또는 삭제할 수 있습니다.", HttpStatus.FORBIDDEN);


  private final String code;
  private final String message;
  private final HttpStatus status;
}
