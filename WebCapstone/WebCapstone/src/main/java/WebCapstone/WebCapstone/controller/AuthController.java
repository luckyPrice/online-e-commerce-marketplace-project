package WebCapstone.WebCapstone.controller;

import WebCapstone.WebCapstone.DTO.*;
//import WebCapstone.WebCapstone.service.AuthService;
import WebCapstone.WebCapstone.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@CrossOrigin
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/signUp") // 회원가입 기능
    public ResponseDTO<?> signUp(@RequestBody SignupDTO requestBody){
        ResponseDTO<?> result = authService.signUp(requestBody);
        return result;

    }

    @PostMapping("/signIn") // 로그인 기능
    public ResponseDTO<SignInResponseDTO> signIn(@RequestBody SignInDTO requestBody){
        ResponseDTO<SignInResponseDTO> result = authService.signIn(requestBody);
        return result;

    }



}
