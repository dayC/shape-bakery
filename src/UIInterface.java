/**
 * Defines the interface for a user interface class that displays graphical user interface elements
 * and listens for user input.
 */
public interface UIInterface {
    /**
     * Updates the score in the graphical interface.
     * @param score The score to be updated.
     */
     void updateScore(int score);

    /**
     * Starts a new round, receiving an array of shapes to display to the user in order to be memorized.
     * @param order An array of shapes that is already sorted in the correct memorization order.
     */
    void startRound(Shape[] order);
}