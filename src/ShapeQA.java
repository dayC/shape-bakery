import java.util.concurrent.ThreadLocalRandom;
import java.util.Arrays;

/**
 * Generates and validates question and answer sequences for Shape objects.
 */
public class ShapeQA implements QAInterface<Shape> {
    private Shape[] sequence; /**< The sequence last generated. */

    /**
     * Generates a randomized array of shapes of length n.
     * @param n = Desired sequence length.
     * @return A randomized array of shapes of length n.
     */
    public Shape[] generate(int n) {
        sequence = new Shape[n];

        for (int i = 0; i < n; i++) {
            sequence[i] = Shape.values()[ThreadLocalRandom.current().nextInt(1, Shape.values().length)];
        }

        return sequence;
    }

    /**
     * Validates a String of answers my comparing it with the correct answer.
     * @param answer = The proposed answer sequence as an array of strings.
     * @return True if the supplied answer matches the correct answer, otherwise false.
     */
    public boolean validate(String[] answer) {
        return Arrays.equals(answer, dumpReadable());
    }

    /**
     * Utility function to dump all readable Shape strings into an array of Strings,
     * since validate() compares two strings if they are equal.
     * @return An array of the readable strings.
     */
    private String[] dumpReadable() {
        String[] readableArray = new String[sequence.length];

        for (int i = 0; i < sequence.length; i++) {
            readableArray[i] = sequence[i].getReadable();
        }

        return readableArray;
    }

    /**
     * Returns the string "Memorize the following sequence."
     * @return The string "Memorize the following sequence:"
     */
    public String getQuestion() {
        return "Memorize the following sequence:";
    }
}
