package yegam.supportservice.questionThread.mapper;

import org.springframework.stereotype.Component;
import yegam.supportservice.questionThread.dto.response.QuestionThreadCreateResponseDto;
import yegam.supportservice.questionThread.dto.response.QuestionThreadResponseDto;
import yegam.supportservice.questionThread.entity.QuestionThread;

@Component
public class QuestionThreadMapper {

  public QuestionThreadResponseDto toQuestionThreadResponseDto(QuestionThread thread) {
    if (thread == null) return null;
    return QuestionThreadResponseDto.builder()
        .senderId(thread.getSenderId())
        .senderRole(thread.getSenderRole().name())
        .content(thread.getContent())
        .createdAt(thread.getCreatedAt())
        .build();
  }

  public QuestionThreadCreateResponseDto toQuestionThreadCreateResponseDto(QuestionThread thread) {
    if (thread == null) return null;
    return QuestionThreadCreateResponseDto.builder()
        .message("질의 대화가 등록되었습니다.")
        .createdAt(thread.getCreatedAt())
        .build();
  }
}
