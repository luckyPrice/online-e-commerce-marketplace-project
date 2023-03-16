package WebCapstone.WebCapstone.repository;

import WebCapstone.WebCapstone.entity.MemberEntity;
import WebCapstone.WebCapstone.entity.UploadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UploadRepository extends JpaRepository<UploadEntity, String> {

    public UploadEntity findByitemid(int itemid);
    @Transactional
    public void deleteByItemid(int itemid);

}