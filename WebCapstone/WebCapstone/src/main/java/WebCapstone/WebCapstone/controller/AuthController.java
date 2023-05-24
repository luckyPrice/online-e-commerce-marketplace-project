package WebCapstone.WebCapstone.controller;

import WebCapstone.WebCapstone.DTO.*;
//import WebCapstone.WebCapstone.service.AuthService;
import WebCapstone.WebCapstone.entity.CashInfo;
import WebCapstone.WebCapstone.service.AuthService;
import WebCapstone.WebCapstone.service.OrderService;
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

    @Autowired
    OrderService orderService;

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

    @PostMapping("/getAuth") // 회원정보를 가져오는 기능
    public MemberInfoDTO getAuth(@RequestBody NicknameDTO nickNameDTO){
        return authService.getMemberInfo(nickNameDTO);
        //return result;

    }

    @PostMapping("/UpdateCash") // 캐시 적용
    public void UpdateCash(@RequestBody CashDTO cashDTO){
        authService.UpdateCash(cashDTO);
        authService.createCashInfo(cashDTO, "plus");
        //return result;

    }

    @PostMapping("/ReduceCash") // 로그인 기능
    public void ReduceCash(@RequestBody CashDTO cashDTO){
        authService.ReduceCash(cashDTO);
        authService.createCashInfo(cashDTO, "minus");

        //return result;

    }

    @PostMapping("/getCashOrder")
    public List<CashInfo> getCashOrder(@RequestBody NicknameDTO nicknameDTO){
        return authService.getCashOrder(nicknameDTO);
    }

    @PostMapping("createAuthtime")
    public int getTime(@RequestBody NicknameDTO nicknameDTO) {
        return authService.getTime(nicknameDTO);
    }





}
