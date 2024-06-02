package ChatAnyWhere.chatanywhere.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank
    @Column(unique = true,nullable = false)
    @Email
    private  String username;
    @NotBlank
    private String extnuserid;
    @NotBlank
    private String password;
    @CreationTimestamp
    private Date createdOn;
    @UpdateTimestamp
    private String updatedOn;
    @OneToMany(mappedBy = "user")
    @JsonIgnoreProperties(value = "user")
    private List<Friend> friendsList;

}
