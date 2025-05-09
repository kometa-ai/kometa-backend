package su.kometa.kometabackend.constants;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import su.kometa.kometabackend.repositories.ConfigRepository;

@Component
@RequiredArgsConstructor
public class LimitationsConstants {
    private final ConfigRepository configRepository;

    private int getIntValue(String key) {
        return Integer.parseInt(configRepository.findByKey(key).getValue());
    }

    public int getUsernameMax() {
        return getIntValue("username_max_length");
    }

    public int getUsernameMin() {
        return getIntValue("username_min_length");
    }

    public int getPasswordMax() {
        return getIntValue("password_max_length");
    }

    public int getPasswordMin() {
        return getIntValue("password_min_length");
    }

    public int getTitleMax() {
        return getIntValue("title_max_length");
    }

    public int getTitleMin() {
        return getIntValue("title_min_length");
    }

    public int getMessageContentMax() {
        return getIntValue("message_content_max_length");
    }

    public int getMessageContentMin() {
        return getIntValue("message_content_min_length");
    }

    public int getMessageAttachmentsMax() {
        return getIntValue("message_attachments_max_amount");
    }

    public int getMessageAttachmentsMin() {
        return getIntValue("message_attachments_min_amount");
    }

    public int getModelApiKeyMax() {
        return getIntValue("model_key_max_length");
    }

    public int getModelApiKeyMin() {
        return getIntValue("model_key_min_length");
    }

    public int getModelTokensMax() {
        return getIntValue("model_tokens_max_amount");
    }

    public int getModelTokensMin() {
        return getIntValue("model_tokens_min_amount");
    }
}