package yegam.communityservice.domain.comment.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentRequestDto {
  private Long userId;
  private String contents;
}
