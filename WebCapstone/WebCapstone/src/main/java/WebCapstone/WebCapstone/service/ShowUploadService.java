package WebCapstone.WebCapstone.service;

import WebCapstone.WebCapstone.DTO.ResponseDTO;
import WebCapstone.WebCapstone.DTO.SignInDTO;
import WebCapstone.WebCapstone.DTO.SignInResponseDTO;
import WebCapstone.WebCapstone.DTO.Upload_Order.UploadDTO;
import WebCapstone.WebCapstone.DTO.Upload_Order.UploadResponseDTO;
import WebCapstone.WebCapstone.entity.MemberEntity;
import WebCapstone.WebCapstone.entity.UploadEntity;
import WebCapstone.WebCapstone.repository.UploadRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//서버에 올린거 메인화면에 보여지게 만들어주는 서비스

@Service
public class ShowUploadService {
    @Autowired
    UploadRepository uploadRepository;


    public List<UploadDTO> ShowUpload(){
        List<UploadDTO> uploadDTOS = new ArrayList<>();
        List<UploadEntity> uploadEntities = uploadRepository.findAll();
        for(var i = 0 ; i < uploadRepository.count(); i++){
            UploadDTO uploadDTO = UploadDTO.builder().memberid(uploadEntities.get(i).getMemberid())
                    .itemid(uploadEntities.get(i).getItemid())
                    .itemname(uploadEntities.get(i).getItemname())
                    .category(uploadEntities.get(i).getCategory())
                    .itemprice(uploadEntities.get(i).getItemprice())
                    .title(uploadEntities.get(i).getTitle())
                    .maintext(uploadEntities.get(i).getMaintext())
                    .URL(uploadEntities.get(i).getURL())
                    .build();
            uploadDTOS.add(uploadDTO);
        }

        return uploadDTOS;

    }

    public UploadDTO ShowUploadDetail(int itemid) {
        UploadEntity uploadEntity = uploadRepository.findByitemid(itemid);
        if(uploadEntity != null){
            UploadDTO uploadDTO = UploadDTO.builder().memberid(uploadEntity.getMemberid())
                    .itemid(uploadEntity.getItemid())
                    .itemname(uploadEntity.getItemname())
                    .category(uploadEntity.getCategory())
                    .itemprice(uploadEntity.getItemprice())
                    .title(uploadEntity.getTitle())
                    .maintext(uploadEntity.getMaintext())
                    .URL(uploadEntity.getURL())
                    .build();
            return uploadDTO;
        }
        return null;
    }
}