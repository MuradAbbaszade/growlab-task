package az.growlabtask.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@Data
@Builder
@NoArgsConstructor
public class JwtResponse {
    private final String TYPE = "Bearer";
    private String accessToken;
    private String refreshToken;
    public JwtResponse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
