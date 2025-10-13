package yegam.favoriteservice.domain.post.dto.response;

import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostFavoriteListResponseDto {
  private Long userId;

  @Getter
  @AllArgsConstructor
  @Builder
  public static class FavoriteItem {
    private Long postId;
    private String title;
    private LocalDateTime createdAt;
  }

  private List<FavoriteItem> favorites;
}
