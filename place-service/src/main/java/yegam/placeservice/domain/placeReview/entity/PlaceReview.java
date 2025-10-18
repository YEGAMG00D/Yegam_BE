package yegam.placeservice.domain.placeReview.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import yegam.placeservice.domain.place.entity.PlaceSummary;
import yegam.placeservice.global.common.BaseTimeEntity;

@Entity
@Table(name = "place_reviews")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaceReview extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long placeReviewId;

  @Column(nullable = false)
  private Long userId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "place_id", nullable = false)
  private PlaceSummary place;

  @Column(nullable = false, columnDefinition = "TEXT")
  private String contents;

  @Column(nullable = false)
  private Integer rating;

  private Boolean isDeleted = false;
  private LocalDateTime deletedAt;
}
