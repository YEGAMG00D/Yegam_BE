package yegam.communityservice.domain.comment.dto.response;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentResponseDto {
  private Long commentId;
  private Long userId;
  private String contents;
  private LocalDateTime createdAt;
}
