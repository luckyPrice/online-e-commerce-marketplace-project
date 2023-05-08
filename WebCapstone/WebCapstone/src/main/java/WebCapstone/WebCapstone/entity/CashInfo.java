package WebCapstone.WebCapstone.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="cashinfo")
@Table(name ="cashinfo")
public class CashInfo {


    @Id
    private int id;

    private String nickname;

    private String plus;

    private int cash;

    private int currentcash;

    private String date;


}
