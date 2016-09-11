/**
 * Defines the interface for a user interface class that displays visual elements and listens to user input.
 */
public interface UIInterface {
    /**
     * Constructor that initializes necessary data for the game to begin soon.
     */
    public void UI();

    /**
     * Begins a new game, effectively resetting previous statistics like the score.
     */
    public void startGame();

     /**
     * Updates the score ui element.
     * @param score The integer representing the current user's score.
     */
    public void updateScore(int score);

    /**
     * Receives an array of four random cakes (Shapes) to display in order to be memorized by the user.
     * @param order The array of Shape's that is to be displayed to the user for a brief moment.
     */
    public void displayOrder(Shape[] order);
}