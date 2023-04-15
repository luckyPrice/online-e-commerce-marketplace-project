package WebCapstone.WebCapstone.service;


import WebCapstone.WebCapstone.DTO.DetailDTO;
import WebCapstone.WebCapstone.DTO.OrderDTO;
import WebCapstone.WebCapstone.entity.OrderEntity;
import WebCapstone.WebCapstone.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    public OrderEntity createOrder(OrderDTO orderDTO){
        int id = 1;
        try{
            while(orderRepository.findByid(id)!=null){
                id++;
            }
        }
        catch(Exception e){
            System.out.println("오류");
        }
        if (orderRepository.findByBuyerAndSellerAndObject(orderDTO.getBuyer(), orderDTO.getSeller(), orderDTO.getObject()) == null){
            OrderEntity orderEntity = new OrderEntity(id, orderDTO.getSeller(), orderDTO.getBuyer(), orderDTO.getObject(), orderDTO.getPrice()
                    ,orderDTO.getURL(), orderDTO.getAddress(), 1);
            orderRepository.save(orderEntity);
            return orderEntity;
        }

        return null;
    }

    public OrderEntity getOrder(DetailDTO detailDTO){
        OrderEntity orderEntity = orderRepository.findByBuyerAndSellerAndObject(detailDTO.getBuyer(), detailDTO.getSeller(), detailDTO.getObject());
        System.out.println(orderEntity);
        if(orderEntity != null){
            return orderEntity;
        }
        return null;
    }

    public OrderEntity Finishbuyer(DetailDTO detailDTO){
        OrderEntity orderEntity = orderRepository.findByBuyerAndSellerAndObject(detailDTO.getBuyer(), detailDTO.getSeller(), detailDTO.getObject());
        System.out.println(orderEntity);
        if(orderEntity != null){
            orderEntity.setStep(3);
            orderRepository.save(orderEntity);
            return orderEntity;
        }
        return null;
    }

    public OrderEntity Finishseller(DetailDTO detailDTO){
        OrderEntity orderEntity = orderRepository.findByBuyerAndSellerAndObject(detailDTO.getBuyer(), detailDTO.getSeller(), detailDTO.getObject());
        System.out.println(orderEntity);
        if(orderEntity != null){
            orderEntity.setStep(2);
            orderRepository.save(orderEntity);
            return orderEntity;
        }
        return null;
    }

    public void deleteOrder(OrderDTO orderDTO){

    }
}
