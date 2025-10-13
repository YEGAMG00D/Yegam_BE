package yegam.favoriteservice.domain.post.dto.response;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostFavoriteResponseDto {
  private Long postId;
  private Long userId;
  private boolean isFavorite;
  private String message;
  private LocalDateTime updatedAt;
}
