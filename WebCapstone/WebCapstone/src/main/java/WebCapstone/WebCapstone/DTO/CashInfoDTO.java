package WebCapstone.WebCapstone.DTO;

import jakarta.persistence.Id;
import lombok.*;

@Data
@NoArgsConstructor
@Setter
@Getter
@ToString
public class CashInfoDTO {

    private String nickname;

    private String plus;

    private int cash;

    private int currentcash;

    private String date;
}
