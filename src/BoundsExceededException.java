/**
 * Exception thrown when an operation would exceed a lower or upper bound.
 */
public class BoundsExceededException extends Exception {

    public BoundsExceededException() {
        super();
    }

    public BoundsExceededException(String message) {
        super(message);
    }
}
