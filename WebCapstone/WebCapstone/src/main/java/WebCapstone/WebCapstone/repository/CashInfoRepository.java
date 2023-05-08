package WebCapstone.WebCapstone.repository;

import WebCapstone.WebCapstone.entity.CashInfo;
import WebCapstone.WebCapstone.entity.MemberEntity;
import WebCapstone.WebCapstone.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CashInfoRepository extends JpaRepository<CashInfo, String> {

    public CashInfo findByid(int id);

    public List<CashInfo> findByNickname(String nickname);
}
