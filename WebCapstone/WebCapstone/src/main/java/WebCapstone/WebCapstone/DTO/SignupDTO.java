package WebCapstone.WebCapstone.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupDTO {
    private String name;
    private String id;
    private String password;
    private String nickname;
    private String phonenumber;
    private String sex;
    private String address;
}
