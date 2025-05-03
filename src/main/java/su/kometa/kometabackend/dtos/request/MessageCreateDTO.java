package su.kometa.kometabackend.dtos.request;

import jakarta.validation.constraints.Size;
import lombok.Data;
import su.kometa.kometabackend.constants.LimitationsConstants;

@Data
public class MessageCreateDTO {

    @Size(min = LimitationsConstants.MESSAGE_CONTENT_MIN, max = LimitationsConstants.MESSAGE_CONTENT_MAX)
    private String content;
}
