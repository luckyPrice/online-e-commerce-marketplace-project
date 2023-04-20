package WebCapstone.WebCapstone.entity;

import WebCapstone.WebCapstone.DTO.ForumDTO;
import WebCapstone.WebCapstone.DTO.SignupDTO;
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
@Entity(name="forum")
@Table(name ="forum")
public class ForumEntity {
    private int itemid;
    @Id
    private int commentid;
    private String comment;
    private int star;

    private String writter;

    private String target;

    private String date;


    public ForumEntity(ForumDTO dto){
        this.commentid = dto.getCommentid();
        this.itemid = dto.getItemid();
        this.star = dto.getStar();
        this.comment = dto.getComment();
        this.writter = dto.getWritter();
        this.target = dto.getTarget();
        this.date = dto.getDate();
    }
}