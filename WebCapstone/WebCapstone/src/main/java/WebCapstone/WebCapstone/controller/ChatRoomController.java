package WebCapstone.WebCapstone.controller;

import WebCapstone.WebCapstone.DTO.*;
import WebCapstone.WebCapstone.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.FieldPosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Controller
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/room")
public class ChatRoomController {


    private final ChatService chatService;




    @Async
    @PostMapping("/create")
    @ResponseBody
    public ChatDTO saveChat(@RequestBody ChatDTO chatDTO){
        StringBuffer stringBuffer = new StringBuffer();
        Date now = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
        simpleDateFormat.format(now, stringBuffer, new FieldPosition(0));
        chatDTO.setDate(stringBuffer.toString());
        System.out.println("chatDto는" + chatDTO);
        return chatService.chatsave(chatDTO);
    }

    @Async
    @PostMapping("/getmessage")
    @ResponseBody
    public List<ChatDTO> getAllMessage(@RequestBody ChatDTO chatDTO){
        return chatService.getAllMessage(chatDTO);
    }

    @Async
    @PostMapping("/getchatroom")
    @ResponseBody
    public Set<MyChatDTO> getMyChatRoomInfo(@RequestBody NicknameDTO nickname){
        return chatService.findMyChatRoom(nickname.getNickname());
    }


    @PostMapping("/chatcount")
    @ResponseBody
    public int ChatCount(@RequestBody NicknameDTO nickname){
        return chatService.checkChatCount(nickname.getNickname());
    }

    @PostMapping("/getalarm")
    @ResponseBody
    public List<ChatDTO> getAlarm(@RequestBody NicknameDTO nicknameDTO){
        return chatService.getAlarm(nicknameDTO.getNickname());
    }



}
