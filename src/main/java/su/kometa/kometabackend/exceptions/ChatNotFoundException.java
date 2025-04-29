
package su.kometa.kometabackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import su.kometa.kometabackend.constants.ExceptionsConstants;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ChatNotFoundException extends BaseException {

    public ChatNotFoundException() {
        super(ExceptionsConstants.Messages.CHAT_NOT_FOUND.getValue(), ChatNotFoundException.class.getAnnotation(ResponseStatus.class).value(), ExceptionsConstants.Chat.NOT_FOUND.getValue());
    }
}