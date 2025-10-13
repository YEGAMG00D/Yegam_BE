package yegam.supportservice.inquiryThread.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import yegam.supportservice.inquiry.entity.Inquiry;

@Entity
@Table(name = "inquiry_threads")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InquiryThread {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "inquiry_thread_id")
  private Long id; // 문의 대화 고유 id

  @Column(name = "sender_id", nullable = false)
  private Long senderId; // 작성자 id

  @Enumerated(EnumType.STRING)
  @Column(name = "sender_role", nullable = false)
  private SenderRole senderRole; // 작성자 구분: USER / ADMIN

  @Column(name = "content", columnDefinition = "TEXT", nullable = false)
  private String content; // 내용

  @Column(name = "created_at")
  private LocalDateTime createdAt; // 작성일


  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "inquiry_id")
  private Inquiry inquiry;

  public enum SenderRole {
    USER, ADMIN
  }
}
