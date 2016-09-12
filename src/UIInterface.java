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
     * Updates the round in the graphical interface.
     * @param round The round to be updated.
     */
    void updateRound(int round);

    /**
     * Updates the informational message in the graphical interface.
     * @param message The message to be displayed.
     */
    void updateInstructions(String message);

    /**
     * Starts a new round, receiving an array of shapes to display to the user in order to be memorized.
     * @param order An array of shapes that is already sorted in the correct memorization order.
     */
    void startRound(Shape[] order);

    /**
     * Helper method to shuffle an array of Shape objects. Temporarily stored here until Game Engine or QA
     * can implement.
     * @param order An array of shapes that is already sorted in the correct memorization order.
     */
    void shuffle(Shape[] order);
}