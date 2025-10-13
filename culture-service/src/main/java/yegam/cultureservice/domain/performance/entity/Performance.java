package yegam.cultureservice.domain.performance.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(
    name = "performances",
    indexes = {
        @Index(name = "idx_title", columnList = "title"),
        @Index(name = "idx_place_id", columnList = "place_id")
    }
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Performance {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "performance_id")
  private Long id;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private String category;

  private String local;

  @Column(nullable = false)
  private LocalDate startDate;

  private LocalDate endDate;

  @Lob
  private String posterUrl;

  private String runtime;

  private String ageLimit;

  private String cast;

  private String crew;

  @Column(name = "place_id")
  private Long placeId;

  private Integer reviewCount = 0;

  private Double avgRating = 0.0;

  @Lob
  private String aiSummary;

  @CreationTimestamp
  private LocalDateTime createdAt;

  @UpdateTimestamp
  private LocalDateTime updatedAt;

  @Column(nullable = false)
  private Boolean isDeleted = false;

  private LocalDateTime deletedAt;

  @Column(nullable = false, unique = true, length = 36)
  private String uuid = UUID.randomUUID().toString();
}
