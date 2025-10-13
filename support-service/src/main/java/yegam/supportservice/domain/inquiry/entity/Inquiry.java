package yegam.supportservice.domain.inquiry.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import yegam.supportservice.inquiryThread.entity.InquiryThread;

@Entity
@Table(name = "inquiries")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Inquiry {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "inquiry_id")
  private Long id; // 문의 고유 id

  @Column(name = "user_id", nullable = false)
  private Long userId; // 문의 작성자

  @Column(name = "title", nullable = false)
  private String title; // 문의 제목

  @Column(name = "content", columnDefinition = "TEXT", nullable = false)
  private String content; // 문의 내용

  @Enumerated(EnumType.STRING)
  @Column(name = "status", nullable = false)
  private InquiryStatus status = InquiryStatus.WAIT; // 상태: WAIT / ANSWERED / CLOSED

  @Column(name = "created_at")
  private LocalDateTime createdAt; // 등록일

  @Column(name = "updated_at")
  private LocalDateTime updatedAt; // 수정일

  @Column(name = "is_deleted", nullable = false)
  private boolean isDeleted = false; // 삭제 여부

  @Column(name = "deleted_at")
  private LocalDateTime deletedAt; // 삭제된 시각


  @OneToMany(mappedBy = "inquiry", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<InquiryThread> threads = new ArrayList<>();

  public enum InquiryStatus {
    WAIT, ANSWERED, CLOSED
  }
}
