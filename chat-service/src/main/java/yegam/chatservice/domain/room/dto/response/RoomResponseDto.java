package yegam.chatservice.domain.room.dto.response;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomResponseDto {
  private Long roomId;
  private Long performanceId;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
