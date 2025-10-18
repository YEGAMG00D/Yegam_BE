package yegam.placeservice.domain.place.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import yegam.placeservice.domain.place.entity.PlaceSummary;

import java.util.List;

public interface PlaceSummaryRepository extends JpaRepository<PlaceSummary, Long> {

  Page<PlaceSummary> findByIsDeletedFalse(Pageable pageable);

  Page<PlaceSummary> findByTypeAndIsDeletedFalse(String type, Pageable pageable);

  Page<PlaceSummary> findByNameContainingOrAddressContainingAndIsDeletedFalse(String name, String address, Pageable pageable);

  @Query(value = """
      SELECT p.*, 
        (6371 * acos(cos(radians(:lat)) * cos(radians(p.lat)) *
        cos(radians(p.lng) - radians(:lng)) + sin(radians(:lat)) * sin(radians(p.lat)))) AS distance
      FROM place_summary p
      HAVING distance < :radiusKm
      ORDER BY distance ASC
      """, nativeQuery = true)
  List<PlaceSummary> findNearbyPlaces(Double lat, Double lng, Double radiusKm);
}
