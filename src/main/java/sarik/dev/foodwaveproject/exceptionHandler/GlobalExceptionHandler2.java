//package sarik.dev.foodwaveproject.exceptionHandler;
//
//import org.springframework.dao.DataIntegrityViolationException;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.FieldError;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.context.request.WebRequest;
////import sarik.dev.foodwaveproject.exception.ResourceNotFoundException;
//
//import java.time.LocalDateTime;
//import java.util.HashMap;
//import java.util.Map;
//
//@ControllerAdvice
//public class GlobalExceptionHandler2 {
//
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getAllErrors().forEach((error) -> {
//            String fieldName = ((FieldError) error).getField();
//            String errorMessage = error.getDefaultMessage();
//            errors.put(fieldName, errorMessage);
//        });
//        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
//    }
//
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(DataIntegrityViolationException.class)
//    public ResponseEntity<Map<String, String>> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
//        Map<String, String> errors = new HashMap<>();
//        if (ex.getRootCause() != null) {
//            String errorMessage = ex.getRootCause().getMessage();
//            errors.put("error", "Data integrity violation: " + errorMessage);
//        }
//        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
//    }
//
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<Map<String, String>> handleGeneralException(Exception ex) {
//        Map<String, String> errors = new HashMap<>();
//        errors.put("error", "An unexpected error occurred: " + ex.getMessage());
//        return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
////    @ExceptionHandler(ResourceNotFoundException.class)
////    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
////        Map<String, Object> body = new HashMap<>();
////        body.put("timestamp", LocalDateTime.now());
////        body.put("status", HttpStatus.NOT_FOUND.value());
////        body.put("error", "Not Found");
////        body.put("message", ex.getMessage());
////        body.put("path", request.getDescription(false));
////
////        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
////    }
////
////    // Add a generic exception handler for unexpected errors
////    @ExceptionHandler(Exception.class)
////    public ResponseEntity<Object> handleException(Exception ex, WebRequest request) {
////        Map<String, Object> body = new HashMap<>();
////        body.put("timestamp", LocalDateTime.now());
////        body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
////        body.put("error", "Internal Server Error");
////        body.put("message", ex.getMessage());
////        body.put("path", request.getDescription(false));
////
////        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
////    }
//}
