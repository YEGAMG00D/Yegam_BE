package yegam.userservice.domain.user.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginRequestDto {

  @Email(message = "이메일 형식이 올바르지 않습니다.")
  @NotBlank(message = "이메일은 필수 입력값입니다.")
  private String email;

  @NotBlank(message = "비밀번호는 필수 입력값입니다.")
  private String password;
}
