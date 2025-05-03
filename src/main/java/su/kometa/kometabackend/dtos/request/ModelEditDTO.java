package su.kometa.kometabackend.dtos.request;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ModelEditDTO {

    @Size(min = 8, max = 256)
    private String apiKey;

    @Size(max = 2048)
    private long maxTokensPerChat;

    private boolean enabled;
}
