package su.kometa.kometabackend.dtos.response;

import lombok.Data;
import su.kometa.kometabackend.models.Chat;

@Data
public class OkDTO {

    private boolean ok;

    public OkDTO() {
        this.ok = true;
    }
}