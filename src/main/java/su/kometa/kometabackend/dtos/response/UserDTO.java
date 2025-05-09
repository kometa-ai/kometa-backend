package su.kometa.kometabackend.dtos.response;

import lombok.Data;
import su.kometa.kometabackend.models.User;

@Data
public class UserDTO {

    private long id;

    private String username;

    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
    }
}
