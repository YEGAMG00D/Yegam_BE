package yegam.placeservice.domain.place.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "places")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Place {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long placeId;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String type;

  @Column(nullable = false)
  private String address;

  private String homepage;
  private Integer openingYear;
  private Integer totalSeat = 0;
  private Double lat;
  private Double lng;
  private String facilities;

  @Column(nullable = false)
  private Integer reviewCount = 0;

  @Column(nullable = false)
  private Boolean isDeleted = false;

  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private LocalDateTime deletedAt;

  @Column(nullable = false, unique = true, length = 36)
  private String uuid;
}
