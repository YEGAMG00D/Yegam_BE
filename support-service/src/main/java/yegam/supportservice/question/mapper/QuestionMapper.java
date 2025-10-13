package yegam.supportservice.question.mapper;

import org.springframework.stereotype.Component;
import yegam.supportservice.question.dto.response.QuestionResponseDto;
import yegam.supportservice.question.entity.Question;

@Component
public class QuestionMapper {
  public QuestionResponseDto toQuestionResponseDto(Question question) {
    if (question == null) return null;
    return QuestionResponseDto.builder()
        .questionId(question.getId())
        .title(question.getTitle())
        .content(question.getContent())
        .status(question.getStatus().name())
        .createdAt(question.getCreatedAt())
        .build();
  }
}
