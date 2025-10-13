package yegam.supportservice.domain.questionThread.dto.request;

import lombok.*;
import yegam.supportservice.domain.questionThread.entity.QuestionThread;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionThreadRequestDto {
  private String content;
  private String senderRole; // USER or ADMIN

  public QuestionThread toEntity(Long senderId) {
    return QuestionThread.builder()
        .senderId(senderId)
        .senderRole(QuestionThread.SenderRole.valueOf(senderRole))
        .content(content)
        .build();
  }
}
