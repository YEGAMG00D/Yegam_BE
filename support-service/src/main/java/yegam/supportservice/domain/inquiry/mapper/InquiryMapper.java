package yegam.supportservice.domain.inquiry.mapper;

import org.springframework.stereotype.Component;
import yegam.supportservice.inquiry.dto.response.InquiryResponseDto;
import yegam.supportservice.inquiry.entity.Inquiry;

@Component
public class InquiryMapper {

  public InquiryResponseDto toInquiryResponseDto(Inquiry inquiry) {
    if (inquiry == null) return null;
    return InquiryResponseDto.builder()
        .inquiryId(inquiry.getId())
        .title(inquiry.getTitle())
        .content(inquiry.getContent())
        .status(inquiry.getStatus().name())
        .createdAt(inquiry.getCreatedAt())
        .build();
  }
}
