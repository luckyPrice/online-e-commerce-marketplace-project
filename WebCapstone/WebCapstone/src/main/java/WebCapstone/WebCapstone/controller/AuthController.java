package WebCapstone.WebCapstone.controller;

import WebCapstone.WebCapstone.DTO.*;
//import WebCapstone.WebCapstone.service.AuthService;
import WebCapstone.WebCapstone.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/signUp") // 회원가입 기능
    public ResponseDTO<?> signUp(@Valid @RequestBody SignupDTO requestBody, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {//데이터검증
            System.out.println("Form data has some errors");
            List<ObjectError> errors = bindingResult.getAllErrors();
            for(ObjectError error:errors) {
                System.out.println(error.getDefaultMessage());
            }
            return ResponseDTO.setFailed("올바르게 정보를 입력해주세요");
        }
        ResponseDTO<?> result = authService.signUp(requestBody);
        return result;

    }

    @PostMapping("/signIn") // 로그인 기능
    public ResponseDTO<SignInResponseDTO> signIn(@RequestBody SignInDTO requestBody){
        ResponseDTO<SignInResponseDTO> result = authService.signIn(requestBody);
        return result;

    }

    @PostMapping("/getAuth") // 로그인 기능
    public MemberInfoDTO getAuth(@RequestBody NicknameDTO nickNameDTO){
        return authService.getMemberInfo(nickNameDTO);
        //return result;

    }



}
