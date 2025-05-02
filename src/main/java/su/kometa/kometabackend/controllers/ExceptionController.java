package su.kometa.kometabackend.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import su.kometa.kometabackend.configs.CommonConfig;
import su.kometa.kometabackend.constants.ExceptionsConstants;
import su.kometa.kometabackend.dtos.response.ExceptionDTO;
import su.kometa.kometabackend.exceptions.BaseException;

@Slf4j
@RestControllerAdvice
public class ExceptionController {

    private final CommonConfig commonConfig;

    @Autowired
    public ExceptionController(CommonConfig commonConfig) {
        this.commonConfig = commonConfig;
    }

    private ResponseEntity<ExceptionDTO> buildErrorResponse(int errorCode, String message, HttpStatus status) {
        log.error(ExceptionsConstants.Messages.SERVER_EXCEPTION.getValue(), errorCode, status, message);
        return ResponseEntity.status(status).body(new ExceptionDTO(false, errorCode, message));
    }

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ExceptionDTO> handleBaseException(BaseException exception) {
        return buildErrorResponse(exception.getErrorCode(), exception.getMessage(), exception.getStatus());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionDTO> handleHttpMessageNotReadable() {
        return buildErrorResponse(ExceptionsConstants.API.EMPTY_BODY.getValue(), ExceptionsConstants.Messages.REQUEST_BODY_EMPTY.getValue(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ExceptionDTO> handleNoHandlerFoundException() {
        return buildErrorResponse(ExceptionsConstants.API.ROUTE_NOT_FOUND.getValue(), ExceptionsConstants.Messages.ROUTE_NOT_FOUND.getValue(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDTO> handleException(Exception exception) {
        String message = exception.getMessage();
        if (commonConfig.isDevelopment()) exception.printStackTrace();
        if (!commonConfig.isDevelopment()) message = ExceptionsConstants.Messages.INTERNAL_ERROR.getValue();

        log.error(ExceptionsConstants.Messages.SERVER_EXCEPTION_STACKTRACE.getValue(), exception);
        return buildErrorResponse(ExceptionsConstants.Server.UNKNOWN.getValue(), message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
