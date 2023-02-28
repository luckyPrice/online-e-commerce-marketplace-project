package WebCapstone.WebCapstone.controller;

import WebCapstone.WebCapstone.DTO.ItemIDDTO;
import WebCapstone.WebCapstone.DTO.ResponseDTO;
import WebCapstone.WebCapstone.DTO.Upload_Order.UploadDTO;
import WebCapstone.WebCapstone.DTO.Upload_Order.UploadResponseDTO;
import WebCapstone.WebCapstone.service.ShowUploadService;
import WebCapstone.WebCapstone.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/load")
public class UploadController {

    @Autowired
    UploadService uploadService;

    @Autowired
    ShowUploadService showUploadService;

    @PostMapping("/Upload") // 업로드 기능
    public ResponseDTO<UploadResponseDTO> Upload(@RequestBody UploadDTO requestBody){
        System.out.println(requestBody.toString());
        ResponseDTO<UploadResponseDTO> result = uploadService.Upload(requestBody);
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
