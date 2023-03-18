package WebCapstone.WebCapstone.controller;

import WebCapstone.WebCapstone.DTO.*;
import WebCapstone.WebCapstone.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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



}
