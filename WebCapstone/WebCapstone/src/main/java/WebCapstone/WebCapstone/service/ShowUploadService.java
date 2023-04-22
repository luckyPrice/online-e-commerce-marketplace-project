package WebCapstone.WebCapstone.service;

import WebCapstone.WebCapstone.DTO.*;
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

import java.util.*;

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
                    .detailcategory(uploadEntities.get(i).getDetailcategory())
                    .itemprice(uploadEntities.get(i).getItemprice())
                    .title(uploadEntities.get(i).getTitle())
                    .maintext(uploadEntities.get(i).getMaintext())
                    .URL(uploadEntities.get(i).getURL())
                    .view(uploadEntities.get(i).getView())
                    .favor(uploadEntities.get(i).getFavor())
                    .uploadtime(uploadEntities.get(i).getUploadtime())
                    .purpose(uploadEntities.get(i).getPurpose())
                    .status(uploadEntities.get(i).getStatus())
                    .build();
            uploadDTOS.add(uploadDTO);
        }

        return uploadDTOS;

    }



    public UploadDTO ShowUploadDetail(ItemIDDTO itemIDDTO) {
        UploadEntity uploadEntity = uploadRepository.findByItemid(itemIDDTO.getItemid());
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
                    .detailcategory(uploadEntity.getDetailcategory())
                    .itemprice(uploadEntity.getItemprice())
                    .title(uploadEntity.getTitle())
                    .maintext(uploadEntity.getMaintext())
                    .URL(uploadEntity.getURL())
                    .view(uploadEntity.getView())
                    .favor(uploadEntity.getFavor())
                    .uploadtime(uploadEntity.getUploadtime())
                    .favorcheck(favorcheck)
                    .purpose(uploadEntity.getPurpose())
                    .status(uploadEntity.getStatus())
                    .build();
            return uploadDTO;
        }
        return null;
    }

    public UploadDTO OrderUploadDetail(DetailDTO detailDTO) {
        UploadEntity uploadEntity = uploadRepository.findByMemberidAndTitle(detailDTO.getSeller(), detailDTO.getObject());

        if(uploadEntity != null){
            UploadDTO uploadDTO = UploadDTO.builder().memberid(uploadEntity.getMemberid())
                    .itemid(uploadEntity.getItemid())
                    .itemname(uploadEntity.getItemname())
                    .category(uploadEntity.getCategory())
                    .detailcategory(uploadEntity.getDetailcategory())
                    .itemprice(uploadEntity.getItemprice())
                    .title(uploadEntity.getTitle())
                    .maintext(uploadEntity.getMaintext())
                    .URL(uploadEntity.getURL())
                    .view(uploadEntity.getView())
                    .favor(uploadEntity.getFavor())
                    .uploadtime(uploadEntity.getUploadtime())
                    .favorcheck(true)
                    .purpose(uploadEntity.getPurpose())
                    .status(uploadEntity.getStatus())
                    .build();
            return uploadDTO;
        }
        return null;
    }

    public void deleteFavor(ItemIDDTO itemIDDTO){
        System.out.println(itemIDDTO);
        UploadEntity uploadEntity = uploadRepository.findByItemid(itemIDDTO.getItemid());
        FavorEntity favorEntity = favorRepository.findBySenduserAndReceiveuserAndTitle(itemIDDTO.getCurrentuser(),
                uploadEntity.getMemberid(), uploadEntity.getTitle());
        System.out.println(favorEntity);
        if(favorEntity!=null){
            favorRepository.deleteById(favorEntity.getId());
            System.out.println("input");
        }

    }

    public List<UploadDTO> favorUpload(FavorDTO favorDTO){
        System.out.println(favorDTO);
        List<FavorEntity> favorEntity = favorRepository.findAll();
        List<UploadDTO> uploadDTOS = new ArrayList<>();
        for(var i = 0 ; i < favorRepository.count(); i++){
            if(Objects.equals(favorEntity.get(i).getSenduser(), favorDTO.getNickname())){
                UploadEntity uploadEntity = uploadRepository.findByMemberidAndTitle(favorEntity.get(i).getReceiveuser(), favorEntity.get(i).getTitle());
                UploadDTO uploadDTO = UploadDTO.builder().memberid(uploadEntity.getMemberid())
                        .itemid(uploadEntity.getItemid())
                        .itemname(uploadEntity.getItemname())
                        .category(uploadEntity.getCategory())
                        .detailcategory(uploadEntity.getDetailcategory())
                        .itemprice(uploadEntity.getItemprice())
                        .title(uploadEntity.getTitle())
                        .maintext(uploadEntity.getMaintext())
                        .URL(uploadEntity.getURL())
                        .view(uploadEntity.getView())
                        .favor(uploadEntity.getFavor())
                        .uploadtime(uploadEntity.getUploadtime())
                        .favorcheck(false)
                        .purpose(uploadEntity.getPurpose())
                        .status(uploadEntity.getStatus())
                        .build();
                System.out.println("업로드" + uploadDTO);
                uploadDTOS.add(uploadDTO);
            }
        }
        return uploadDTOS;

    }

    public Set<UploadDTO> recommendUpload(ItemIDDTO itemIDDTO){
        List<UploadEntity> uploadEntities = uploadRepository.findByCategory(itemIDDTO.getCurrentuser());
        System.out.println(uploadEntities.size());
        int num = 0;
        Set<UploadDTO> uploadDTOS = new HashSet<>();



        if(uploadEntities.size() > 9) {

            while (uploadDTOS.size() < 9) {

                num = (int) (Math.random() * (uploadEntities.size()));
                if(num != itemIDDTO.getItemid()){ // 자기 자신은 추천하면 안됨
                    UploadDTO uploadDTO = UploadDTO.builder().memberid(uploadEntities.get(num).getMemberid())
                            .itemid(uploadEntities.get(num).getItemid())
                            .itemname(uploadEntities.get(num).getItemname())
                            .category(uploadEntities.get(num).getCategory())
                            .detailcategory(uploadEntities.get(num).getDetailcategory())
                            .itemprice(uploadEntities.get(num).getItemprice())
                            .title(uploadEntities.get(num).getTitle())
                            .maintext(uploadEntities.get(num).getMaintext())
                            .URL(uploadEntities.get(num).getURL())
                            .view(uploadEntities.get(num).getView())
                            .favor(uploadEntities.get(num).getFavor())
                            .uploadtime(uploadEntities.get(num).getUploadtime())
                            .favorcheck(false)
                            .purpose(uploadEntities.get(num).getPurpose())
                            .status(uploadEntities.get(num).getStatus())
                            .build();
                    uploadDTOS.add(uploadDTO);
                }


            }

        }
        else {
            for(var i = 0 ; i < uploadEntities.size(); i++) {
                if(itemIDDTO.getItemid() != uploadEntities.get(i).getItemid()){
                    UploadDTO uploadDTO = UploadDTO.builder().memberid(uploadEntities.get(i).getMemberid())
                            .itemid(uploadEntities.get(i).getItemid())
                            .itemname(uploadEntities.get(i).getItemname())
                            .category(uploadEntities.get(i).getCategory())
                            .detailcategory(uploadEntities.get(i).getDetailcategory())
                            .itemprice(uploadEntities.get(i).getItemprice())
                            .title(uploadEntities.get(i).getTitle())
                            .maintext(uploadEntities.get(i).getMaintext())
                            .URL(uploadEntities.get(i).getURL())
                            .view(uploadEntities.get(i).getView())
                            .favor(uploadEntities.get(i).getFavor())
                            .uploadtime(uploadEntities.get(i).getUploadtime())
                            .favorcheck(false)
                            .purpose(uploadEntities.get(i).getPurpose())
                            .status(uploadEntities.get(i).getStatus())
                            .build();
                    uploadDTOS.add(uploadDTO);
                }

            }
        }
            System.out.println(uploadDTOS);
            return uploadDTOS;





    }
}