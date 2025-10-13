package yegam.communityservice.domain.post.mapper;

import org.springframework.stereotype.Component;
import yegam.communityservice.domain.post.dto.response.PostResponseDto;
import yegam.communityservice.domain.post.dto.response.UserPostListResponseDto;
import yegam.communityservice.domain.post.entity.Post;

@Component
public class PostMapper {

  public PostResponseDto toPostResponseDto(Post post) {
    if (post == null) return null;

    return PostResponseDto.builder()
        .postId(post.getPostId())
        .userId(post.getUserId())
        .title(post.getTitle())
        .contents(post.getContents())
        .category(post.getCategory())
        .viewCount(post.getViewCount())
        .likeCount(post.getLikeCount())
        .commentCount(post.getCommentCount())
        .createdAt(post.getCreatedAt())
        .updatedAt(post.getUpdatedAt())
        .build();
  }

  public UserPostListResponseDto toResponse(Post post) {
    if (post == null) return null;

    return UserPostListResponseDto.builder()
        .postId(post.getPostId())
        .title(post.getTitle())
        .category(post.getCategory())
        .viewCount(post.getViewCount())
        .likeCount(post.getLikeCount())
        .commentCount(post.getCommentCount())
        .createdAt(post.getCreatedAt())
        .build();
  }
}
