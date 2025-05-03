package su.kometa.kometabackend.dtos.request;

import jakarta.validation.constraints.Size;
import lombok.Data;
import su.kometa.kometabackend.constants.LimitationsConstants;

@Data
public class UserEditDTO {

    @Size(min = LimitationsConstants.USERNAME_MIN, max = LimitationsConstants.USERNAME_MAX)
    private String username;
}
