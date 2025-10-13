package yegam.communityservice.domain.comment.dto.response;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCommentListResponseDto {
  private Long commentId;
  private Long postId;
  private String postTitle;
  private String contents;
  private LocalDateTime createdAt;
}
