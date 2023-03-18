package WebCapstone.WebCapstone.service;

import WebCapstone.WebCapstone.DTO.ResponseDTO;
import WebCapstone.WebCapstone.DTO.SignInDTO;
import WebCapstone.WebCapstone.DTO.SignInResponseDTO;
import WebCapstone.WebCapstone.DTO.SignupDTO;
import WebCapstone.WebCapstone.entity.MemberEntity;
import WebCapstone.WebCapstone.filter.JwtAuthencationFilter;
import WebCapstone.WebCapstone.repository.MemberRepository;
import WebCapstone.WebCapstone.security.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    MemberRepository memberRepository;

    @Autowired TokenProvider tokenProvider;

    @Autowired
    JwtAuthencationFilter jwtAuthencationFilter;





    private boolean checkPassword(SignupDTO member){
        String password = member.getPwd();
        if (password.length()<=4){
            return false;
        }
        return true;
    }

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public ResponseDTO<?> signUp(SignupDTO dto){
        String Id = dto.getId();
        String Pwd = dto.getPwd();
        String PasswordCheck = dto.getPasswordcheck();

        try{
            if(memberRepository.existsById(Id)){
                return ResponseDTO.setFailed("존재하는 아이디입니다.");
            }
        }catch(Exception e){
            return ResponseDTO.setFailed("서버 오류입니다.");
        }

        if(!Pwd.equals(PasswordCheck)){
            return ResponseDTO.setFailed("비밀번호 다시 확인해주세요.");
        }

        if(!checkPassword(dto)){
            return ResponseDTO.setFailed("비밀번호가 너무 짧아요.");
        }
        MemberEntity userEntity = new MemberEntity(dto);

        //비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(Pwd);
        userEntity.setPwd(encodedPassword);
        try{
            memberRepository.save(userEntity);
        }catch(Exception e){
            return ResponseDTO.setFailed("Data Base error");
        }




        return ResponseDTO.setSuccess("Signup success", null);
    }

    public ResponseDTO<SignInResponseDTO> signIn(SignInDTO dto){
        String id = dto.getId();
        String pwd = dto.getPwd();




        MemberEntity memberEntity = null;
        try{
            memberEntity = memberRepository.findByid(id);
            //잘못된 아이디
            if(memberEntity == null){
                return ResponseDTO.setFailed("로그인 실패");
            }
            //잘못된 비밀번호
            if(!passwordEncoder.matches(pwd, memberEntity.getPwd())){
                return ResponseDTO.setFailed("로그인 실패");
            }
        }catch(Exception e){
            return ResponseDTO.setFailed("데이터베이스 에러");
        }


        memberEntity.setPwd("");
        String token = tokenProvider.create(memberEntity.getNickname());
        int exprTime = 3600000;

        SignInResponseDTO signInResponseDTO = new SignInResponseDTO(token, exprTime, memberEntity); // 토큰 만료시간 맴버 정보
        return ResponseDTO.setSuccess("로그인 성공", signInResponseDTO);
    }
}