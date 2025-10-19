package yegam.chatservice.domain.room.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yegam.chatservice.domain.room.dto.request.RoomCreateRequestDto;
import yegam.chatservice.domain.room.dto.response.RoomResponseDto;
import yegam.chatservice.domain.room.entity.Room;
import yegam.chatservice.domain.room.mapper.RoomMapper;
import yegam.chatservice.domain.room.repository.RoomRepository;
import yegam.chatservice.global.exception.CustomException;
import yegam.chatservice.global.exception.GlobalErrorCode;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class RoomService {

  private final RoomRepository roomRepository;
  private final RoomMapper roomMapper;

  /** 공연별 채팅방 생성 (없으면 생성, 있으면 반환) */
  public RoomResponseDto createRoom(RoomCreateRequestDto dto) {
    if (dto.getPerformanceId() == null) {
      throw new CustomException(GlobalErrorCode.INVALID_INPUT_VALUE);
    }

    return roomRepository.findByPerformanceId(dto.getPerformanceId())
        .map(roomMapper::toRoomResponseDto)
        .orElseGet(() -> {
          Room room = Room.builder()
              .performanceId(dto.getPerformanceId())
              .build();
          Room saved = roomRepository.save(room);
          return roomMapper.toRoomResponseDto(saved);
        });
  }

  /** 공연 ID 기준 단일 채팅방 조회 */
  @Transactional(readOnly = true)
  public RoomResponseDto getRoomByPerformanceId(Long performanceId) {
    Room room = roomRepository.findByPerformanceId(performanceId)
        .orElseThrow(() -> new CustomException(GlobalErrorCode.RESOURCE_NOT_FOUND));
    return roomMapper.toRoomResponseDto(room);
  }

  /** 모든 채팅방 목록 조회 */
  @Transactional(readOnly = true)
  public List<RoomResponseDto> getAllRooms() {
    return roomRepository.findAll().stream()
        .map(roomMapper::toRoomResponseDto)
        .collect(Collectors.toList());
  }
}
