package su.kometa.kometabackend.configs;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Getter
@Setter
@Configuration
@ConfigurationProperties("apis")
public class ModelConfig {

    private OpenAI openAI;

    private Gemini gemini;

    @Getter
    @Setter
    public static class OpenAI {
        private List<String> models;
        private Api api;
    }

    @Getter
    @Setter
    public static class Gemini {
        private List<String> models;
        private Api api;
    }

    @Getter
    @Setter
    public static class Api {
        private String key;
        private String url;
    }
}