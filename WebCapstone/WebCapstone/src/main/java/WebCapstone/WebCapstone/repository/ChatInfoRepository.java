package WebCapstone.WebCapstone.repository;

import WebCapstone.WebCapstone.entity.ChatEntity;
import WebCapstone.WebCapstone.entity.ChatInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ChatInfoRepository extends JpaRepository<ChatInfo, String> {

    public ChatInfo findBySenduserAndReceiveuserAndChattitle(String senduser, String receiveuser, String chattitle);

    public ChatInfo findByReceiveuser(String receiveuser);

    @Transactional
    public void deleteBySenduserAndChattitle(String senduser, String chattitle);

    @Transactional
    public void deleteByReceiveuserAndChattitle(String receiveuser, String chattitle);
}
