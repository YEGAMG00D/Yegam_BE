package yegam.cultureservice.domain.performanceReview.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import yegam.cultureservice.global.exception.model.BaseErrorCode;

@Getter
@AllArgsConstructor
public enum PerformanceReviewErrorCode implements BaseErrorCode {
  PERFORMANCE_REVIEW_NOT_FOUND("PERFORMANCE_REVIEW_404", "공연 후기 정보를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
  INVALID_CATEGORY("PERFORMANCE_REVIEW_400", "유효하지 않은 요청 형식입니다.", HttpStatus.BAD_REQUEST),
  UNAUTHORIZED_USER("PERFORMANCE_REVIEW_403", "본인의 후기만 수정 또는 삭제할 수 있습니다.", HttpStatus.FORBIDDEN);

  private final String code;
  private final String message;
  private final HttpStatus status;
}
