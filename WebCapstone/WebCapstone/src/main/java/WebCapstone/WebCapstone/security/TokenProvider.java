package WebCapstone.WebCapstone.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class TokenProvider {
    //Jwt 생성 및 검증을 위한 키
    private static final String SECURITY_KEY = "2CSAFWFWAFWF22214142SFWAFW3W";

    //Jwt 생성하는 매서드
    public String create(String id){
        //만료날짜를 현재 날짜 + 1시간으로 설정
        Date exprTime = Date.from(Instant.now().plus(1, ChronoUnit.HOURS));
        System.out.println("..");

        //JWT 생성
        return  Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, SECURITY_KEY)
                .setSubject(id).setIssuedAt(new Date()).setExpiration(exprTime)
                .compact();




    }

    //검증
    public String validate(String token){
        Claims claims = Jwts.parser().setSigningKey(SECURITY_KEY).parseClaimsJws(token).getBody();

        return claims.getSubject();
    }
}