package su.kometa.kometabackend.configs;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Data
@Configuration
@ConfigurationProperties(prefix = "apis")
public class ModelConfig {
    private OpenAI openai;
    private Gemini gemini;

    @Data
    public static class OpenAI {
        private List<String> models;
        private Api api;
    }

    @Data
    public static class Gemini {
        private List<String> models;
        private Api api;
    }

    @Data
    public static class Api {
        private String key;
        private String url;
    }
}