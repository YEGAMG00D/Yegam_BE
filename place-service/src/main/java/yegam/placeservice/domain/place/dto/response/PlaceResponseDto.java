package yegam.placeservice.domain.place.dto.response;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaceResponseDto {
  private Long placeId;
  private String name;
  private String type;
  private String address;
  private String homepage;
  private Integer openingYear;
  private Integer totalSeat;
  private Double lat;
  private Double lng;
  private String facilities;
  private Integer reviewCount;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

}
