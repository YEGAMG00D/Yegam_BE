package yegam.userservice.domain.user.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(
    name = "users",
    indexes = {
        @Index(name = "idx_nickname", columnList = "nickname")
    },
    uniqueConstraints = {
        @UniqueConstraint(name = "uk_email", columnNames = "email")
    }
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private Long id;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false)
  private String password;

  @Column
  private String name;

  @Column
  private LocalDate birth;

  @Column
  private String gender;

  @Column
  private String phone;

  @Column
  private String address;

  @Column(nullable = false)
  private String nickname;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Role role = Role.USER;

  @CreationTimestamp
  @Column(updatable = false)
  private LocalDateTime createdAt;

  @UpdateTimestamp
  private LocalDateTime updatedAt;

  @Column(nullable = false)
  private Boolean isDeleted = false;

  private LocalDateTime deletedAt;

  @Column(nullable = false, unique = true, length = 36)
  private String uuid = UUID.randomUUID().toString();



  public enum Role {
    USER,
    ADMIN
  }
}
