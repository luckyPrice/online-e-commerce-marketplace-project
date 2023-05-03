package WebCapstone.WebCapstone.DTO;


import lombok.*;

@Data
@NoArgsConstructor
@Setter
@Getter
@ToString
public class ChatDTO {

    //private String chatRoomId;
    private String senduser;
    private String receiveuser;
    private String chattitle;
    private String message;
    private String date;

    private int notread;

    private String type = "message";

    @Builder
    public ChatDTO(String senduser, String receiveuser, String chattitle, String message, String date, int notread, String type){
        this.senduser = senduser;
        this.receiveuser = receiveuser;
        this.chattitle = chattitle;
        this.message = message;
        this.date = date;
        this.notread = notread;
        this.type = type;
    }





}


