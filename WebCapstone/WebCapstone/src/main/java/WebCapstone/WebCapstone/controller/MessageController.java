package WebCapstone.WebCapstone.controller;

import WebCapstone.WebCapstone.DTO.ChatDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequiredArgsConstructor
public class MessageController {




    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;




    @Async
    @MessageMapping("/chat")
    public void receivePrivate(@Payload ChatDTO chat) {

        System.out.println("in1");
        System.out.println(chat);
        simpMessagingTemplate.convertAndSend("/private/message/" + chat.getReceiveuser() , chat); // 룸아이디 뭐를 가져오지?

    }




}
