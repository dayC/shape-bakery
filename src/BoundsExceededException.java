/**
 * Exception thrown when an operation would exceed a lower or upper bound.
 */
public class BoundsExceededException extends Exception {

    /**
     * Overloaded method with no arguments.
     */
    public BoundsExceededException() {
        super();
    }

    /**
     * @param message String message of the exception.
     */
    public BoundsExceededException(String message) {
        super(message);
    }
}
