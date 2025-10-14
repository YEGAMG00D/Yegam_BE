package yegam.cultureservice.domain.performance.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import yegam.cultureservice.global.exception.model.BaseErrorCode;

@Getter
@AllArgsConstructor
public enum PerformanceErrorCode implements BaseErrorCode {
  PERFORMANCE_NOT_FOUND("PERFORMANCE_404", "공연 정보를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
  INVALID_CATEGORY("PERFORMANCE_400", "유효하지 않은 카테고리입니다.", HttpStatus.BAD_REQUEST);

  private final String code;
  private final String message;
  private final HttpStatus status;
}
