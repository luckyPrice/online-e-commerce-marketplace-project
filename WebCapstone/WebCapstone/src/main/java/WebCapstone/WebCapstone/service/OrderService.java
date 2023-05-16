package WebCapstone.WebCapstone.service;


import WebCapstone.WebCapstone.DTO.DetailDTO;
import WebCapstone.WebCapstone.DTO.NicknameDTO;
import WebCapstone.WebCapstone.DTO.OrderDTO;
import WebCapstone.WebCapstone.entity.OrderEntity;
import WebCapstone.WebCapstone.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.FieldPosition;
import java.text.SimpleDateFormat;
import java.util.*;

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
                    ,orderDTO.getURL(), orderDTO.getAddress(), stringBuffer.toString()," ", 1);
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
        if(orderEntity.getStep() == 3){
            StringBuffer stringBuffer = new StringBuffer();
            Date now = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
            simpleDateFormat.format(now, stringBuffer, new FieldPosition(0));
            orderEntity.setReceivedate(stringBuffer.toString());
        }
        System.out.println(orderEntity);
        orderRepository.save(orderEntity);

        return orderEntity;



    }

    public List<OrderEntity> getAllOrder(){
        List<OrderEntity> orderEntities = orderRepository.findAll();
        return orderEntities;
    }


    public int orderCount(NicknameDTO nicknameDTO){
        List<OrderEntity> orderEntities = orderRepository.findAll();
        var count = 0;
        if(orderEntities != null){
            for(var i = 0; i < orderEntities.size(); i++){
                if(Objects.equals(orderEntities.get(i).getBuyer(), nicknameDTO.getNickname()) || Objects.equals(orderEntities.get(i).getSeller(), nicknameDTO.getNickname())){
                    count++;
                }
            }
        }
        return count;
    }



    public void deleteOrder(DetailDTO detailDTO){
        OrderEntity orderEntity = orderRepository.findByBuyerAndSellerAndObject(detailDTO.getBuyer(), detailDTO.getSeller(), detailDTO.getObject());

        System.out.println(orderEntity);
        if(orderEntity != null){
            orderRepository.deleteByBuyerAndSellerAndObject(detailDTO.getBuyer(), detailDTO.getSeller(), detailDTO.getObject());
        }
    }

    public int checktime(DetailDTO detailDTO){
        OrderEntity orderEntity = orderRepository.findByBuyerAndSellerAndObject(detailDTO.getBuyer(), detailDTO.getSeller(), detailDTO.getObject());
        StringBuffer stringBuffer = new StringBuffer();
        Date now = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
        simpleDateFormat.format(now, stringBuffer, new FieldPosition(0));


        String str1[];
        String str2[];
        str1 = orderEntity.getReceivedate().split("/");
        str2 = stringBuffer.toString().split("/");
        System.out.println(Arrays.toString(str1));
        System.out.println(Arrays.toString(str2));
        String str3[];
        String str4[];
        str3 = str1[2].split(" ");
        str4 = str2[2].split(" ");
        int orderyear = Integer.parseInt(str3[0]);
        int nowyear = Integer.parseInt(str4[0]);
        int ordermonth = Integer.parseInt(str1[0]);
        int nowmonth = Integer.parseInt(str2[0]);
        int orderday = Integer.parseInt(str1[1]);
        int nowday = Integer.parseInt(str2[1]);
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        c1.set(orderyear, ordermonth, orderday);
        c2.set(nowyear, nowmonth, nowday);
        long d1,d2;
        d1=c1.getTimeInMillis();
        d2=c2.getTimeInMillis();

        int days =(int)((d2-d1)/(1000*60*60*24));
        System.out.println("날짜 차이는 " + days);



        if(days >= 2){
            System.out.println("2일이 지났습니다");
        }


    return days;



        //System.out.println(now.getTime() - orderEntity.getDate());


    }
}
