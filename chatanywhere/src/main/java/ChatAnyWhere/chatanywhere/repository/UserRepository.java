package ChatAnyWhere.chatanywhere.repository;

import ChatAnyWhere.chatanywhere.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

public interface UserRepository extends JpaRepository<User,Integer> {
   User findByUsername(String username);
   boolean existsByUsername(String username);
}
