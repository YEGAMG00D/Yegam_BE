package yegam.placeservice.domain.place.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StageInfoDto {
  private String name;
  private Integer seatScale;
  private Boolean hasOrchestraPit;
  private Boolean hasRehearsalRoom;
  private String stageArea;
}
