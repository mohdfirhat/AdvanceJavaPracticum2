package Exception;

/**
 * An Exception which extends the {@link RuntimeException}. Used to display the user input errors.
 */
public class InvalidInputException extends RuntimeException {
    /**
     * Used in the application to display the error to the user
     * @param message human-readable error which will be printed to user
     */
    public InvalidInputException(String message){
        super(message);
    }
}