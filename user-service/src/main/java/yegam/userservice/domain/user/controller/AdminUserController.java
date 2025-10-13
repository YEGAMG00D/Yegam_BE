package yegam.userservice.domain.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import yegam.userservice.domain.user.dto.response.UserResponseDto;
import yegam.userservice.domain.user.service.AdminUserService;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminUserController {

  private final AdminUserService adminUserService;

  /** 전체 회원 목록 조회 */
  @GetMapping("/users")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<List<UserResponseDto>> getAllUsers() {
    List<UserResponseDto> users = adminUserService.getAllUsers();
    return ResponseEntity.ok(users);
  }

  /** 특정 회원 상세 조회 */
  @GetMapping("/users/{userId}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long userId) {
    UserResponseDto user = adminUserService.getUserById(userId);
    return ResponseEntity.ok(user);
  }
}
