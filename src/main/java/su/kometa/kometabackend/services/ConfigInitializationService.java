package su.kometa.kometabackend.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import su.kometa.kometabackend.configs.CommonConfig;
import su.kometa.kometabackend.models.Config;
import su.kometa.kometabackend.repositories.ConfigRepository;

import jakarta.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class ConfigInitializationService {
    private final ConfigRepository configRepository;
    private final CommonConfig commonConfig;

    @PostConstruct
    public void initializeConfig() {
        // Основные настройки
        initializeConfigValue("version", commonConfig.getVersion());
        initializeConfigValue("env", commonConfig.getEnv());
        initializeConfigValue("invite_only", String.valueOf(commonConfig.isInviteOnly()));
        initializeConfigValue("api_url", commonConfig.getApi().getUrl());
        initializeConfigValue("gateway_url", commonConfig.getGateway().getUrl());

        // Ограничения для username
        initializeConfigValue("username_min_length", String.valueOf(commonConfig.getLimitations().getUsername().getMinLength()));
        initializeConfigValue("username_max_length", String.valueOf(commonConfig.getLimitations().getUsername().getMaxLength()));

        // Ограничения для password
        initializeConfigValue("password_min_length", String.valueOf(commonConfig.getLimitations().getPassword().getMinLength()));
        initializeConfigValue("password_max_length", String.valueOf(commonConfig.getLimitations().getPassword().getMaxLength()));

        // Ограничения для title
        initializeConfigValue("title_min_length", String.valueOf(commonConfig.getLimitations().getTitle().getMinLength()));
        initializeConfigValue("title_max_length", String.valueOf(commonConfig.getLimitations().getTitle().getMaxLength()));

        // Ограничения для message content
        initializeConfigValue("message_content_min_length", String.valueOf(commonConfig.getLimitations().getMessageContent().getMinLength()));
        initializeConfigValue("message_content_max_length", String.valueOf(commonConfig.getLimitations().getMessageContent().getMaxLength()));

        // Ограничения для message attachments
        initializeConfigValue("message_attachments_min_amount", String.valueOf(commonConfig.getLimitations().getMessageAttachments().getMinAmount()));
        initializeConfigValue("message_attachments_max_amount", String.valueOf(commonConfig.getLimitations().getMessageAttachments().getMaxAmount()));

        // Ограничения для model key
        initializeConfigValue("model_key_min_length", String.valueOf(commonConfig.getLimitations().getModelKey().getMinLength()));
        initializeConfigValue("model_key_max_length", String.valueOf(commonConfig.getLimitations().getModelKey().getMaxLength()));

        // Ограничения для model tokens
        initializeConfigValue("model_tokens_min_amount", String.valueOf(commonConfig.getLimitations().getModelTokens().getMinAmount()));
        initializeConfigValue("model_tokens_max_amount", String.valueOf(commonConfig.getLimitations().getModelTokens().getMaxAmount()));
    }

    private void initializeConfigValue(String key, String value) {
        if (!configRepository.existsByKey(key)) {
            Config config = new Config(key, value);
            configRepository.save(config);
        }
    }
} 