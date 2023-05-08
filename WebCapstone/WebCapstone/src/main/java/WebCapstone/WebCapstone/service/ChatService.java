package WebCapstone.WebCapstone.service;


import WebCapstone.WebCapstone.DTO.ChatDTO;
import WebCapstone.WebCapstone.DTO.ItemIDDTO;
import WebCapstone.WebCapstone.DTO.MyChatDTO;
import WebCapstone.WebCapstone.DTO.NicknameDTO;
import WebCapstone.WebCapstone.entity.ChatEntity;
import WebCapstone.WebCapstone.entity.ChatInfo;
import WebCapstone.WebCapstone.entity.UploadEntity;
import WebCapstone.WebCapstone.repository.ChatInfoRepository;
import WebCapstone.WebCapstone.repository.ChatRepository;
import WebCapstone.WebCapstone.repository.UploadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ChatService {



    @Autowired
    ChatRepository chatRepository;

    @Autowired
    ChatInfoRepository chatInfoRepository;

    @Autowired
    UploadRepository uploadRepository;


    public ChatDTO chatsave(ChatDTO chatDTO){
        int id = 1;
        try{
            while(chatRepository.findByid(id)!=null){
                id++;
            }
        }
        catch(Exception e){
            System.out.println("오류");
        }
        System.out.println(chatDTO);
        List<ChatEntity> chat = chatRepository.findAll();
        ChatInfo chatInfo = chatInfoRepository.findBySenduserAndReceiveuserAndChattitle(chatDTO.getSenduser(), chatDTO.getReceiveuser(), chatDTO.getChattitle());
        System.out.println(chatInfo);
        if(chatInfo != null){
            int temp = chatInfo.getNotread() + 1;
            chatInfo.setNotread(temp);
            chatInfoRepository.save(chatInfo);
        }
        else{
            ChatInfo chatInfo1 = new ChatInfo(UUID.randomUUID().toString(), chatDTO.getSenduser(), chatDTO.getReceiveuser(), chatDTO.getChattitle(), 1);
            ChatInfo chatInfo2 = new ChatInfo(UUID.randomUUID().toString(), chatDTO.getReceiveuser(), chatDTO.getSenduser(), chatDTO.getChattitle(), 0);
            chatInfoRepository.save(chatInfo1);
            chatInfoRepository.save(chatInfo2);
        }

        ChatEntity chatEntity = new ChatEntity(id, chatDTO.getSenduser(), chatDTO.getReceiveuser(),
                chatDTO.getChattitle(),
                chatDTO.getMessage(), chatDTO.getDate(), 1, chatDTO.getType());
        chatRepository.save(chatEntity);




        return chatDTO;
    }


    public List<ChatDTO> getAllMessage(ChatDTO chatDTO){
        List<ChatDTO> chatDTOS = new ArrayList<>();

        //ChatRequest pageRequest = PageRequest.of(0, chatRepository.count(), Sort.by("id").ascending());
        List<ChatEntity> chat = chatRepository.findAll(Sort.by("date"));
        System.out.println(chatDTO);
        System.out.println("chat" + chat);
        ChatInfo chatInfo = chatInfoRepository.findBySenduserAndReceiveuserAndChattitle(chatDTO.getReceiveuser(), chatDTO.getSenduser(), chatDTO.getChattitle());
        if(chatInfo != null){
            chatInfo.setNotread(0);
        }

        chatInfoRepository.save(chatInfo);
        System.out.println(chatRepository.count());
        for(var i = 0 ; i < chatRepository.count(); i ++){
            System.out.println(chat.get(i));
            if(Objects.equals(chat.get(i).getSenduser(), chatDTO.getSenduser()) && Objects.equals(chat.get(i).getReceiveuser(), chatDTO.getReceiveuser()) && Objects.equals(chat.get(i).getChattitle(), chatDTO.getChattitle())){
                ChatDTO chatDTO1 = ChatDTO.builder().senduser(chat.get(i).getSenduser()).receiveuser(chat.get(i).getReceiveuser()).chattitle(chat.get(i).getChattitle()).message(chat.get(i).getMessage())
                                .date(chat.get(i).getDate()).notread(chat.get(i).getNotread()).type(chat.get(i).getType()).build();
                chatDTOS.add(chatDTO1);
            }
            else if(Objects.equals(chat.get(i).getSenduser(), chatDTO.getReceiveuser()) && Objects.equals(chat.get(i).getSenduser(), chatDTO.getReceiveuser()) && Objects.equals(chat.get(i).getChattitle(), chatDTO.getChattitle())){
                ChatDTO chatDTO1 = ChatDTO.builder().senduser(chat.get(i).getSenduser()).receiveuser(chat.get(i).getReceiveuser()).chattitle(chat.get(i).getChattitle()).message(chat.get(i).getMessage())
                        .date(chat.get(i).getDate()).notread(0).type(chat.get(i).getType()).build();
                chatDTOS.add(chatDTO1);
                chat.get(i).setNotread(0);
                chatRepository.save(chat.get(i));
            }
        }

        return chatDTOS;





    }

    public Set<MyChatDTO> findMyChatRoom(String usernickname){
        Set <MyChatDTO> myChatDTOS = new HashSet<>();
        List<ChatInfo> chatInfos = chatInfoRepository.findAll();
        var count = 0;
        for(var i = 0 ; i < chatInfoRepository.count() ; i++){

            if(Objects.equals(chatInfos.get(i).getReceiveuser(), usernickname)){
                MyChatDTO myChatDTO = MyChatDTO.builder().nickname(chatInfos.get(i).getSenduser()).chattitle(chatInfos.get(i).getChattitle()).notread(chatInfos.get(i).getNotread())
                        .build();
                myChatDTOS.add(myChatDTO);
            }
        }
        return myChatDTOS;
    }

    public int checkChatCount(String usernickname){
        int count = 0;
        List<ChatInfo> chatInfos = chatInfoRepository.findAll();
        for(var i = 0 ; i < chatInfoRepository.count() ; i++) {
            if(Objects.equals(chatInfos.get(i).getReceiveuser(), usernickname)){
                count += chatInfos.get(i).getNotread();
            }
        }
        return count;
    }


    public void deleteChat(ItemIDDTO itemIDDTO){
        System.out.println(itemIDDTO.getItemid());
        UploadEntity uploadEntity = uploadRepository.findByItemid(itemIDDTO.getItemid());
        System.out.println(uploadEntity);
        if(uploadEntity!=null){


            chatRepository.deleteBySenduserAndChattitle(uploadEntity.getMemberid(), uploadEntity.getTitle());
            chatRepository.deleteByReceiveuserAndChattitle(uploadEntity.getMemberid(), uploadEntity.getTitle());
            chatInfoRepository.deleteByReceiveuserAndChattitle(uploadEntity.getMemberid(), uploadEntity.getTitle());
            chatInfoRepository.deleteByReceiveuserAndChattitle(uploadEntity.getMemberid(), uploadEntity.getTitle());
            System.out.println("ok");
        }
    }

    public List<ChatDTO> getAlarm(String nickname){
        List<ChatDTO> chatDTOS = new ArrayList<>();
        List<ChatInfo> chatInfos = chatInfoRepository.findAll();
        List<ChatEntity> chatEntities = chatRepository.findAll(Sort.by("date"));
        System.out.println("1" + chatEntities);
        System.out.println("2" + chatInfos);
        int count = 0;
        for(var i = 0 ; i < chatInfoRepository.count(); i++){
            if(Objects.equals(chatInfos.get(i).getReceiveuser(), nickname)){
                count = count + chatInfos.get(i).getNotread();
            }

        }
        System.out.println("카운트는" + count);

        for(var i = 0 ; i < chatRepository.count(); i++){
            if(chatEntities.get(i).getNotread() == 1 && count > 0){
                ChatDTO chatDTO = ChatDTO.builder().senduser(chatEntities.get(i).getSenduser()).receiveuser(chatEntities.get(i).getReceiveuser()).chattitle(chatEntities.get(i).getChattitle()).message(chatEntities.get(i).getMessage())
                        .date(chatEntities.get(i).getDate()).notread(1).type(chatEntities.get(i).getType()).build();
                chatDTOS.add(chatDTO);
                count = count - 1;
            }
        }
        System.out.println("???" + chatDTOS);
    return chatDTOS;


    }


}
