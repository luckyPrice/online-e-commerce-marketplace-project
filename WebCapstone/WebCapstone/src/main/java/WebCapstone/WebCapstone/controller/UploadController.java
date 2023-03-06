package WebCapstone.WebCapstone.controller;

import WebCapstone.WebCapstone.DTO.ItemIDDTO;
import WebCapstone.WebCapstone.DTO.ResponseDTO;
import WebCapstone.WebCapstone.DTO.Upload_Order.UploadDTO;
import WebCapstone.WebCapstone.DTO.Upload_Order.UploadResponseDTO;
import WebCapstone.WebCapstone.service.AwsS3Service;
import WebCapstone.WebCapstone.service.ShowUploadService;
import WebCapstone.WebCapstone.service.UploadService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

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



    @RequestMapping(value="Upload", method=RequestMethod.POST)
    public ResponseDTO<UploadResponseDTO> Upload (HttpServletRequest request, @RequestParam(value="file", required=false) List<MultipartFile> files
            , @RequestParam(value="memberid", required=false) String memberid
            , @RequestParam(value="category", required=false) String category
            , @RequestParam(value="itemname", required=false) String itemname
            , @RequestParam(value="itemid", required=false) String itemid
            , @RequestParam(value="title", required=false) String title
            , @RequestParam(value="maintext", required=false) String maintext
            , @RequestParam(value="itemprice", required=false) String itemprice) throws IOException {
        String URL = awsS3Service.uploadFile(files);
        UploadDTO uploadDTO = UploadDTO.builder().memberid(memberid).category(category)
                        .itemname(itemname).itemid(Integer.parseInt(itemid)).title(title).maintext(maintext)
                        .itemprice(Integer.parseInt(itemprice)).URL(URL).build();
        ResponseDTO<UploadResponseDTO> result = uploadService.Upload(uploadDTO);
        return result;

    }


    @GetMapping("/UploadShow")// 업로드 기능
    public List<UploadDTO> ShowUpload(){
        System.out.println("메인페이지 업로드 요청");
        return showUploadService.ShowUpload();


    }

    @PostMapping("/showDetail")
    public UploadDTO showDetail(@RequestBody ItemIDDTO itemid){
        System.out.println(itemid.getItemid());
        return showUploadService.ShowUploadDetail(itemid.getItemid());
    }
}
