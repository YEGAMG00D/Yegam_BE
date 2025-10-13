package yegam.supportservice.domain.question.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import yegam.supportservice.questionThread.entity.QuestionThread;

@Entity
@Table(name = "questions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Question {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "question_id")
  private Long id; // 질의 고유 id

  @Column(name = "user_id", nullable = false)
  private Long userId; // 질의 작성자

  @Column(name = "title", nullable = false)
  private String title; // 질의 제목

  @Column(name = "content", columnDefinition = "TEXT", nullable = false)
  private String content; // 질의 내용

  @Enumerated(EnumType.STRING)
  @Column(name = "status", nullable = false)
  private QuestionStatus status = QuestionStatus.WAIT; // 상태

  @Column(name = "created_at")
  private LocalDateTime createdAt; // 등록일

  @Column(name = "updated_at")
  private LocalDateTime updatedAt; // 수정일

  @Column(name = "is_deleted", nullable = false)
  private boolean isDeleted = false; // 삭제 여부

  @Column(name = "deleted_at")
  private LocalDateTime deletedAt; // 삭제된 시각


  @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<QuestionThread> threads = new ArrayList<>();

  public enum QuestionStatus {
    WAIT, ANSWERED, CLOSED
  }
}
