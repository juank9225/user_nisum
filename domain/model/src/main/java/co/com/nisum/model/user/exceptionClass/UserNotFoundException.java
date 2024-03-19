package co.com.nisum.model.user.exceptionClass;

public class UserNotFoundException extends RuntimeException {
    private Integer errorCode;
    private String errorMessage;

    public UserNotFoundException(Integer errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
