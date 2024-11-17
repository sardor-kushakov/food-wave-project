package sarik.dev.foodwaveproject.exception;

import java.util.Map;

public class InvalidInputException extends RuntimeException {

    private final Map<String, String> fieldErrors; // Maydon xatolari (optional)

    // Umumiy xabar uchun konstruktor
    public InvalidInputException(String message) {
        super(message);
        this.fieldErrors = null;
    }

    // Xabar va maydon xatolari uchun konstruktor
    public InvalidInputException(String message, Map<String, String> fieldErrors) {
        super(message);
        this.fieldErrors = fieldErrors;
    }

    // Maydon xatolarini olish
    public Map<String, String> getFieldErrors() {
        return fieldErrors;
    }
}
