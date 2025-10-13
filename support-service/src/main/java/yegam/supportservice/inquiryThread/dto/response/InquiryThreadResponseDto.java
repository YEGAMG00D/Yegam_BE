package yegam.supportservice.inquiryThread.dto.response;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InquiryThreadResponseDto {
  private Long senderId;
  private String senderRole;
  private String content;
  private LocalDateTime createdAt;

}
