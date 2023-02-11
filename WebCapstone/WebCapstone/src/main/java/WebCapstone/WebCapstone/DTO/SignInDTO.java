package WebCapstone.WebCapstone.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignInDTO {
    @NotBlank
    private String id;
    @NotBlank
    private String pwd;

    public String getId() {
        return id;
    }

    public String getPwd() {
        return pwd;
    }
}
