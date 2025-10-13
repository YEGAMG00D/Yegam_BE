package yegam.userservice.domain.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import yegam.userservice.domain.user.dto.request.*;
import yegam.userservice.domain.user.dto.response.UserResponseDto;
import yegam.userservice.domain.user.service.UserService;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  /** 이메일 중복 확인 */
  @PostMapping("/check-duplicate")
  public ResponseEntity<?> checkDuplicate(@RequestBody @Valid UserSignUpRequestDto dto) {
    boolean isDuplicated = userService.checkDuplicateEmail(dto.getEmail());
    return ResponseEntity.ok().body(
        isDuplicated
            ? "이미 존재하는 이메일입니다."
            : "사용 가능한 이메일입니다."
    );
  }

  /** 회원가입 */
  @PostMapping
  public ResponseEntity<UserResponseDto> signUp(@RequestBody @Valid UserSignUpRequestDto dto) {
    UserResponseDto response = userService.signUp(dto);
    return ResponseEntity.ok(response);
  }

  /** 내 정보 조회 */
  @GetMapping("/me")
  public ResponseEntity<UserResponseDto> getMyInfo(@AuthenticationPrincipal Long userId) {
    UserResponseDto response = userService.getUserById(userId);
    return ResponseEntity.ok(response);
  }

  /** 내 정보 수정 */
  @PutMapping("/me")
  public ResponseEntity<UserResponseDto> updateMyInfo(
      @AuthenticationPrincipal Long userId,
      @RequestBody @Valid UserUpdateRequestDto dto
  ) {
    UserResponseDto response = userService.updateUser(userId, dto);
    return ResponseEntity.ok(response);
  }

  /** 비밀번호 변경 */
  @PatchMapping("/me/password")
  public ResponseEntity<?> changePassword(
      @AuthenticationPrincipal Long userId,
      @RequestBody @Valid PasswordChangeRequestDto dto
  ) {
    userService.changePassword(userId, dto);
    return ResponseEntity.ok("비밀번호가 변경되었습니다.");
  }

  /** 회원 탈퇴 (soft delete) */
  @PatchMapping("/me")
  public ResponseEntity<?> deleteUser(
      @AuthenticationPrincipal Long userId,
      @RequestBody(required = false) UserDeleteRequestDto dto
  ) {
    userService.deleteUser(userId, dto);
    return ResponseEntity.ok("회원 탈퇴가 완료되었습니다.");
  }
}
