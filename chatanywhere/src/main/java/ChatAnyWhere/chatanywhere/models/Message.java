package ChatAnyWhere.chatanywhere.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.AccessType;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Message
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;
    @NotBlank
    private  String messageTxnId;
    @NotBlank
    private String senderUsername;
    @NotBlank
    private String receiverUsername;
    @ManyToOne()
    @JoinColumn
    @JsonIgnoreProperties(value = "")
    @JsonIgnore
    private User sender;
    @ManyToOne()
    @JoinColumn
    @JsonIgnoreProperties(value = "")
    @JsonIgnore
    private User receiver;
    @NotBlank
    private String message;
    @CreationTimestamp
    private Date createdOn;

}
