package ChatAnyWhere.chatanywhere.dto;

import ChatAnyWhere.chatanywhere.config.Config;
import ChatAnyWhere.chatanywhere.models.User;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.util.Arrays.asList;
import static org.hibernate.boot.model.process.spi.MetadataBuildingProcess.build;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUserRequest {

    @NotBlank
    private  String username;
    @NotBlank
    private String password;
    public User toUSer() throws Exception
    {
       return User.builder().password(Config.encrypt(this.password)).username(this.username).extnuserid(UUID.randomUUID().toString()).friendsList(new ArrayList<>()).build();
    }
}
