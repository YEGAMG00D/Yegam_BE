package yegam.chatservice.domain.room.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yegam.chatservice.domain.room.entity.Room;

import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
  Optional<Room> findByPerformanceId(Long performanceId);
}
