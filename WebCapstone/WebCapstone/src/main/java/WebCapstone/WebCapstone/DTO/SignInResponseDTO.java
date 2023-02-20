package WebCapstone.WebCapstone.DTO;

import WebCapstone.WebCapstone.entity.MemberEntity;
import lombok.*;

import java.lang.reflect.Member;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SignInResponseDTO {
    private String token;
    private int exprTime;
    private MemberEntity user;




}
