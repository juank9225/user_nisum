package co.com.nisum.model.user.exceptionClass;

public class InvalidRequestException extends RuntimeException {
    private Integer errorCode;
    private String errorMessage;

    public InvalidRequestException(Integer errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

}
