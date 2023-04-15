package WebCapstone.WebCapstone.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class DetailDTO {
    private String seller;

    private String buyer;

    private String object;
}
