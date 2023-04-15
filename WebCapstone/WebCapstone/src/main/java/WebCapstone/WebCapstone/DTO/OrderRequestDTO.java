package WebCapstone.WebCapstone.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class OrderRequestDTO {
    private String seller;
    private String buyer;

    private String object;

    private int price;
    private String address;
}
