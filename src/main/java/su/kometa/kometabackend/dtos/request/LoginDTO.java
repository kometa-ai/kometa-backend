package su.kometa.kometabackend.dtos.request;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginDTO {

    @Size(min = 3, max = 32)
    private String username;

    @Size(min = 1, max = 128)
    private String password;
}
