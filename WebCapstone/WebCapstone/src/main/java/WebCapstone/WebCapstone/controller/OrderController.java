package WebCapstone.WebCapstone.controller;

import WebCapstone.WebCapstone.DTO.DetailDTO;
import WebCapstone.WebCapstone.DTO.OrderDTO;
import WebCapstone.WebCapstone.entity.OrderEntity;
import WebCapstone.WebCapstone.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {


    @Autowired
    OrderService orderService;
    @PostMapping("/ordercreate")
    @ResponseBody
    public OrderEntity Order(@RequestBody OrderDTO orderDTO){
        System.out.println(orderDTO);


        return orderService.createOrder(orderDTO);
        //return null;
    }

    @PostMapping("/orderget")
    @ResponseBody
    public OrderEntity getOrder(@RequestBody DetailDTO detailDTO){
        System.out.println(detailDTO);

        return orderService.getOrder(detailDTO);


    }

    @PostMapping("/finishBuyer")
    @ResponseBody
    public OrderEntity FinishBuyer(@RequestBody DetailDTO detailDTO){

        return orderService.Finishbuyer(detailDTO);
    }

    @PostMapping("/finishSeller")
    @ResponseBody
    public OrderEntity FinishSeller(@RequestBody DetailDTO detailDTO){

        return orderService.Finishseller(detailDTO);
    }

    @PostMapping("/orderchange")
    @ResponseBody
    public OrderEntity orderNext(@RequestBody DetailDTO detailDTO){

        return orderService.orderNext(detailDTO);

    }
}
