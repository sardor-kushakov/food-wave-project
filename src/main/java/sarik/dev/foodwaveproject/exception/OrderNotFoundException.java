package sarik.dev.foodwaveproject.exception;

public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException(String message) {
        super(message);
    }

    public OrderNotFoundException(Long orderId) {
        super("Order with ID " + orderId + " not found");
    }
}
