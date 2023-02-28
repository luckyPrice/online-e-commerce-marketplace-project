package WebCapstone.WebCapstone.DTO.Upload_Order;

import lombok.*;

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

    @Builder
    public UploadDTO(String memberid, String category, String itemname, int itemid, String title, String maintext, int itemprice){
        this.memberid = memberid;
        this.itemname = itemname;
        this.itemid = itemid;
        this.title = title;
        this.maintext = maintext;
        this.itemprice = itemprice;
    }


}