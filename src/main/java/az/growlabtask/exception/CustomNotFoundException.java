package az.growlabtask.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CustomNotFoundException extends RuntimeException {
    private String message;
}
