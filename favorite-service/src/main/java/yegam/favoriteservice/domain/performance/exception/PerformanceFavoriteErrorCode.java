package yegam.favoriteservice.domain.performance.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import yegam.favoriteservice.global.exception.model.BaseErrorCode;

@Getter
@AllArgsConstructor
public enum PerformanceFavoriteErrorCode implements BaseErrorCode {

  FAVORITE_NOT_FOUND("FAVORITE_4001", "관심 공연을 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
  FAVORITE_ALREADY_EXISTS("FAVORITE_4002", "이미 관심 공연으로 등록되어 있습니다.", HttpStatus.CONFLICT),
  FAVORITE_SAVE_FAILED("FAVORITE_5001", "관심 공연 저장에 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR),
  FAVORITE_DELETE_FAILED("FAVORITE_5002", "관심 공연 삭제에 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR),
  INVALID_USER("FAVORITE_4003", "잘못된 사용자 접근입니다.", HttpStatus.UNAUTHORIZED),
  INVALID_REQUEST("FAVORITE_4004", "요청이 올바르지 않습니다.", HttpStatus.BAD_REQUEST);

  private final String code;      // 에러 코드 문자열
  private final String message;   // 에러 메시지
  private final HttpStatus status; // HTTP 상태 코드
}
