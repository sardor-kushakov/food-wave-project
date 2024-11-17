package sarik.dev.foodwaveproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sarik.dev.foodwaveproject.dto.BaseResponse;
import sarik.dev.foodwaveproject.dto.ErrorData;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice("sarik.dev")
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResponse<?>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> fieldErrors = new HashMap<>();
        for (FieldError fieldError : ex.getFieldErrors()) {
            fieldErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        ErrorData errorData = new ErrorData("Validation failed", fieldErrors);
        BaseResponse<?> response = new BaseResponse<>(errorData);

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<BaseResponse<?>> handleAuthenticationException(AuthenticationException ex) {
        ErrorData errorData = new ErrorData(ex.getMessage());
        BaseResponse<?> response = new BaseResponse<>(errorData);

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<BaseResponse<?>> handleAuthorizationException(AuthorizationException ex) {
        ErrorData errorData = new ErrorData(ex.getMessage());
        BaseResponse<?> response = new BaseResponse<>(errorData);

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<BaseResponse<?>> handleOrderNotFoundException(OrderNotFoundException ex) {
        ErrorData errorData = new ErrorData(ex.getMessage());
        BaseResponse<?> response = new BaseResponse<>(errorData);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<BaseResponse<?>> handleProductNotFoundException(ProductNotFoundException ex) {
        ErrorData errorData = new ErrorData(ex.getMessage());
        BaseResponse<?> response = new BaseResponse<>(errorData);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(PaymentProcessingException.class)
    public ResponseEntity<BaseResponse<?>> handlePaymentProcessingException(PaymentProcessingException ex) {
        ErrorData errorData = new ErrorData(ex.getMessage());
        BaseResponse<?> response = new BaseResponse<>(errorData);

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<BaseResponse<?>> handleTokenExpiredException(TokenExpiredException ex) {
        ErrorData errorData = new ErrorData(ex.getMessage());
        BaseResponse<?> response = new BaseResponse<>(errorData);

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<BaseResponse<?>> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ErrorData errorData = new ErrorData(ex.getMessage());
        BaseResponse<?> response = new BaseResponse<>(errorData);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<BaseResponse<?>> handleInvalidInputException(InvalidInputException ex) {
        ErrorData errorData = new ErrorData(ex.getMessage(), ex.getFieldErrors());
        BaseResponse<?> response = new BaseResponse<>(errorData);

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse<?>> handleGlobalExceptions(Exception ex) {
        ErrorData errorData = new ErrorData("An unexpected error occurred", ex.getMessage());
        BaseResponse<?> response = new BaseResponse<>(errorData);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
