package WebCapstone.WebCapstone.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ChangeDTO {
    private int itemid;

    private String itemname;

    private String title;//게시글 제목

    private String maintext;//게시글 본문

    private int itemprice;
}
