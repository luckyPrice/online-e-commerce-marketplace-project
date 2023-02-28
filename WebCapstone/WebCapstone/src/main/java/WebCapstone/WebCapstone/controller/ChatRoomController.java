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
        System.out.println(chatDTO);
        return chatService.chatsave(chatDTO);
    }

    @Async
    @PostMapping("/getmessage")
    @ResponseBody
    public List<ChatDTO> getAllMessage(@RequestBody ChatDTO chatDTO){
        System.out.println(chatDTO);


        System.out.println("---------------------------------");
        //chatService.getAllMessage(chatRoomDTO);
        return chatService.getAllMessage(chatDTO);
    }

    @Async
    @PostMapping("/getchatroom")
    @ResponseBody
    public Set<MyChatDTO> getMyChatRoomInfo(@RequestBody NicknameDTO nickname){

        return chatService.findMyChatRoom(nickname.getNickname());
    }



    /*@PostMapping("/find/{username}")
    @ResponseBody
    public List<ChatRoomDTO> roomInfo(
            Model model,
            @PathVariable String username){
        return chatService.findBy
    }
    )*/



}
