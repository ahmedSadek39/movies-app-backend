package movie.security.payload.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse {

    private int statusCode;
    private String clientMessage;
    private String developerMessage;
    private String messageId;
    private Object body;
    private String timestamp;

}