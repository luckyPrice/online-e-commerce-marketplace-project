package WebCapstone.WebCapstone.DTO;

import lombok.*;

@Data
@NoArgsConstructor
@Getter
public class MyChatDTO {
    private String nickname;
    private String chattitle;

    private int notread;



    @Builder
    public MyChatDTO(String nickname, String chattitle, int notread){
        this.nickname = nickname;
        this.chattitle = chattitle;
        this.notread = notread;
    }
}
