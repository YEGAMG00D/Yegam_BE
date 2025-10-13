package yegam.userservice.domain.user.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserSignUpRequestDto {

  @Email(message = "이메일 형식이 올바르지 않습니다.")
  @NotBlank(message = "이메일은 필수 입력값입니다.")
  private String email;

  @NotBlank(message = "비밀번호는 필수 입력값입니다.")
  @Size(min = 8, message = "비밀번호는 8자 이상이어야 합니다.")
  private String password;

  @NotBlank(message = "이름은 필수 입력값입니다.")
  private String name;

  private LocalDate birth; // LocalDate로 직접 받기

  private String gender;

  @Pattern(regexp = "^[0-9]{10,11}$", message = "연락처 형식이 올바르지 않습니다.")
  private String phone;

  private String address;

  @NotBlank(message = "닉네임은 필수 입력값입니다.")
  private String nickname;
}
