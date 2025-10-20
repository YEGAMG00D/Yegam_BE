package yegam.favoriteservice.domain.performance.dto.response;

import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PerformanceFavoriteListResponseDto {
  private Long userId;
  private List<PerformanceFavoriteResponseDto> favorites;
}
