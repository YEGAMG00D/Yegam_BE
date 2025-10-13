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

  @Builder
  @Getter
  @AllArgsConstructor
  public static class FavoriteItem {
    private Long performanceId;
    private String title;
    private String posterUrl;
    private LocalDateTime createdAt;
  }

  private List<FavoriteItem> favorites;
}
