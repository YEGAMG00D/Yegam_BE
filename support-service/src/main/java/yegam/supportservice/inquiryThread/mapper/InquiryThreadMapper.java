package yegam.supportservice.inquiryThread.mapper;

import org.springframework.stereotype.Component;
import yegam.supportservice.inquiryThread.dto.response.InquiryThreadCreateResponseDto;
import yegam.supportservice.inquiryThread.dto.response.InquiryThreadResponseDto;
import yegam.supportservice.inquiryThread.entity.InquiryThread;

@Component
public class InquiryThreadMapper {

  public InquiryThreadResponseDto toInquiryThreadResponseDto(InquiryThread thread) {
    if (thread == null) return null;
    return InquiryThreadResponseDto.builder()
        .senderId(thread.getSenderId())
        .senderRole(thread.getSenderRole().name())
        .content(thread.getContent())
        .createdAt(thread.getCreatedAt())
        .build();
  }

  public InquiryThreadCreateResponseDto toInquiryThreadCreateResponseDto(InquiryThread thread) {
    if (thread == null) return null;
    return InquiryThreadCreateResponseDto.builder()
        .message("대화가 등록되었습니다.")
        .createdAt(thread.getCreatedAt())
        .build();
  }
}
