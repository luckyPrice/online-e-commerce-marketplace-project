package WebCapstone.WebCapstone.repository;

import WebCapstone.WebCapstone.DTO.SignupDTO;
import WebCapstone.WebCapstone.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, String> {

    public MemberEntity findByid(String id);



}
