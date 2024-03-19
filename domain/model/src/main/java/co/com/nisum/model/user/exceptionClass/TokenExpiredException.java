package co.com.nisum.model.user.exceptionClass;

public class TokenExpiredException extends RuntimeException {
    private Integer errorCode;
    private String errorMessage;

    public TokenExpiredException(Integer errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
