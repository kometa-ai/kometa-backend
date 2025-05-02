package su.kometa.kometabackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import su.kometa.kometabackend.constants.ExceptionsConstants;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class NeedToAuthorizeException extends BaseException {

    public NeedToAuthorizeException() {
        super(ExceptionsConstants.Messages.NEED_TO_AUTHORIZE.getValue(), UserNotFoundException.class.getAnnotation(ResponseStatus.class).value(), ExceptionsConstants.User.NEED_TO_AUTHORIZE.getValue());
    }
}