package su.kometa.kometabackend.constants;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import su.kometa.kometabackend.configs.CommonConfig;

@Component
@RequiredArgsConstructor
public class LimitationsConstants {
    private final CommonConfig commonConfig;

    public int getUsernameMax() {
        return commonConfig.getLimitations().getUsername().getMaxLength();
    }

    public int getUsernameMin() {
        return commonConfig.getLimitations().getUsername().getMinLength();
    }

    public int getPasswordMax() {
        return commonConfig.getLimitations().getPassword().getMaxLength();
    }

    public int getPasswordMin() {
        return commonConfig.getLimitations().getPassword().getMinLength();
    }

    public int getTitleMax() {
        return commonConfig.getLimitations().getTitle().getMaxLength();
    }

    public int getTitleMin() {
        return commonConfig.getLimitations().getTitle().getMinLength();
    }

    public int getMessageContentMax() {
        return commonConfig.getLimitations().getMessageContent().getMaxLength();
    }

    public int getMessageContentMin() {
        return commonConfig.getLimitations().getMessageContent().getMinLength();
    }

    public int getMessageAttachmentsMax() {
        return commonConfig.getLimitations().getMessageAttachments().getMaxAmount();
    }

    public int getMessageAttachmentsMin() {
        return commonConfig.getLimitations().getMessageAttachments().getMinAmount();
    }

    public int getModelApiKeyMax() {
        return commonConfig.getLimitations().getModelKey().getMaxLength();
    }

    public int getModelApiKeyMin() {
        return commonConfig.getLimitations().getModelKey().getMinLength();
    }

    public int getModelTokensMax() {
        return commonConfig.getLimitations().getModelTokens().getMaxAmount();
    }

    public int getModelTokensMin() {
        return commonConfig.getLimitations().getModelTokens().getMinAmount();
    }
}