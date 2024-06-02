package ChatAnyWhere.chatanywhere.models;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WrappedUser {
    private  String username;
    private String userid;
    private Date createdOn;
    private String updatedOn;
    private List<Friend> friendsList;
    public static WrappedUser toWrappedUser(User user)
    {
        return WrappedUser.builder()
                .userid(user.getExtnuserid())
                .username(user.getUsername())
                .createdOn(user.getCreatedOn())
                .updatedOn(user.getUpdatedOn())
                .friendsList(user.getFriendsList())
                .build();
    }

}
