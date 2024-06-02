package ChatAnyWhere.chatanywhere.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest{
    @NotBlank
    private String username;
    @NotBlank
    private  String password;


}
