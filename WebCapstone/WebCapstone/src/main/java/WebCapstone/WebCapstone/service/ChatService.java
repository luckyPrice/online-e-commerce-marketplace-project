package WebCapstone.WebCapstone.service;


import WebCapstone.WebCapstone.DTO.ChatDTO;
import WebCapstone.WebCapstone.entity.ChatEntity;
import WebCapstone.WebCapstone.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ChatService {



    @Autowired
    ChatRepository chatRepository;








    public ChatDTO chatsave(ChatDTO chatDTO){
        ChatEntity chatEntity = new ChatEntity(UUID.randomUUID().toString(), chatDTO.getSenduser(), chatDTO.getReceiveuser(),
                chatDTO.getMessage(), chatDTO.getDate());
        System.out.println(chatEntity);
        chatRepository.save(chatEntity);
        return chatDTO;
    }


    public List<ChatDTO> getAllMessage(ChatDTO chatDTO){
        List<ChatDTO> chatDTOS = new ArrayList<>();

        List<ChatEntity> chat = chatRepository.findAll();
        for(var i = 0 ; i < chatRepository.count(); i ++){
            if(Objects.equals(chat.get(i).getSenduser(), chatDTO.getSenduser()) && Objects.equals(chat.get(i).getReceiveuser(), chatDTO.getReceiveuser())){
                System.out.println("inputmessage");
                ChatDTO chatDTO1 = ChatDTO.builder().senduser(chat.get(i).getSenduser()).receiveuser(chat.get(i).getReceiveuser()).message(chat.get(i).getMessage())
                                .date(chat.get(i).getDate()).build();
                chatDTOS.add(chatDTO1);
            }
            else if(Objects.equals(chat.get(i).getSenduser(), chatDTO.getReceiveuser()) && Objects.equals(chat.get(i).getSenduser(), chatDTO.getReceiveuser())){
                System.out.println("inputmessage");
                ChatDTO chatDTO1 = ChatDTO.builder().senduser(chat.get(i).getSenduser()).receiveuser(chat.get(i).getReceiveuser()).message(chat.get(i).getMessage())
                        .date(chat.get(i).getDate()).build();
                chatDTOS.add(chatDTO1);
            }
        }


        //List<chatRoom2DTO> chatRoom2DTOList = new ArrayList<>();
        //chatRoom2DTOList.add(chatRoomRepository.findByroomname(chatRoomDTO.getRoomId()));
        return chatDTOS;
        //chatRoomDTOS.add(chatRoomRepository.findBySellUser(username).getChatRoomDTO());
        //chatRoomDTOS.add(chatRoomRepository.findByBuyUser(username).getChatRoomDTO());





    }

    public Set<String> findMyChatRoom(String usernickname){
        Set <String> stringList = new HashSet<>();
        List<ChatEntity> chat = chatRepository.findAll();
        for(var i = 0 ; i < chatRepository.count() ; i++){
            if(Objects.equals(chat.get(i).getSenduser(),usernickname)){
                stringList.add(chat.get(i).getReceiveuser());
            }
            else if(Objects.equals(chat.get(i).getReceiveuser(), usernickname)){
                stringList.add(chat.get(i).getSenduser());
            }
        }
        System.out.println(stringList);
        return stringList;
    }



}
