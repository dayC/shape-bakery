import java.util.concurrent.ThreadLocalRandom;

/**
 * Generates and validates question and answer sequences for Shape objects.
 */
public class ShapeQA implements QAInterface<Shape> {
    private Shape[] options; /**< The options last generated. */
    private int ansIdx; /**< Index of the correct answer. */

    public Shape[] getOptions() {
        return options;
    }

    public void generate(int n) {
        options = new Shape[n];

        for (int i = 0; i < n; i++) {
            options[i] = Shape.values()[ThreadLocalRandom.current().nextInt(1, Shape.values().length)];
        }

        ansIdx = ThreadLocalRandom.current().nextInt(0, n);
    }

    public boolean validate(int idx) {
        return idx == ansIdx;
    }

    public String getQuestion() {
        return "Which of these is a " + options[ansIdx].getReadable() + '?';
    }
}
