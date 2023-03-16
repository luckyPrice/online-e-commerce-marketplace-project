package WebCapstone.WebCapstone.entity;

import WebCapstone.WebCapstone.DTO.FavorRequestDTO;
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
@Entity(name="favor")
@Table(name ="favor")
public class FavorEntity {
    @Id
    private int id;
    private String senduser;
    private String receiveuser;
    private String title;
    private String time;

    public FavorEntity(FavorRequestDTO favorRequestDTO){
        this.id = favorRequestDTO.getId();
        this.senduser = favorRequestDTO.getSenduser();
        this.receiveuser = favorRequestDTO.getReceiveuser();
        this.title = favorRequestDTO.getTitle();
        this.time = favorRequestDTO.getTime();
    }
}
