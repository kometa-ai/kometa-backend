package su.kometa.kometabackend.dtos.response;

import lombok.Data;

@Data
public class ExceptionDTO {
    private boolean ok;

    private int code;

    private String message;

    public ExceptionDTO(boolean ok, int code, String message) {
        this.ok = ok;
        this.code = code;
        this.message = message;
    }
}