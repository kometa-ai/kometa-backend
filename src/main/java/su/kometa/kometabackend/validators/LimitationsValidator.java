package su.kometa.kometabackend.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import su.kometa.kometabackend.constants.LimitationsConstants;

@Component
@RequiredArgsConstructor
public class LimitationsValidator implements ConstraintValidator<LimitationsConstraint, String> {
    private final LimitationsConstants limitationsConstants;
    private String type;
    private boolean isMin;

    @Override
    public void initialize(LimitationsConstraint constraintAnnotation) {
        this.type = constraintAnnotation.type();
        this.isMin = constraintAnnotation.isMin();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        int length = value.length();
        switch (type) {
            case "username":
                return isMin ? length >= limitationsConstants.getUsernameMin() : length <= limitationsConstants.getUsernameMax();
            case "password":
                return isMin ? length >= limitationsConstants.getPasswordMin() : length <= limitationsConstants.getPasswordMax();
            case "title":
                return isMin ? length >= limitationsConstants.getTitleMin() : length <= limitationsConstants.getTitleMax();
            case "messageContent":
                return isMin ? length >= limitationsConstants.getMessageContentMin() : length <= limitationsConstants.getMessageContentMax();
            case "modelApiKey":
                return isMin ? length >= limitationsConstants.getModelApiKeyMin() : length <= limitationsConstants.getModelApiKeyMax();
            default:
                return true;
        }
    }
} 