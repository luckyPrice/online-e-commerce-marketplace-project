package WebCapstone.WebCapstone.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName="set")
@NoArgsConstructor
public class ResponseDTO<D> {
    private boolean result;
    private String message;
    private D data;

    public static <D> ResponseDTO<D> setSuccess(String message, D data){
        return ResponseDTO.set(true, message, data);
    }

    public static <D> ResponseDTO<D> setFailed(String message){
        return ResponseDTO.set(false, message, null);
    }
}
