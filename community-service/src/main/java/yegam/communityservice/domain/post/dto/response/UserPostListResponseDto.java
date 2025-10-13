package yegam.communityservice.domain.post.dto.response;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserPostListResponseDto {
  private Long postId;
  private String title;
  private String category;
  private Integer viewCount;
  private Integer likeCount;
  private Integer commentCount;
  private LocalDateTime createdAt;
}
