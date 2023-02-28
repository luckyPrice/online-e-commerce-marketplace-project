package WebCapstone.WebCapstone.DTO;

import lombok.*;

@Data
@NoArgsConstructor
@Getter
public class MyChatDTO {
    private String nickname;
    private String chattitle;


    @Builder
    public MyChatDTO(String nickname, String chattitle){
        this.nickname = nickname;
        this.chattitle = chattitle;
    }
}
