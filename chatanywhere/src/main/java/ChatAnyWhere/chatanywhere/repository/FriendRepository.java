package ChatAnyWhere.chatanywhere.repository;

import ChatAnyWhere.chatanywhere.models.Friend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendRepository extends JpaRepository<Friend,Integer> {
    Friend findByUsername(String username);
}
