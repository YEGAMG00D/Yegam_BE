package yegam.communityservice.domain.post.dto.response;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostResponseDto {
  private Long postId;
  private Long userId;
  private String title;
  private String contents;
  private String category;
  private Integer viewCount;
  private Integer likeCount;
  private Integer commentCount;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
