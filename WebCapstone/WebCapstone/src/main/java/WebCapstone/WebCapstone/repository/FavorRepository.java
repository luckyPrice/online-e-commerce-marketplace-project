package WebCapstone.WebCapstone.repository;

import WebCapstone.WebCapstone.entity.ChatEntity;
import WebCapstone.WebCapstone.entity.FavorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface FavorRepository extends JpaRepository<FavorEntity, String> {
    public FavorEntity findByid(int id);

    public FavorEntity findBySenduserAndReceiveuserAndTitle(String senduser, String receiveuser, String title);

    @Transactional
    public void deleteById(int id);
}
