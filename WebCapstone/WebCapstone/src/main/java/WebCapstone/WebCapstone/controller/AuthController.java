package WebCapstone.WebCapstone.controller;

import WebCapstone.WebCapstone.DTO.ResponseDTO;
import WebCapstone.WebCapstone.DTO.SignInDTO;
import WebCapstone.WebCapstone.DTO.SignupDTO;
import WebCapstone.WebCapstone.DTO.SignupResponseDTO;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@CrossOrigin
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/signUp") // 회원가입 기능(추후에 구현)
    public ResponseDTO<SignupResponseDTO> signUp(@RequestBody SignupDTO requestBody){
        System.out.println(requestBody.toString());
        return null;
    }

    @PostMapping("/signIn") // 로그인 기능(추후에 구현)
    public String signIn(@RequestBody SignInDTO requestBody){
        System.out.println(requestBody.toString());
        if(Objects.equals(requestBody.getId(), "test@test.com") && Objects.equals(requestBody.getPassword(), "admin1234")){
            System.out.println("ok");
            return "ok";
        }
        else{
            System.out.println("fail");
            return "fail";
        }
    }


}
