
package su.kometa.kometabackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import su.kometa.kometabackend.constants.ExceptionsConstants;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserWithThisUsernameAlreadyExistsException extends BaseException {

    public UserWithThisUsernameAlreadyExistsException() {
        super(ExceptionsConstants.Messages.USER_WITH_THIS_USERNAME_ALREADY_EXISTS.getValue(), UserWithThisUsernameAlreadyExistsException.class.getAnnotation(ResponseStatus.class).value(), ExceptionsConstants.User.ALREADY_EXISTS_WITH_THIS_USERNAME.getValue());
    }
}