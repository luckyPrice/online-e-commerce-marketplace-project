package WebCapstone.WebCapstone.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupDTO {
    private String username;

    private String id;
    private String pwd;
    private String passwordcheck;
    private String nickname;
    private String phonenumber;
    private String sex;
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
