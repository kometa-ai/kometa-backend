package su.kometa.kometabackend.dtos.request;

import jakarta.validation.constraints.Size;
import lombok.Data;
import su.kometa.kometabackend.constants.LimitationsConstants;

@Data
public class ModelEditDTO {

    @Size(min = LimitationsConstants.MODEL_API_KEY_MIN, max = LimitationsConstants.MODEL_API_KEY_MAX)
    private String apiKey;

    @Size(min = LimitationsConstants.MODEL_TOKENS_MIN, max = LimitationsConstants.MODEL_TOKENS_MAX)
    private long maxTokensPerChat;

    private boolean enabled;
}
