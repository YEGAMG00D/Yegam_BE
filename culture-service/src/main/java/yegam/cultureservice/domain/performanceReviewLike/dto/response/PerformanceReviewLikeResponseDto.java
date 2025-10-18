package yegam.cultureservice.domain.performanceReviewLike.dto.response;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PerformanceReviewLikeResponseDto {
  private Long reviewId;
  private Long userId;
  private Boolean liked; // 현재 요청 유저가 좋아요 눌렀는지 여부
  private Integer totalLikes; // 전체 좋아요 수
  private LocalDateTime createdAt;
}
