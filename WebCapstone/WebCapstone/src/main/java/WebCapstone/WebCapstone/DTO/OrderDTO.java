package WebCapstone.WebCapstone.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class OrderDTO {
    private String seller;
    private String buyer;

    private String object;

    private int price;
    private String URL;
    private String address;

    private String date;

    private String request;

}
