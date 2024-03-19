package co.com.nisum.model.user.exceptionClass;

public class BadRequestException extends RuntimeException {
    private Integer errorCode;
    private String errorMessage;

    public BadRequestException(Integer errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
