package az.growlabtask.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@Data
@Builder
public class JwtResponse {
    @Value("${app.jwt.token.prefix}")
    private String type;
    private String accessToken;
    private String refreshToken;
    private String tokenType = type ;
    public JwtResponse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
