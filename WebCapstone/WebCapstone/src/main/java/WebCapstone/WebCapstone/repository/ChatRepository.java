package WebCapstone.WebCapstone.repository;

import WebCapstone.WebCapstone.entity.ChatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<ChatEntity, String> {

    public ChatEntity findByid(int id);
    @Transactional
    public void deleteBySenduserAndChattitle(String senduser, String chattitle);

    @Transactional
    public void deleteByReceiveuserAndChattitle(String receiveuser, String chattitle);
}
