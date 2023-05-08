package WebCapstone.WebCapstone.service;


import WebCapstone.WebCapstone.DTO.DetailDTO;
import WebCapstone.WebCapstone.DTO.OrderDTO;
import WebCapstone.WebCapstone.entity.OrderEntity;
import WebCapstone.WebCapstone.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.FieldPosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
            StringBuffer stringBuffer = new StringBuffer();
            Date now = new Date();

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
            simpleDateFormat.format(now, stringBuffer, new FieldPosition(0));

            OrderEntity orderEntity = new OrderEntity(id, orderDTO.getSeller(), orderDTO.getBuyer(), orderDTO.getObject(), orderDTO.getPrice()
                    ,orderDTO.getURL(), orderDTO.getAddress(), stringBuffer.toString(), 1);
            System.out.println(orderEntity);
            orderRepository.save(orderEntity);
            return orderEntity;
        }

        return null;
    }

    public OrderEntity getOrder(DetailDTO detailDTO){
        OrderEntity orderEntity = orderRepository.findByBuyerAndSellerAndObject(detailDTO.getBuyer(), detailDTO.getSeller(), detailDTO.getObject());
        System.out.println(orderEntity);
        if(orderEntity == null){

            orderEntity = orderRepository.findByBuyerAndSellerAndObject(detailDTO.getSeller(), detailDTO.getBuyer(), detailDTO.getObject());
        }
        if(orderEntity != null){


                System.out.println("1");
                return orderEntity;


        }

        System.out.println("3");
        return null;
    }

    public OrderEntity orderNext(DetailDTO detailDTO){

        System.out.println("orderNext" + detailDTO);
        OrderEntity orderEntity = orderRepository.findByBuyerAndSellerAndObject(detailDTO.getBuyer(), detailDTO.getSeller(), detailDTO.getObject());
        orderEntity.setStep(orderEntity.getStep() + 1);
        System.out.println(orderEntity);
        orderRepository.save(orderEntity);

        return orderEntity;



    }

    public List<OrderEntity> getAllOrder(){
        List<OrderEntity> orderEntities = orderRepository.findAll();
        return orderEntities;
    }



    public void deleteOrder(DetailDTO detailDTO){
        OrderEntity orderEntity = orderRepository.findByBuyerAndSellerAndObject(detailDTO.getBuyer(), detailDTO.getSeller(), detailDTO.getObject());

        System.out.println(orderEntity);
        if(orderEntity != null){
            orderRepository.deleteByBuyerAndSellerAndObject(detailDTO.getBuyer(), detailDTO.getSeller(), detailDTO.getObject());
        }
    }
}
