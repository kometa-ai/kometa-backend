package su.kometa.kometabackend.services;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import su.kometa.kometabackend.configs.ModelConfig;
import su.kometa.kometabackend.exceptions.WrongModelResponseException;
import su.kometa.kometabackend.models.Chat;
import su.kometa.kometabackend.models.Message;
import su.kometa.kometabackend.models.Model;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Service
public class GeminiService {

    private final HttpClient httpClient;

    private final Model model;

    private final ObjectMapper objectMapper;

    @Autowired
    public GeminiService(ModelConfig modelConfig) {
        this.model = new Model();

        model.setApiURL(modelConfig.getGemini().getApi().getUrl());
        model.setApiKey(modelConfig.getGemini().getApi().getKey());

        this.httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();

        this.objectMapper = new ObjectMapper();
    }

    public Message sendMessage(Chat chat, String content) {
        List<ServiceRequestBody.Content> contents = chat.getMessages().stream()
                .map(message -> {
                    String role = message.getUser() == null ? "model" : "user";
                    List<ServiceRequestBody.Part> parts = List.of(new ServiceRequestBody.Part(message.getContent()));
                    return new ServiceRequestBody.Content(role, parts);
                }).toList();

        contents.add(new ServiceRequestBody.Content("user", List.of(new ServiceRequestBody.Part(content))));

        try {
            ServiceRequestBody body = new ServiceRequestBody(contents);
            String requestJson = objectMapper.writeValueAsString(body);

            this.model.setName(chat.getModel().getName());

            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create(this.model.getApiURL() + this.model.getName() + ":generateContent?key=" + this.model.getApiKey()))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestJson))
                    .build();

            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                ServiceResponseBody responseBody = objectMapper.readValue(response.body(), ServiceResponseBody.class);
                return new Message(null, this.model, chat, responseBody.getCandidates().getFirst().getContent().getParts().getFirst().getText());
            } else {
                throw new WrongModelResponseException();
            }
        } catch (Exception e) {
            throw new WrongModelResponseException();
        }
    }

    @Data
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    static class ServiceRequestBody {

        private List<Content> contents;

        @Data
        @AllArgsConstructor
        public static class Content {
            private String role;
            List<Part> parts;
        }

        @Data
        @AllArgsConstructor
        public static class Part {
            private String text;
        }
    }

    @Data
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    static class ServiceResponseBody {
        private List<Candidate> candidates;
        private UsageMetadata usageMetadata;
        private String modelVersion;

        @Data
        @AllArgsConstructor
        public static class Candidate {
            private Content content;
            private String finishReason;
            private double avgLogprobs;
        }

        @Data
        @AllArgsConstructor
        public static class Content {
            private List<Part> parts;
            private String role;
        }

        @Data
        @AllArgsConstructor
        public static class Part {
            private String text;
        }

        @Data
        @AllArgsConstructor
        public static class UsageMetadata {
            private int promptTokenCount;
            private int candidatesTokenCount;
            private int totalTokenCount;
            private List<TokenDetails> promptTokensDetails;
            private List<TokenDetails> candidatesTokensDetails;
        }

        @Data
        @AllArgsConstructor
        public static class TokenDetails {
            private String modality;
            private int tokenCount;
        }
    }
}

