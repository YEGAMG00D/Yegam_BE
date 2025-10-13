package yegam.chatservice.domain.message.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import yegam.chatservice.domain.room.entity.Room;

@Entity
@Table(name = "chat_messages")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Message {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long messageId;

  @Column(name = "user_id", nullable = false)
  private Long userId;

  @Column(columnDefinition = "TEXT", nullable = false)
  private String content;

  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "room_id", nullable = false)
  private Room room;

  @Column(name = "is_deleted", nullable = false)
  private Boolean isDeleted = false;

  @Column(name = "deleted_at")
  private LocalDateTime deletedAt;

  @Column(name = "uuid", unique = true)
  private String uuid;
}
