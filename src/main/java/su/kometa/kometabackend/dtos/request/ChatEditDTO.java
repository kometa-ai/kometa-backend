package su.kometa.kometabackend.dtos.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import su.kometa.kometabackend.validators.LimitationsConstraint;

@Data
public class ChatEditDTO {

    @NotBlank
    @LimitationsConstraint(type = "title", isMin = true)
    @LimitationsConstraint(type = "title", isMin = false)
    private String title;
}
