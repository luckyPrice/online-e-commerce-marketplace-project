package WebCapstone.WebCapstone.repository;

import WebCapstone.WebCapstone.entity.ChatEntity;
import WebCapstone.WebCapstone.entity.ChatInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatInfoRepository extends JpaRepository<ChatInfo, String> {

    public ChatInfo findBySenduserAndReceiveuserAndChattitle(String senduser, String receiveuser, String chattitle);

    public ChatInfo findByReceiveuser(String receiveuser);
}
