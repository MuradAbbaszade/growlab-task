package az.growlabtask.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class AttributeRequest {
    @NotBlank
    private String attribute;
}
