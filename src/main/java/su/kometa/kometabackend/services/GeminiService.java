package su.kometa.kometabackend.services;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import su.kometa.kometabackend.configs.ModelConfig;
import su.kometa.kometabackend.exceptions.WrongModelResponseException;
import su.kometa.kometabackend.models.Chat;
import su.kometa.kometabackend.models.Message;
import su.kometa.kometabackend.models.Model;
import su.kometa.kometabackend.repositories.MessageRepository;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class GeminiService {

    private final HttpClient httpClient;

    private final Model model;

    private final ObjectMapper objectMapper;

    private final MessageRepository messageRepository;

    @Autowired
    public GeminiService(ModelConfig modelConfig, MessageRepository messageRepository) {
        this.model = new Model();

        model.setApiURL(modelConfig.getGemini().getApi().getUrl());
        model.setApiKey(modelConfig.getGemini().getApi().getKey());

        this.httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();

        this.objectMapper = new ObjectMapper();
        this.messageRepository = messageRepository;
    }

    public Message sendMessage(Chat chat, String content) {
        List<ServiceRequestBody.Content> contents = new ArrayList<>(chat.getMessages().stream()
                .map(message -> {
                    String role = message.getUser() == null ? "model" : "user";
                    List<ServiceRequestBody.Part> parts = List.of(new ServiceRequestBody.Part(message.getContent()));
                    return new ServiceRequestBody.Content(role, parts);
                }).toList());

        contents.add(new ServiceRequestBody.Content("user", List.of(new ServiceRequestBody.Part(content))));

        List<ServiceRequestBody.Tool> tools = List.of(
                new ServiceRequestBody.Tool(new ServiceRequestBody.Tool.GoogleSearch())
        );

        try {
            ServiceRequestBody body = new ServiceRequestBody(contents, tools);
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
                return new Message(null, chat.getModel(), chat, responseBody.getCandidates().getFirst().getContent().getParts().getFirst().getText(), null);
            } else {
                throw new WrongModelResponseException();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new WrongModelResponseException();
        }
    }

    @Data
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    static class ServiceRequestBody {

        private List<Content> contents;
        private List<Tool> tools;

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
        
        @Data
        @AllArgsConstructor
        public static class Tool {
            private GoogleSearch google_search;

            @Data
            @JsonIgnoreProperties(ignoreUnknown = true)
            public static class GoogleSearch {

            }
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    static class ServiceResponseBody {

        private List<Candidate> candidates;
        private UsageMetadata usageMetadata;
        private String modelVersion;

        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Candidate {
            private Content content;
            private String finishReason;
            private double avgLogprobs;
        }

        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Content {
            private List<Part> parts;
            private String role;
        }

        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Part {
            private String text;
        }

        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class UsageMetadata {
            private int promptTokenCount;
            private int candidatesTokenCount;
            private int totalTokenCount;
            private List<TokenDetails> promptTokensDetails;
            private List<TokenDetails> candidatesTokensDetails;
        }

        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class TokenDetails {
            private String modality;
            private int tokenCount;
        }
    }
}