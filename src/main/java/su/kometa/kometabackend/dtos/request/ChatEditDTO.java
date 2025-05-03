package su.kometa.kometabackend.dtos.request;

import jakarta.validation.constraints.Size;
import lombok.Data;
import su.kometa.kometabackend.constants.LimitationsConstants;

@Data
public class ChatEditDTO {

    @Size(min = LimitationsConstants.TITLE_MIN, max = LimitationsConstants.TITLE_MAX)
    private String title;
}
