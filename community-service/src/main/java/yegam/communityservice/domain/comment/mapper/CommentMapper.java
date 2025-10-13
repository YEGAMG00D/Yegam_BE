package yegam.communityservice.domain.comment.mapper;

import org.springframework.stereotype.Component;
import yegam.communityservice.domain.comment.dto.response.CommentResponseDto;
import yegam.communityservice.domain.comment.dto.response.UserCommentListResponseDto;
import yegam.communityservice.domain.comment.entity.Comment;

@Component
public class CommentMapper {

  public CommentResponseDto toCommentResponseDto(Comment comment) {
    if (comment == null) return null;

    return CommentResponseDto.builder()
        .commentId(comment.getCommentId())
        .userId(comment.getUserId())
        .contents(comment.getContents())
        .createdAt(comment.getCreatedAt())
        .build();
  }

  public UserCommentListResponseDto toResponse(Comment comment) {
    if (comment == null || comment.getPost() == null) return null;

    return UserCommentListResponseDto.builder()
        .commentId(comment.getCommentId())
        .postId(comment.getPost().getPostId())
        .postTitle(comment.getPost().getTitle())
        .contents(comment.getContents())
        .createdAt(comment.getCreatedAt())
        .build();
  }
}
