package WebCapstone.WebCapstone.DTO.Upload_Order;

import WebCapstone.WebCapstone.entity.MemberEntity;
import WebCapstone.WebCapstone.entity.UploadEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;



import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UploadResponseDTO {
    private List<UploadEntity> uploadArray;
}