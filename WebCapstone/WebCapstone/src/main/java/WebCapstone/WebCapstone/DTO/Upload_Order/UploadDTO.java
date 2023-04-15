package WebCapstone.WebCapstone.DTO.Upload_Order;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Data
@Setter
@Getter
@NoArgsConstructor
public class UploadDTO {
    private String memberid;

    @NotEmpty(message = "카테고리를 선택하지 않았습니다.")
    private String category;
    @NotEmpty(message = "세부 카테고리를 선택하지 않았습니다.")
    private String detailcategory;
    @NotEmpty(message = "상품 이름을 입력하지 않았습니다.")
    private String itemname;
    private int itemid;

    @NotEmpty(message = "제목을 입력하지 않았습니다.")
    private String title;//게시글 제목
    @NotEmpty(message = "설명란을 입력하지 않았습니다.")
    private String maintext;//게시글 본문
    @Min(value = 1, message = "0원 이상의 금액을 입력하지 않았습니다.")
    private int itemprice;


    private String URL;

    private int view;


    private int favor;

    private String uploadtime;
    private String purpose;



    private boolean favorcheck = false;



    @Builder
    public UploadDTO(String memberid, String category, String detailcategory, String itemname, int itemid, String title, String maintext, int itemprice, String purpose,String URL, int view, int favor, String uploadtime, boolean favorcheck){
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
        this.purpose = purpose;
        this.favorcheck = favorcheck;

    }


}