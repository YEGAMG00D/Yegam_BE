package yegam.communityservice.domain.post.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostRequestDto {
  private Long userId;
  private String title;
  private String contents;
  private String category;
}
