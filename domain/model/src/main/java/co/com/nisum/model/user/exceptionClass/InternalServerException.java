package co.com.nisum.model.user.exceptionClass;

public class InternalServerException extends RuntimeException {
    private Integer errorCode;
    private String errorMessage;

    public InternalServerException(Integer errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
