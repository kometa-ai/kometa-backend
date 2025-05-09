package su.kometa.kometabackend.dtos.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import su.kometa.kometabackend.validators.LimitationsConstraint;

@Data
public class ModelEditDTO {

    @NotBlank
    @LimitationsConstraint(type = "modelApiKey", isMin = true)
    @LimitationsConstraint(type = "modelApiKey", isMin = false)
    private String apiKey;

    @NotBlank
    @LimitationsConstraint(type = "modelTokens", isMin = true)
    @LimitationsConstraint(type = "modelTokens", isMin = false)
    private String maxTokensPerChat;

    private boolean enabled;
}
