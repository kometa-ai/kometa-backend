
package su.kometa.kometabackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import su.kometa.kometabackend.constants.ExceptionsConstants;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MessageNotFoundException extends BaseException {

    public MessageNotFoundException() {
        super(ExceptionsConstants.Messages.MESSAGE_NOT_FOUND.getValue(), MessageNotFoundException.class.getAnnotation(ResponseStatus.class).value(), ExceptionsConstants.Message.NOT_FOUND.getValue());
    }
}