
package su.kometa.kometabackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import su.kometa.kometabackend.constants.ExceptionsConstants;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class WrongPasswordException extends BaseException {

    public WrongPasswordException() {
        super(ExceptionsConstants.Messages.USER_NOT_FOUND.getValue(), UserNotFoundException.class.getAnnotation(ResponseStatus.class).value(), ExceptionsConstants.User.NOT_FOUND.getValue());
    }
}