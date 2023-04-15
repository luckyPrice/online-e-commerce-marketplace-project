package WebCapstone.WebCapstone.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupDTO {

    @NotEmpty(message = "이름을 입력해주세요")
    private String username;
    @Size(min=2, max=100, message = "아이디를 입력해주세요")
    private String id;
    private String pwd;
    private String passwordcheck;
    @Size(min=2, max=100, message = "사용하실 닉네임을 입력해주세요")
    private String nickname;
    @NotEmpty(message = "전화번호를 입력해주세요")
    private String phonenumber;
    private String sex;
    @NotEmpty(message = "주소를 입력해주세요")
    private String address;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setPasswordCheck(String passwordcheck) {
        this.passwordcheck = passwordcheck;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getUsername() {
        return username;
    }

    public String getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public String getNickname() {
        return nickname;
    }

    public String getPasswordcheck() {
        return passwordcheck;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public String getPwd() {
        return pwd;
    }

    public String getSex() {
        return sex;
    }
}