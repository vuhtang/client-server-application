package exceptions;

/**
 * Exception which throws in case of incorrect input data.
 */
public class InvalidInputException extends Exception {
    /**
     * The message with the description of thrown exception.
     */
    private String message;

    /**
     * Initialized a new exception with the given message.
     *
     * @param message the message with the description of thrown exception
     */
    public InvalidInputException(String message) {
        super(message);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
