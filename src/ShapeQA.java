import java.util.concurrent.ThreadLocalRandom;
import java.util.Arrays;

/**
 * Generates and validates question and answer sequences for Shape objects.
 */
public class ShapeQA implements QAInterface<Shape> {
    private Shape[] sequence; /**< The sequence last generated. */

    public Shape[] generate(int n) {
        sequence = new Shape[n];

        for (int i = 0; i < n; i++) {
            sequence[i] = Shape.values()[ThreadLocalRandom.current().nextInt(1, Shape.values().length)];
        }

        return sequence;
    }

    public boolean validate(String[] answer) {
        return Arrays.equals(answer, dumpReadable());
    }

    /**
     * Utility function to dump all readable Shape strings into an array,
     * @return An array of the readable strings.
     */
    private String[] dumpReadable() {
        String[] readableArray = new String[sequence.length];

        for (int i = 0; i < sequence.length; i++) {
            readableArray[i] = sequence[i].getReadable();
        }

        return readableArray;
    }

    public String getQuestion() {
        return "Memorize the following sequence:";
    }
}
