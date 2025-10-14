package yegam.placeservice.domain.place.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import yegam.placeservice.global.exception.model.BaseErrorCode;

@Getter
@AllArgsConstructor
public enum PlaceErrorCode implements BaseErrorCode {

  PLACE_NOT_FOUND("PLACE_404", "공연장 정보를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
  INVALID_TYPE("PLACE_400", "유효하지 않은 공연장 타입입니다.", HttpStatus.BAD_REQUEST);

  private final String code;
  private final String message;
  private final HttpStatus status;
}
