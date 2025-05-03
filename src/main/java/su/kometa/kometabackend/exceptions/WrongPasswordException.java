package su.kometa.kometabackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import su.kometa.kometabackend.constants.ExceptionsConstants;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class WrongPasswordException extends BaseException {

    public WrongPasswordException() {
        super(ExceptionsConstants.Messages.WRONG_PASSWORD.getValue(), UserNotFoundException.class.getAnnotation(ResponseStatus.class).value(), ExceptionsConstants.User.WRONG_PASSWORD.getValue());
    }
}