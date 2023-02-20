package WebCapstone.WebCapstone.service;

import WebCapstone.WebCapstone.DTO.ResponseDTO;
import WebCapstone.WebCapstone.DTO.Upload_Order.UploadDTO;
import WebCapstone.WebCapstone.DTO.Upload_Order.UploadResponseDTO;
import WebCapstone.WebCapstone.Upload.Category;
import WebCapstone.WebCapstone.Upload.Upload;
import WebCapstone.WebCapstone.entity.MemberEntity;
import WebCapstone.WebCapstone.repository.MemberRepository;
import WebCapstone.WebCapstone.repository.UploadRepository;
import WebCapstone.WebCapstone.security.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UploadService {

    @Autowired
    UploadRepository uploadRepository;

    @Autowired
    TokenProvider tokenProvider;

    public ResponseDTO<UploadResponseDTO> Upload(UploadDTO dto){
        String memberid = dto.getMemberid();
        String category = dto.getCategory();
        String itemname = dto.getItemname();
        String maintext = dto.getMaintext();
        String title = dto.getTitle();
        int itemprice = dto.getItemprice();
        int itemid = 1;


        try{
            while(uploadRepository.findByitemid(itemid)!=null){
                itemid++;
            }
            dto.setItemid(itemid);
            //upload = uploadRepository.findByid(itemid);

        }catch(Exception e){
            return ResponseDTO.setFailed("업로드한 상품의 고유코드에 문제가 생겼습니다.");
        }

        System.out.println(dto.toString());


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

        Upload upload = new Upload(dto);
        System.out.println("ok");


        try{
            uploadRepository.save(upload);
        }catch(Exception e){
            return ResponseDTO.setFailed("Data Base error");
        }




        return ResponseDTO.setSuccess("상품이 업로드 되었습니다.", null);
    }
}