package su.kometa.kometabackend.validators;

import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LimitationsConstraints {
    LimitationsConstraint[] value();
} 