package WebCapstone.WebCapstone.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberInfoDTO {

    private String username;

    private String nickname;

    private String phonenumber;
    private String sex;
    private String address;

    private int cash;
}
