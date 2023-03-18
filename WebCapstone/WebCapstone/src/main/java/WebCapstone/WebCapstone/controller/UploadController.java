package WebCapstone.WebCapstone.controller;

import WebCapstone.WebCapstone.DTO.FavorDTO;
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
import java.text.FieldPosition;
import java.text.SimpleDateFormat;
import java.util.Date;
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
            , @RequestParam(value="itemprice", required=false) String itemprice
            ,@RequestParam(value="detailcategory", required=false)String detailcategory) throws IOException {
        String URL = awsS3Service.uploadFile(files);

        StringBuffer stringBuffer = new StringBuffer();
        Date now = new Date();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
        simpleDateFormat.format(now, stringBuffer, new FieldPosition(0));
        UploadDTO uploadDTO = UploadDTO.builder().memberid(memberid).category(category)
                        .itemname(itemname).itemid(Integer.parseInt(itemid)).title(title).maintext(maintext)
                        .itemprice(Integer.parseInt(itemprice)).detailcategory(detailcategory).URL(URL).view(0).favor(0).uploadtime(stringBuffer.toString()).build();
        ResponseDTO<UploadResponseDTO> result = uploadService.Upload(uploadDTO);
        return result;


    }


    @GetMapping("/UploadShow")// 업로드 기능
    public List<UploadDTO> ShowUpload(){
        return showUploadService.ShowUpload();


    }

    @PostMapping("/showDetail")
    public UploadDTO showDetail(@RequestBody ItemIDDTO itemid){

        return showUploadService.ShowUploadDetail(itemid);
    }

    @PostMapping("/delete")
    public void deleteUpload(@RequestBody ItemIDDTO itemid){
        uploadService.deleteUpload(itemid);
    }

    @PostMapping("/favor")
    public void favorApply(@RequestBody FavorDTO favorDTO){
        uploadService.favorApply(favorDTO);
    }
}
