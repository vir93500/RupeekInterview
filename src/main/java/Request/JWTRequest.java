package Request;

import lombok.Data;

@Data
public class JWTRequest {
    private String username;
    private String password;
}
