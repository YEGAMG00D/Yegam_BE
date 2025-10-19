package yegam.chatservice.domain.room.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yegam.chatservice.domain.room.dto.request.RoomCreateRequestDto;
import yegam.chatservice.domain.room.dto.response.RoomResponseDto;
import yegam.chatservice.domain.room.service.RoomService;
import yegam.chatservice.global.response.BaseResponse;

import java.util.List;

@Tag(name = "Room API", description = "채팅방(Room) 생성/조회 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rooms")
public class RoomController {

  private final RoomService roomService;

  @Operation(summary = "공연 ID로 채팅방 생성 또는 조회")
  @PostMapping
  public ResponseEntity<BaseResponse<RoomResponseDto>> createRoom(@RequestBody RoomCreateRequestDto dto) {
    RoomResponseDto room = roomService.createRoom(dto);
    return ResponseEntity.ok(BaseResponse.success("채팅방 생성/반환 성공", room));
  }

  @Operation(summary = "공연 ID로 채팅방 조회")
  @GetMapping("/{performanceId}")
  public ResponseEntity<BaseResponse<RoomResponseDto>> getRoomByPerformanceId(@PathVariable Long performanceId) {
    RoomResponseDto room = roomService.getRoomByPerformanceId(performanceId);
    return ResponseEntity.ok(BaseResponse.success("채팅방 조회 성공", room));
  }

  @Operation(summary = "전체 채팅방 목록 조회")
  @GetMapping
  public ResponseEntity<BaseResponse<List<RoomResponseDto>>> getAllRooms() {
    List<RoomResponseDto> rooms = roomService.getAllRooms();
    return ResponseEntity.ok(BaseResponse.success("채팅방 목록 조회 성공", rooms));
  }
}
