package yegam.chatservice.domain.message.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yegam.chatservice.domain.message.entity.Message;
import yegam.chatservice.domain.room.entity.Room;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

  List<Message> findAllByRoom(Room room);
  List<Message> findAllByUserId(Long userId);
}
