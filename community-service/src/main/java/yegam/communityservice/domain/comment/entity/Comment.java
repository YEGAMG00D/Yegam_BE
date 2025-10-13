package yegam.communityservice.domain.comment.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import yegam.communityservice.domain.post.entity.Post;

@Entity
@Table(name = "comments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long commentId;

  @Column(nullable = false)
  private Long userId; // 작성자 (User-service FK)

  @Column(nullable = false, columnDefinition = "TEXT")
  private String contents;

  private LocalDateTime createdAt;

  @Column(nullable = false)
  private Boolean isDeleted = false;

  private LocalDateTime deletedAt;


  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "post_id", nullable = false)
  private Post post;
}
