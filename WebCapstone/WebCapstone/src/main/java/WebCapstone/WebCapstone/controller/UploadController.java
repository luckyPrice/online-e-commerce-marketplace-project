package WebCapstone.WebCapstone.controller;

import WebCapstone.WebCapstone.DTO.ResponseDTO;
import WebCapstone.WebCapstone.DTO.Upload_Order.UploadDTO;
import WebCapstone.WebCapstone.DTO.Upload_Order.UploadResponseDTO;
import WebCapstone.WebCapstone.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class UploadController {

    @Autowired
    UploadService uploadService;

    @PostMapping("/Upload") // 업로드 기능
    public ResponseDTO<UploadResponseDTO> Upload(@RequestBody UploadDTO requestBody){
        System.out.println(requestBody.toString());
        ResponseDTO<UploadResponseDTO> result = uploadService.Upload(requestBody);
        return result;

    }
}
