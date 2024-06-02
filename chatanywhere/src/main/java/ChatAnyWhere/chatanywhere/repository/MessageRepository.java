package ChatAnyWhere.chatanywhere.repository;

import ChatAnyWhere.chatanywhere.models.Message;
import ChatAnyWhere.chatanywhere.models.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {

    List<Message> findBySenderAndReceiver(User sender,User receiver);
}
