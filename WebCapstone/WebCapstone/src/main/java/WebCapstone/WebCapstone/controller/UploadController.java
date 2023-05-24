package WebCapstone.WebCapstone.controller;

import WebCapstone.WebCapstone.DTO.*;
import WebCapstone.WebCapstone.DTO.Upload_Order.UploadDTO;
import WebCapstone.WebCapstone.DTO.Upload_Order.UploadResponseDTO;
import WebCapstone.WebCapstone.service.AwsS3Service;
import WebCapstone.WebCapstone.service.ChatService;
import WebCapstone.WebCapstone.service.ShowUploadService;
import WebCapstone.WebCapstone.service.UploadService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.FieldPosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping("/api/load")
public class UploadController {

    @Autowired
    UploadService uploadService;

    @Autowired
    ShowUploadService showUploadService;


    @Autowired
    AwsS3Service awsS3Service;

    @Autowired
    ChatService chatService;



    @RequestMapping(value="Upload", method=RequestMethod.POST)
    public ResponseDTO<UploadResponseDTO> Upload (HttpServletRequest request, @Valid UploadDTO uploadDTO, BindingResult bindingResult, @RequestParam(value="file", required=false) List<MultipartFile> files
            , @RequestParam(value="memberid", required=false) String memberid
            , @RequestParam(value="category", required=false) String category
            , @RequestParam(value="itemname", required=false) String itemname
            , @RequestParam(value="itemid", required=false) String itemid
            , @RequestParam(value="title", required=false) String title
            , @RequestParam(value="maintext", required=false) String maintext
            , @RequestParam(value="itemprice", required=false) String itemprice
            , @RequestParam(value="detailcategory", required=false)String detailcategory
            ,@RequestParam(value="purpose", required=false) String purpose) throws IOException {
        String URL;
        if(files == null){
            URL = null;
        }
        else{
            URL = awsS3Service.uploadFile(files);
        }



        StringBuffer stringBuffer = new StringBuffer();
        Date now = new Date();


        if(category.equals("--선택--")) {//데이터검증
            category="";
        }
        if(detailcategory.equals("--선택--")) {//데이터검증
            detailcategory="";
        }
        System.out.println(URL);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
        simpleDateFormat.format(now, stringBuffer, new FieldPosition(0));
        uploadDTO = UploadDTO.builder().memberid(memberid).category(category)
                .itemname(itemname).itemid(Integer.parseInt(itemid)).title(title).maintext(maintext)
                .itemprice(Integer.parseInt(itemprice)).detailcategory(detailcategory).purpose(purpose).URL(URL).view(0).favor(0).uploadtime(stringBuffer.toString()).status("판매중").build();

        if(bindingResult.hasErrors()) {//데이터검증
            System.out.println("Form data has some errors");
            List<ObjectError> errors = bindingResult.getAllErrors();
            for(ObjectError error:errors) {
                System.out.println(error.getDefaultMessage());
            }
            return ResponseDTO.setFailed("모든 상품의 정보를 정확하게 입력해주세요");

        }
        ResponseDTO<UploadResponseDTO> result = uploadService.Upload(uploadDTO);
        return result;


    }


    @GetMapping("/UploadShow")// 업로드 기능
    public List<UploadDTO> ShowUpload(){
        return showUploadService.ShowUpload();


    }

    @PostMapping("/Changeupload") // 가격 수정 기능
    public void changeupload(@RequestBody ChangeDTO changeDTO){
        System.out.println(changeDTO);
        uploadService.uploadchange(changeDTO);
    }



    @PostMapping("/favorRequest")
    public List<UploadDTO> favorUpload(@RequestBody FavorDTO favorDTO){
        return showUploadService.favorUpload(favorDTO);
    }

    @PostMapping("/showDetail")
    public UploadDTO showDetail(@RequestBody ItemIDDTO itemid){

        return showUploadService.ShowUploadDetail(itemid);
    }



    @PostMapping("/orderDetail")
    public UploadDTO orderDetail(@RequestBody DetailDTO detailDTO){
        System.out.println("디테일디티오" + showUploadService.OrderUploadDetail(detailDTO));
        return showUploadService.OrderUploadDetail(detailDTO);
    }

    @PostMapping("/delete")
    public void deleteUpload(@RequestBody ItemIDDTO itemid){
        System.out.println(itemid);
        showUploadService.deleteFavor(itemid); // 해당 게시물에 찜해 놓은 유저들 모두 삭제
        chatService.deleteChat(itemid); // 해당 게시물과 관련된 채팅 모두 삭제
        uploadService.deleteUpload(itemid); // 해당 게시물 직접 삭제


    }

    @PostMapping("/finish")
    public void finishUpload(@RequestBody NicknameDTO nicknameDTO){

        showUploadService.finishFavor(nicknameDTO); // 해당 게시물에 찜해 놓은 유저들 모두 삭제
        chatService.finishChat(nicknameDTO); // 해당 게시물과 관련된 채팅 모두 삭제



    }

    @PostMapping("/favor")
    public void favorApply(@RequestBody FavorDTO favorDTO){
        uploadService.favorApply(favorDTO);
    }

    @PostMapping("/changeStatus")
    public void changeStatus(@RequestBody ItemIDDTO itemIDDTO){
        System.out.println(itemIDDTO);
        uploadService.changeStatus(itemIDDTO);
    }

    @PostMapping("/recommend")
    public Set<UploadDTO> recommendUpload(@RequestBody ItemIDDTO itemIDDTO){
        System.out.println(itemIDDTO);
        return showUploadService.recommendUpload(itemIDDTO);
    }
}