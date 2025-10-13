package yegam.supportservice.questionThread.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import yegam.supportservice.question.entity.Question;

@Entity
@Table(name = "question_threads")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionThread {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "question_thread_id")
  private Long id; // 질의 대화 고유 id

  @Column(name = "sender_id", nullable = false)
  private Long senderId; // 작성자 id

  @Enumerated(EnumType.STRING)
  @Column(name = "sender_role", nullable = false)
  private SenderRole senderRole; // 작성자 구분: USER / ADMIN

  @Column(name = "content", columnDefinition = "TEXT", nullable = false)
  private String content;

  @Column(name = "created_at")
  private LocalDateTime createdAt;


  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "question_id")
  private Question question;

  public enum SenderRole {
    USER, ADMIN
  }
}
