package su.kometa.kometabackend.configs;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("config")
@Data
public class CommonConfig {

    private String version;

    private String env;

    public boolean isDevelopment() {
        return "dev".equalsIgnoreCase(env);
    }
}
