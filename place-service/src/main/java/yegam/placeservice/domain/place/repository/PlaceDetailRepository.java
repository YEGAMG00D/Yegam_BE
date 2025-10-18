package yegam.placeservice.domain.place.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yegam.placeservice.domain.place.entity.PlaceDetail;
import java.util.Optional;

public interface PlaceDetailRepository extends JpaRepository<PlaceDetail, Long> {
  Optional<PlaceDetail> findByMt10id(String mt10id);
}
