package az.growlabtask.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignUpRequest {
    private String username;
    private String email;
    private String password;
    private String name;
    private String surname;
}
