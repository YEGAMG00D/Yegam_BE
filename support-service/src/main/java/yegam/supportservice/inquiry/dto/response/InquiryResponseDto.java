package yegam.supportservice.inquiry.dto.response;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InquiryResponseDto {
  private Long inquiryId;
  private String title;
  private String content;
  private String status;
  private LocalDateTime createdAt;

}
