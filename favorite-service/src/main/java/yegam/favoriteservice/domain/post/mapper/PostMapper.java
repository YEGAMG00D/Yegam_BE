package yegam.favoriteservice.domain.post.mapper;

import org.springframework.stereotype.Component;
import yegam.favoriteservice.domain.post.dto.response.PostFavoriteResponseDto;
import yegam.favoriteservice.domain.post.entity.Post;

@Component
public class PostMapper {

  public PostFavoriteResponseDto toPostFavoriteResponseDto(Post entity, boolean isFavorite, String message) {
    if (entity == null) return null;

    return PostFavoriteResponseDto.builder()
        .postId(entity.getPostId())
        .userId(entity.getUserId())
        .isFavorite(isFavorite)
        .message(message)
        .updatedAt(entity.getUpdatedAt())
        .build();
  }
}
