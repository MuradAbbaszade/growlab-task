package az.growlabtask.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;

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
