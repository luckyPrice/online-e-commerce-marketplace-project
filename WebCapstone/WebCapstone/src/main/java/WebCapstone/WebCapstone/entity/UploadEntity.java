package WebCapstone.WebCapstone.entity;


import WebCapstone.WebCapstone.DTO.SignupDTO;
import WebCapstone.WebCapstone.DTO.Upload_Order.UploadDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="upload")
@Table(name ="upload")
public class UploadEntity {
    private String memberid;

    private String category;

    private String itemname;

    @Id
    private int itemid;
    private String title;//게시글 제목
    private String maintext;//게시글 본문
    private int itemprice;

    private String URL;

    public UploadEntity(UploadDTO dto){
        this.memberid = dto.getMemberid();
        this.category = dto.getCategory();
        this.itemname = dto.getItemname();
        this.itemid = dto.getItemid();
        this.title = dto.getTitle();
        this.maintext = dto.getMaintext();
        this.itemprice = dto.getItemprice();
        this.URL = dto.getURL();

    }

}