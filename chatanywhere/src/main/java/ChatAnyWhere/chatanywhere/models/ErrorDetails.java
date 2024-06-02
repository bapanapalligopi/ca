package ChatAnyWhere.chatanywhere.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ErrorDetails {
    private Date timestamp;
    private String message;
    private String statusCode;
    private String details;
}
