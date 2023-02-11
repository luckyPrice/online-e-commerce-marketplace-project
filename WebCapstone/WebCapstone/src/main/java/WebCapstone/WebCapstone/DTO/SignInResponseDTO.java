package WebCapstone.WebCapstone.DTO;

import WebCapstone.WebCapstone.entity.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignInResponseDTO {
    private String token;
    private int exprTime;
    private MemberEntity user;
}
