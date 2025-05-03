package su.kometa.kometabackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import su.kometa.kometabackend.constants.ExceptionsConstants;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ModelDisabledException extends BaseException {

    public ModelDisabledException() {
        super(ExceptionsConstants.Messages.MODEL_DISABLED.getValue(), ModelDisabledException.class.getAnnotation(ResponseStatus.class).value(), ExceptionsConstants.Model.DISABLED.getValue());
    }
}