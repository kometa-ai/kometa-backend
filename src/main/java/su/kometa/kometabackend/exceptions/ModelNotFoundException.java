package su.kometa.kometabackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import su.kometa.kometabackend.constants.ExceptionsConstants;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ModelNotFoundException extends BaseException {

    public ModelNotFoundException() {
        super(ExceptionsConstants.Messages.MODEL_NOT_FOUND.getValue(), ModelNotFoundException.class.getAnnotation(ResponseStatus.class).value(), ExceptionsConstants.Model.NOT_FOUND.getValue());
    }
}