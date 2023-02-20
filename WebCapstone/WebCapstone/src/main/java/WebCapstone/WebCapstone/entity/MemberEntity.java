package WebCapstone.WebCapstone.entity;

import WebCapstone.WebCapstone.DTO.SignupDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="Member")
@Table(name ="Member")
public class MemberEntity {


    private String username;
    @Id
    private String id;
    private String pwd;
    private String passwordcheck;
    private String nickname;
    private String phonenumber;
    private String sex;
    private String address;

    public MemberEntity(SignupDTO dto){
        this.username = dto.getUsername();
        this.id = dto.getId();
        this.pwd = dto.getPwd();
        this.passwordcheck = dto.getPasswordcheck();
        this.nickname = dto.getNickname();
        this.phonenumber = dto.getPhonenumber();
        this.sex = dto.getSex();
        this.address = dto.getAddress();

    }
}
