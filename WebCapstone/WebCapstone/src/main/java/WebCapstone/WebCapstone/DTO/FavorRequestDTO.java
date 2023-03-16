package WebCapstone.WebCapstone.DTO;

import lombok.*;

@Data
@Builder
@Setter
@Getter
@NoArgsConstructor
public class FavorRequestDTO {
    private int id;
    private String senduser;
    private String receiveuser;
    private String title;
    private String time;

    @Builder
    public FavorRequestDTO(int id, String senduser, String receiveuser, String title, String time){
        this.id = id;
        this.senduser = senduser;
        this.receiveuser = receiveuser;
        this.title = title;
        this.time = time;
    }
}


