package su.kometa.kometabackend.dtos.response;

import lombok.Data;

@Data
public class AccessTokenDTO {

    private String accessToken;

    public AccessTokenDTO(String accessToken) {
        this.accessToken = accessToken;
    }
}
