package yegam.supportservice.domain.inquiryThread.dto.request;

import lombok.*;
import yegam.supportservice.domain.inquiryThread.entity.InquiryThread;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InquiryThreadRequestDto {
  private String content;
  private String senderRole; // USER or ADMIN

  public InquiryThread toEntity(Long senderId) {
    return InquiryThread.builder()
        .senderId(senderId)
        .senderRole(InquiryThread.SenderRole.valueOf(senderRole))
        .content(content)
        .build();
  }
}
