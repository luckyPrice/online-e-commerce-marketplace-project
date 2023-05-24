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
import java.util.*;

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
        StringBuffer stringBuffer = new StringBuffer();
        Date now = new Date();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
        simpleDateFormat.format(now, stringBuffer, new FieldPosition(0));
        dto.setDate(stringBuffer.toString());
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
        if(memberEntity != null){
            MemberInfoDTO memberInfoDTO = new MemberInfoDTO(memberEntity.getUsername(), memberEntity.getNickname(), memberEntity.getPhonenumber(),
                    memberEntity.getSex(), memberEntity.getAddress(), memberEntity.getCash(), memberEntity.getDate());
            return memberInfoDTO;
        }
        return null;
    }

    public void UpdateCash(CashDTO cashDTO){
        MemberEntity memberEntity = memberRepository.findByNickname(cashDTO.getNickname());
        if(memberEntity != null){
            memberEntity.setCash(memberEntity.getCash() + cashDTO.getCash());
            memberRepository.save(memberEntity);
        }
    }

    public void ReduceCash(CashDTO cashDTO){
        MemberEntity memberEntity = memberRepository.findByNickname(cashDTO.getNickname());
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

    public int getTime(NicknameDTO nicknameDTO){
        MemberEntity memberEntity = memberRepository.findByNickname(nicknameDTO.getNickname());

        StringBuffer stringBuffer = new StringBuffer();
        Date now = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
        simpleDateFormat.format(now, stringBuffer, new FieldPosition(0));

        String str1[];
        String str2[];
        str1 = memberEntity.getDate().split("/");
        str2 = stringBuffer.toString().split("/");
        System.out.println(Arrays.toString(str1));
        System.out.println(Arrays.toString(str2));
        String str3[];
        String str4[];
        str3 = str1[2].split(" ");
        str4 = str2[2].split(" ");
        int orderyear = Integer.parseInt(str3[0]);
        int nowyear = Integer.parseInt(str4[0]);
        int ordermonth = Integer.parseInt(str1[0]);
        int nowmonth = Integer.parseInt(str2[0]);
        int orderday = Integer.parseInt(str1[1]);
        int nowday = Integer.parseInt(str2[1]);
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        c1.set(orderyear, ordermonth, orderday);
        c2.set(nowyear, nowmonth, nowday);
        long d1,d2;
        d1=c1.getTimeInMillis();
        d2=c2.getTimeInMillis();

        int days =(int)((d2-d1)/(1000*60*60*24));
        System.out.println("날짜 차이는 " + days);

        return days;
    }
}