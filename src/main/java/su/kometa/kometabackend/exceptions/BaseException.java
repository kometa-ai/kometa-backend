package su.kometa.kometabackend.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class BaseException extends RuntimeException {

    public int errorCode;

    public HttpStatus status;

    public String message;

    public BaseException(String message, HttpStatus status, int errorCode) {
        this.message = message;
        this.status = status;
        this.errorCode = errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
