package az.growlabtask.dto.request;

import az.growlabtask.enums.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class RoleRequest {
    private String role;
    private Status status;
    private Long userId;
}
