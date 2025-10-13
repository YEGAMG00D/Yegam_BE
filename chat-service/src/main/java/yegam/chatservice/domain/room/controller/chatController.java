package yegam.chatservice.domain.room.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yegam.chatservice.domain.room.dto.request.RoomCreateRequestDto;
import yegam.chatservice.domain.room.dto.response.RoomResponseDto;
import yegam.chatservice.domain.room.entity.Room;
import yegam.chatservice.domain.room.mapper.RoomMapper;
import yegam.chatservice.domain.room.repository.RoomRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatController {

  private final RoomRepository roomRepository;
  private final RoomMapper roomMapper;

  // 특정 공연에 채팅방이 있는지 조회
  @GetMapping("/rooms/{performanceId}")
  public ResponseEntity<?> getRoom(@PathVariable Long performanceId) {
    Optional<Room> room = roomRepository.findByPerformanceId(performanceId);
    return room.map(value -> ResponseEntity.ok(roomMapper.toRoomResponseDto(value)))
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  // 채팅방 개설
  @PostMapping("/rooms")
  public ResponseEntity<RoomResponseDto> createRoom(@RequestBody RoomCreateRequestDto dto) {
    Room room = Room.builder()
        .performanceId(dto.getPerformanceId())
        .createdAt(LocalDateTime.now())
        .build();

    Room saved = roomRepository.save(room);
    return ResponseEntity.ok(roomMapper.toRoomResponseDto(saved));
  }
}
