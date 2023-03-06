package WebCapstone.WebCapstone.DTO.Upload_Order;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@Setter
@Getter
@NoArgsConstructor
public class UploadDTO {
    private String memberid;

    private String category;
    private String itemname;
    private int itemid;

    private String title;//게시글 제목
    private String maintext;//게시글 본문
    private int itemprice;

    private String URL;

    @Builder
    public UploadDTO(String memberid, String category, String itemname, int itemid, String title, String maintext, int itemprice, String URL){
        this.memberid = memberid;
        this.category = category;
        this.itemname = itemname;
        this.itemid = itemid;
        this.title = title;
        this.maintext = maintext;
        this.itemprice = itemprice;
        this.URL = URL;
    }


}