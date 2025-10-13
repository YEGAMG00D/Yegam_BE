package yegam.supportservice.inquiryThread.dto.response;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InquiryThreadCreateResponseDto {
  private String message;
  private LocalDateTime createdAt;
}
