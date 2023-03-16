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
@Entity(name="chatinfo")
@Table(name ="chatinfo")
public class ChatInfo {
    @Id
    private String id;

    private String senduser;

    private String receiveuser;

    private String chattitle;

    private int notread;
}
