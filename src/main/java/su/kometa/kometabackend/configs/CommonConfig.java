package su.kometa.kometabackend.configs;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("common")
public class CommonConfig {

    private String version;

    private String env;

    private boolean inviteOnly;

    private Api api;

    private Gateway gateway;

    @Data
    public static class Api {
        private String url;
    }

    @Data
    public static class Gateway {
        private String url;
    }

    public boolean isDevelopment() {
        return "dev".equalsIgnoreCase(env);
    }
}
