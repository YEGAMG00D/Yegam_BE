package yegam.favoriteservice.domain.performance.dto.response;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PerformanceFavoriteResponseDto {
  private Long performanceId;
  private Long userId;
  private boolean isFavorite;
  private String message;
  private LocalDateTime updatedAt;
}
