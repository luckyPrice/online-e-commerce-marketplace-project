package WebCapstone.WebCapstone.repository;

import WebCapstone.WebCapstone.entity.ChatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends JpaRepository<ChatEntity, String> {

}
