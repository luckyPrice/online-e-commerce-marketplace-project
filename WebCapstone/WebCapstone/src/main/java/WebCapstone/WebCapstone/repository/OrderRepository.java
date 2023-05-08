package WebCapstone.WebCapstone.repository;

import WebCapstone.WebCapstone.entity.ChatEntity;
import WebCapstone.WebCapstone.entity.OrderEntity;
import WebCapstone.WebCapstone.entity.UploadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface OrderRepository extends JpaRepository<OrderEntity, String> {

    public OrderEntity findByid(int id);

    public OrderEntity findByBuyerAndSellerAndObject(String buyer, String seller, String object);

    @Transactional
    public void deleteByBuyerAndSellerAndObject(String buyer, String seller, String object);
}
