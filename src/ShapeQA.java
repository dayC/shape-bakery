import java.util.concurrent.ThreadLocalRandom;

/**
 * Generates and validates question and answer sequences for Shape objects.
 */
public class ShapeQA implements QAInterface<Shape> {
    private Shape[] sequence; /**< The sequence last generated. */

    public void generate(int n) {
        sequence = new Shape[n];

        for (int i = 0; i < n; i++) {
            sequence[i] = Shape.values()[ThreadLocalRandom.current().nextInt(1, Shape.values().length)];
        }
    }

    public boolean validate(Shape[] answer) {
        return sequence.equals(answer);
    }

    public String getQuestion() {
        return "Memorize the following sequence:";
    }
}
