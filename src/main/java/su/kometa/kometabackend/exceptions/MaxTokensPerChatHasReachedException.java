package su.kometa.kometabackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import su.kometa.kometabackend.constants.ExceptionsConstants;

@ResponseStatus(HttpStatus.BANDWIDTH_LIMIT_EXCEEDED)
public class MaxTokensPerChatHasReachedException extends BaseException {

    public MaxTokensPerChatHasReachedException() {
        super(ExceptionsConstants.Messages.MODEL_MAX_TOKENS_PER_CHAT_REACHED.getValue(), MaxTokensPerChatHasReachedException.class.getAnnotation(ResponseStatus.class).value(), ExceptionsConstants.Model.MAX_TOKENS_PER_CHAT_REACHED.getValue());
    }
}