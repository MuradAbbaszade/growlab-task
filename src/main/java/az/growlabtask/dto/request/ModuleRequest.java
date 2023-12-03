package az.growlabtask.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@NoArgsConstructor
public class ModuleRequest {
    @NotBlank
    private String name;
}
