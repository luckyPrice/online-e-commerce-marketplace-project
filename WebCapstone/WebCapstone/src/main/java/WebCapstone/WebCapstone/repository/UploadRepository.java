package WebCapstone.WebCapstone.repository;

import WebCapstone.WebCapstone.Upload.Upload;
import WebCapstone.WebCapstone.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UploadRepository extends JpaRepository<Upload, String> {

    public Upload findByitemid(int itemid);
}