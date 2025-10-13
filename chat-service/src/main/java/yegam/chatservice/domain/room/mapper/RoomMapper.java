package yegam.chatservice.domain.room.mapper;

import org.springframework.stereotype.Component;
import yegam.chatservice.domain.room.dto.response.RoomResponseDto;
import yegam.chatservice.domain.room.entity.Room;

@Component
public class RoomMapper {

  public RoomResponseDto toRoomResponseDto(Room room) {
    if (room == null) return null;

    return RoomResponseDto.builder()
        .roomId(room.getId())
        .performanceId(room.getPerformanceId())
        .createdAt(room.getCreatedAt())
        .updatedAt(room.getUpdatedAt())
        .build();
  }
}
