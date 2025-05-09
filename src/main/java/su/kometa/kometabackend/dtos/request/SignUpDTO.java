package su.kometa.kometabackend.dtos.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import su.kometa.kometabackend.validators.LimitationsConstraint;

@Data
public class SignUpDTO {

    @NotBlank
    @LimitationsConstraint(type = "username", isMin = true)
    @LimitationsConstraint(type = "username", isMin = false)
    private String username;

    @NotBlank
    @LimitationsConstraint(type = "password", isMin = true)
    @LimitationsConstraint(type = "password", isMin = false)
    private String password;
}
