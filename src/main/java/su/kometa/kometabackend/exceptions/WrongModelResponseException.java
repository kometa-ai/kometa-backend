package su.kometa.kometabackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import su.kometa.kometabackend.constants.ExceptionsConstants;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class WrongModelResponseException extends BaseException {

    public WrongModelResponseException() {
        super(ExceptionsConstants.Messages.WRONG_MODEL_RESPONSE.getValue(), UserNotFoundException.class.getAnnotation(ResponseStatus.class).value(), ExceptionsConstants.Model.WRONG_RESPONSE.getValue());
    }
}