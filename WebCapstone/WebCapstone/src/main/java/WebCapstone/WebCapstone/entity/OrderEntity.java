package WebCapstone.WebCapstone.entity;

import WebCapstone.WebCapstone.DTO.OrderDTO;
import WebCapstone.WebCapstone.DTO.Upload_Order.UploadDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.FieldPosition;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="orderinfo")
@Table(name ="orderinfo")
public class OrderEntity {

    @Id
    private int id;
    private String seller;
    private String buyer;

    private String object;

    private int price;
    private String URL;
    private String address;

    private String date;

    private String receivedate;
    private int step;

    private String request;

    public OrderEntity(OrderDTO dto){
        StringBuffer stringBuffer = new StringBuffer();
        Date now = new Date();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
        simpleDateFormat.format(now, stringBuffer, new FieldPosition(0));

        this.seller = dto.getSeller();
        this.buyer = dto.getBuyer();
        this.object = dto.getObject();
        this.price = dto.getPrice();
        this.URL = dto.getURL();
        this.address = dto.getAddress();
        this.step = 1;
        this.date = stringBuffer.toString();
        this.request = dto.getRequest();
    }
}
