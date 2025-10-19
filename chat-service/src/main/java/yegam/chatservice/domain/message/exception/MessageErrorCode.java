package yegam.chatservice.domain.message.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import yegam.chatservice.global.exception.model.BaseErrorCode;

@Getter
@AllArgsConstructor
public enum MessageErrorCode implements BaseErrorCode {

  ROOM_NOT_FOUND("MESSAGE_404", "해당 채팅방을 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
  MESSAGE_NOT_FOUND("MESSAGE_405", "해당 메시지를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
  FORBIDDEN_SENDER("MESSAGE_403", "본인만 메시지를 삭제 또는 수정할 수 있습니다.", HttpStatus.FORBIDDEN);

  private final String code;
  private final String message;
  private final HttpStatus status;
}
