package su.kometa.kometabackend.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import su.kometa.kometabackend.configs.ModelConfig;
import su.kometa.kometabackend.models.Model;
import su.kometa.kometabackend.repositories.ModelRepository;

import jakarta.annotation.PostConstruct;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ModelInitializationService {
    private final ModelRepository modelRepository;
    private final ModelConfig modelConfig;

    @PostConstruct
    public void initializeModels() {
        initializeOpenAIModels();
        initializeGeminiModels();
    }

    private void initializeOpenAIModels() {
        ModelConfig.OpenAI openai = modelConfig.getOpenai();
        if (openai != null && openai.getModels() != null) {
            for (String modelName : openai.getModels()) {
                if (!modelRepository.existsByName(modelName)) {
                    Model model = new Model(
                        modelName,
                        "openai",
                        true,
                        openai.getApi().getKey(),
                        openai.getApi().getUrl()
                    );
                    modelRepository.save(model);
                }
            }
        }
    }

    private void initializeGeminiModels() {
        ModelConfig.Gemini gemini = modelConfig.getGemini();
        if (gemini != null && gemini.getModels() != null) {
            for (String modelName : gemini.getModels()) {
                if (!modelRepository.existsByName(modelName)) {
                    Model model = new Model(
                        modelName,
                        "gemini",
                        true,
                        gemini.getApi().getKey(),
                        gemini.getApi().getUrl()
                    );
                    modelRepository.save(model);
                }
            }
        }
    }
} 