package su.kometa.kometabackend.configs;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "common")
public class CommonConfig {
    private String version;
    private String env;
    private boolean inviteOnly;
    private Api api;
    private Gateway gateway;
    private Limitations limitations;

    @Data
    public static class Api {
        private String url;
    }

    @Data
    public static class Gateway {
        private String url;
    }

    @Data
    public static class Limitations {
        private Username username;
        private Password password;
        private Title title;
        private MessageContent messageContent;
        private MessageAttachments messageAttachments;
        private ModelKey modelKey;
        private ModelTokens modelTokens;
    }

    @Data
    public static class Username {
        private int minLength;
        private int maxLength;
    }

    @Data
    public static class Password {
        private int minLength;
        private int maxLength;
    }

    @Data
    public static class Title {
        private int minLength;
        private int maxLength;
    }

    @Data
    public static class MessageContent {
        private int minLength;
        private int maxLength;
    }

    @Data
    public static class MessageAttachments {
        private int minAmount;
        private int maxAmount;
    }

    @Data
    public static class ModelKey {
        private int minLength;
        private int maxLength;
    }

    @Data
    public static class ModelTokens {
        private int minAmount;
        private int maxAmount;
    }

    public boolean isDevelopment() {
        return "dev".equalsIgnoreCase(env);
    }
}
