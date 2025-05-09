package su.kometa.kometabackend.dtos.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import su.kometa.kometabackend.validators.LimitationsConstraint;

@Data
public class UserEditDTO {

    @NotBlank
    @LimitationsConstraint(type = "username", isMin = true)
    @LimitationsConstraint(type = "username", isMin = false)
    private String username;
}
