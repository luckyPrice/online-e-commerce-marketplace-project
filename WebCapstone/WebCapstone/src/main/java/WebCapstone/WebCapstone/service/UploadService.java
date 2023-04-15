package WebCapstone.WebCapstone.service;

import WebCapstone.WebCapstone.DTO.FavorDTO;
import WebCapstone.WebCapstone.DTO.FavorRequestDTO;
import WebCapstone.WebCapstone.DTO.ItemIDDTO;
import WebCapstone.WebCapstone.DTO.ResponseDTO;
import WebCapstone.WebCapstone.DTO.Upload_Order.UploadDTO;
import WebCapstone.WebCapstone.DTO.Upload_Order.UploadResponseDTO;
import WebCapstone.WebCapstone.entity.FavorEntity;
import WebCapstone.WebCapstone.entity.UploadEntity;
import WebCapstone.WebCapstone.repository.ChatRepository;
import WebCapstone.WebCapstone.repository.FavorRepository;
import WebCapstone.WebCapstone.repository.UploadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.FieldPosition;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class UploadService {

    @Autowired
    UploadRepository uploadRepository;

    @Autowired
    FavorRepository favorRepository;

    @Autowired
    ChatRepository chatRepository;



    public ResponseDTO<UploadResponseDTO> Upload(UploadDTO dto){
        String memberid = dto.getMemberid();
        String category = dto.getCategory();
        String itemname = dto.getItemname();
        String maintext = dto.getMaintext();
        String title = dto.getTitle();
        int itemprice = dto.getItemprice();
        int itemid = 1;
        String detiaicategory = dto.getDetailcategory();
        String purpose = dto.getPurpose();
        String URL = dto.getURL();



        try{
            while(uploadRepository.findByItemid(itemid)!=null){
                itemid++;
            }
            dto.setItemid(itemid);
            //upload = uploadRepository.findByid(itemid);

        }catch(Exception e){
            return ResponseDTO.setFailed("업로드한 상품의 고유코드에 문제가 생겼습니다.");
        }



        try{
            //upload = uploadRepository.findByid(itemid);
            if(itemname.equals(null)==true){
                return ResponseDTO.setFailed("상품의 이름을 입력해주세요");
            }
            if(itemname.equals(null)==true){
                return ResponseDTO.setFailed("상품의 이름을 입력해주세요");
            }
        }catch(Exception e){
            return ResponseDTO.setFailed("서버 오류입니다.");
        }

        if(title.equals(null)==true){
            dto.setTitle("제목없음");
        }
        if(maintext.equals(null)==true){
            dto.setMaintext(" ");
        }

        UploadEntity upload = new UploadEntity(dto);


        try{
            uploadRepository.save(upload);
        }catch(Exception e){
            return ResponseDTO.setFailed("Data Base error");
        }




        return ResponseDTO.setSuccess("상품이 업로드 되었습니다.", null);
    }


    public void deleteUpload(ItemIDDTO itemIDDTO){
        uploadRepository.deleteByItemid(itemIDDTO.getItemid());


    }

    public void favorApply(FavorDTO favorDTO){
        UploadEntity uploadEntity = uploadRepository.findByItemid(favorDTO.getItemid());
        int count = 1;
        if(uploadEntity!=null){
            FavorEntity favor = favorRepository.findBySenduserAndReceiveuserAndTitle(favorDTO.getNickname(), uploadEntity.getMemberid(), uploadEntity.getTitle());
            if(favor != null){
                favorRepository.deleteById(favor.getId());
                uploadEntity.setFavor(uploadEntity.getFavor()-1); // 카운트 한개 감소
                uploadRepository.save(uploadEntity);
            }
            else{
                while(favorRepository.findByid(count)!=null){
                    count++;
                }

                StringBuffer stringBuffer = new StringBuffer();
                Date now = new Date();

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
                simpleDateFormat.format(now, stringBuffer, new FieldPosition(0));

                FavorRequestDTO favorRequestDTO = FavorRequestDTO.builder().id(count)
                        .senduser(favorDTO.getNickname())
                        .receiveuser(uploadEntity.getMemberid())
                        .title(uploadEntity.getTitle())
                        .time(stringBuffer.toString())
                        .build();
                FavorEntity favorEntity = new FavorEntity(favorRequestDTO);
                favorRepository.save(favorEntity);
                uploadEntity.setFavor(uploadEntity.getFavor()+1); // 카운트 한개 증가
                uploadRepository.save(uploadEntity);
            }

        }

    }
}