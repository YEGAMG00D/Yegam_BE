package yegam.placeservice.domain.placeReview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yegam.placeservice.domain.placeReview.entity.PlaceReview;

import java.util.List;
import java.util.Optional;

public interface PlaceReviewRepository extends JpaRepository<PlaceReview, Long> {

  List<PlaceReview> findByPlace_IdAndIsDeletedFalse(Long placeId);

  List<PlaceReview> findByUserIdAndIsDeletedFalse(Long userId);

  Optional<PlaceReview> findByPlaceReviewIdAndIsDeletedFalse(Long reviewId);
}
