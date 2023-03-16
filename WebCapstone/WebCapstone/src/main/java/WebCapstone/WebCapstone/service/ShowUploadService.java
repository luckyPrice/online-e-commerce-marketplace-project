package WebCapstone.WebCapstone.service;

import WebCapstone.WebCapstone.DTO.ItemIDDTO;
import WebCapstone.WebCapstone.DTO.ResponseDTO;
import WebCapstone.WebCapstone.DTO.SignInDTO;
import WebCapstone.WebCapstone.DTO.SignInResponseDTO;
import WebCapstone.WebCapstone.DTO.Upload_Order.UploadDTO;
import WebCapstone.WebCapstone.DTO.Upload_Order.UploadResponseDTO;
import WebCapstone.WebCapstone.entity.FavorEntity;
import WebCapstone.WebCapstone.entity.MemberEntity;
import WebCapstone.WebCapstone.entity.UploadEntity;
import WebCapstone.WebCapstone.repository.FavorRepository;
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

    @Autowired
    FavorRepository favorRepository;


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
                    .view(uploadEntities.get(i).getView())
                    .favor(uploadEntities.get(i).getFavor())
                    .uploadtime(uploadEntities.get(i).getUploadtime())
                    .build();
            uploadDTOS.add(uploadDTO);
        }

        return uploadDTOS;

    }

    public UploadDTO ShowUploadDetail(ItemIDDTO itemIDDTO) {
        UploadEntity uploadEntity = uploadRepository.findByitemid(itemIDDTO.getItemid());
        uploadEntity.setView(uploadEntity.getView()+1);
        uploadRepository.save(uploadEntity);
        boolean favorcheck = false;
        FavorEntity favor = favorRepository.findBySenduserAndReceiveuserAndTitle(itemIDDTO.getCurrentuser(), uploadEntity.getMemberid(), uploadEntity.getTitle());
        if(favor!=null){
            favorcheck = true;
        }
        if(uploadEntity != null){
            UploadDTO uploadDTO = UploadDTO.builder().memberid(uploadEntity.getMemberid())
                    .itemid(uploadEntity.getItemid())
                    .itemname(uploadEntity.getItemname())
                    .category(uploadEntity.getCategory())
                    .itemprice(uploadEntity.getItemprice())
                    .title(uploadEntity.getTitle())
                    .maintext(uploadEntity.getMaintext())
                    .URL(uploadEntity.getURL())
                    .view(uploadEntity.getView())
                    .favor(uploadEntity.getFavor())
                    .uploadtime(uploadEntity.getUploadtime())
                    .favorcheck(favorcheck)
                    .build();
            return uploadDTO;
        }
        return null;
    }
}