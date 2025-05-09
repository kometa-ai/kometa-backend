package su.kometa.kometabackend.dtos.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import su.kometa.kometabackend.validators.LimitationsConstraint;

@Data
public class MessageCreateDTO {

    @NotBlank
    @LimitationsConstraint(type = "messageContent", isMin = true)
    @LimitationsConstraint(type = "messageContent", isMin = false)
    private String content;
}
