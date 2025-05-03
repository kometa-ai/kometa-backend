package su.kometa.kometabackend.dtos.request;

import jakarta.validation.constraints.Size;
import lombok.Data;
import su.kometa.kometabackend.constants.LimitationsConstants;

@Data
public class LoginDTO {

    @Size(min = LimitationsConstants.USERNAME_MIN, max = LimitationsConstants.USERNAME_MAX)
    private String username;

    @Size(min = LimitationsConstants.PASSWORD_MIN, max = LimitationsConstants.PASSWORD_MAX)
    private String password;
}
