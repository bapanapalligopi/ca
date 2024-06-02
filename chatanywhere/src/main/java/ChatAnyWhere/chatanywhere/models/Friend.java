package ChatAnyWhere.chatanywhere.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank
    private String username;

    @JoinColumn
    @ManyToOne
    @JsonIgnoreProperties(value = "friendsList")
    @JsonIgnore
    private  User user;

    public static Friend toFriend(String username)
    {
        return  Friend.builder()
                .username(username)
                .build();
    }

}
