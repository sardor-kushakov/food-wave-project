package sarik.dev.foodwaveproject.exception;

public class TokenExpiredException extends AuthenticationException {
    public TokenExpiredException(String message) {
        super(message);
    }
}
