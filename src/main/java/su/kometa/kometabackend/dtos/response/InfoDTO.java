package su.kometa.kometabackend.dtos.response;

import lombok.Data;
import su.kometa.kometabackend.configs.CommonConfig;

@Data
public class InfoDTO {

    private String version;

    private boolean inviteOnly;

    private Object api;

    private Object gateway;

    public InfoDTO(CommonConfig config) {
        this.version = config.getVersion();
        this.inviteOnly = config.isInviteOnly();
        this.api = config.getApi();
        this.gateway = config.getGateway();
    }
}
