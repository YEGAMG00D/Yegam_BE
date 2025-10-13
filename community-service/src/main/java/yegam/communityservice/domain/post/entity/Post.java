package yegam.communityservice.domain.post.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;
import yegam.communityservice.domain.comment.entity.Comment;

@Entity
@Table(name = "posts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long postId;

  @Column(nullable = false)
  private Long userId; // 작성자 (User-service FK)

  @Column(nullable = false)
  private String title;

  @Column(nullable = false, columnDefinition = "TEXT")
  private String contents;

  private String category; // 게시글 분류 (예: 공지, 자유, 후기 등)

  @Column(nullable = false)
  private Integer viewCount = 0;

  @Column(nullable = false)
  private Integer commentCount = 0;

  @Column(nullable = false)
  private Integer likeCount = 0;

  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  @Column(nullable = false)
  private Boolean isDeleted = false;

  private LocalDateTime deletedAt;

  @Column(nullable = false, unique = true, length = 36)
  private String uuid;


  @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Comment> comments;
}
