/**
 * Defines the interface for a user interface class that displays graphical elements and listens for user input.
 */
public interface UIInterface {
    /**
     * Starts a new game, resetting all statistics to zero and preparing a new set of questions.
     * @param order An array of Shape objects, whose sequential indices represent the correct order to be picked.
     */
    public void startGame(Shape[] order);

    /**
     * Renders the order array in random order, and uses a timer to highlight the correct order briefly to the user.
     * @param order An array of Shape objects, whose sequential indices represent the correct order to be picked.
     */
    public void displayOrder(Shape[] order);

    /**
     * Updates the score in the graphical interface.
     * @param score The score to be displayed
     */
    public void updateScore(int score);
}