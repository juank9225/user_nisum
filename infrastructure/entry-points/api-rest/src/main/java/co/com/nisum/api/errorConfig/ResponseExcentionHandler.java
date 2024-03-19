package co.com.nisum.api.errorConfig;

import co.com.nisum.model.user.exceptionClass.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ResponseExcentionHandler extends ResponseEntityExceptionHandler{

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorMsg> handleUserNotFoundException(UserNotFoundException errorMensaje) {
        ErrorMsg errorResponse = new ErrorMsg(HttpStatus.NOT_FOUND, errorMensaje.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(InvalidRequestException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ErrorMsg> handleInvalidRequestException(InvalidRequestException errorMensaje) {
        ErrorMsg errorResponse = new ErrorMsg(HttpStatus.BAD_REQUEST,errorMensaje.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(TokenExpiredException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ErrorMsg> handleTokenExpiredException(TokenExpiredException errorMensaje) {
        ErrorMsg errorResponse = new ErrorMsg(HttpStatus.UNAUTHORIZED, errorMensaje.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }

    @ExceptionHandler(InternalServerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorMsg> handleInternalServerException(InternalServerException errorMensaje) {
        ErrorMsg errorResponse = new ErrorMsg(HttpStatus.INTERNAL_SERVER_ERROR, errorMensaje.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorMsg> handleBadRequestException(BadRequestException errorMensaje) {
        ErrorMsg errorResponse = new ErrorMsg(HttpStatus.BAD_REQUEST, errorMensaje.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}
