package WebCapstone.WebCapstone.service;

import WebCapstone.WebCapstone.DTO.*;
import WebCapstone.WebCapstone.entity.CashInfo;
import WebCapstone.WebCapstone.entity.MemberEntity;
import WebCapstone.WebCapstone.filter.JwtAuthencationFilter;
import WebCapstone.WebCapstone.repository.CashInfoRepository;
import WebCapstone.WebCapstone.repository.MemberRepository;
import WebCapstone.WebCapstone.security.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.FieldPosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class AuthService {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    CashInfoRepository cashInfoRepository;

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

    public MemberInfoDTO getMemberInfo(NicknameDTO nicknameDTO){
        MemberEntity memberEntity = memberRepository.findByNickname(nicknameDTO.getNickname());
        System.out.println(memberEntity);
        if(memberEntity != null){
            MemberInfoDTO memberInfoDTO = new MemberInfoDTO(memberEntity.getUsername(), memberEntity.getNickname(), memberEntity.getPhonenumber(),
                    memberEntity.getSex(), memberEntity.getAddress(), memberEntity.getCash());
            return memberInfoDTO;
        }
        return null;
    }

    public void UpdateCash(CashDTO cashDTO){
        MemberEntity memberEntity = memberRepository.findByNickname(cashDTO.getNickname());
        System.out.println(memberEntity);
        if(memberEntity != null){
            memberEntity.setCash(memberEntity.getCash() + cashDTO.getCash());
            memberRepository.save(memberEntity);
        }
    }

    public void ReduceCash(CashDTO cashDTO){
        MemberEntity memberEntity = memberRepository.findByNickname(cashDTO.getNickname());
        System.out.println(memberEntity);
        if(memberEntity != null){
            memberEntity.setCash(memberEntity.getCash() - cashDTO.getCash());
            memberRepository.save(memberEntity);
        }
    }

    public List<CashInfo> getCashOrder(NicknameDTO nicknameDTO){
        List<CashInfo> cashInfoList = cashInfoRepository.findAll(Sort.by("date"));
        List<CashInfo> cashInfos = new ArrayList<>();;
        for(var i = 0 ; i < cashInfoRepository.count(); i++){
            if(Objects.equals(cashInfoList.get(i).getNickname(), nicknameDTO.getNickname())){
                cashInfos.add(cashInfoList.get(i));
            }
        }
        System.out.println(cashInfos);
        return cashInfos;
    }

    public void createCashInfo(CashDTO cashDTO, String plus){
        int id = 1;
        try{
            while(cashInfoRepository.findByid(id)!=null){
                id++;
            }
        }
        catch(Exception e){
            System.out.println("오류");
        }
        MemberEntity memberEntity = memberRepository.findByNickname(cashDTO.getNickname());

        StringBuffer stringBuffer = new StringBuffer();
        Date now = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
        simpleDateFormat.format(now, stringBuffer, new FieldPosition(0));
        CashInfo cashInfo = new CashInfo(id, cashDTO.getNickname(), plus, cashDTO.getCash(), memberEntity.getCash(), stringBuffer.toString());
        cashInfoRepository.save(cashInfo);

    }
}