package yegam.placeservice.domain.placeReview.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import yegam.placeservice.domain.place.entity.Place;

@Entity
@Table(name = "place_reviews")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaceReview {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long placeReviewId;

  @Column(nullable = false)
  private Long userId; // FK (User-service 연동 시 외부참조)

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "place_id", nullable = false)
  private Place place;

  @Column(nullable = false, columnDefinition = "TEXT")
  private String contents;

  @Column(nullable = false)
  private Integer rating;

  private LocalDateTime createdAt;
  private Boolean isDeleted = false;
  private LocalDateTime deletedAt;
}
