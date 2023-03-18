package WebCapstone.WebCapstone.DTO.Upload_Order;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Data
@Setter
@Getter
@NoArgsConstructor
public class UploadDTO {
    private String memberid;

    private String category;

    private String detailcategory;
    private String itemname;
    private int itemid;

    private String title;//게시글 제목
    private String maintext;//게시글 본문
    private int itemprice;

    private String URL;

    private int view;


    private int favor;

    private String uploadtime;



    private boolean favorcheck = false;

    @Builder
    public UploadDTO(String memberid, String category, String detailcategory, String itemname, int itemid, String title, String maintext, int itemprice, String URL, int view, int favor, String uploadtime, boolean favorcheck){
        this.memberid = memberid;
        this.category = category;
        this.detailcategory = detailcategory;
        this.itemname = itemname;
        this.itemid = itemid;
        this.title = title;
        this.maintext = maintext;
        this.itemprice = itemprice;
        this.URL = URL;
        this.view = view;
        this.favor = favor;
        this.uploadtime = uploadtime;
        this.favorcheck = favorcheck;

    }


}