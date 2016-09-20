import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
/**
 *  The main driver of the program.  Sets up the user interface, shapes array, userShapeSelections array.
 *  \todo getGameDuration, getTurnsRemaining, nextTurn, and setDuration are currently not used.
 *  As of this point the game advances forever, need to use these to detect when the user has
 *  completed the game and display a game over screen. Also, setCakeSequenceLength and getCakeSequenceLength are unused
 *  and probably not needed.
 */
public class GameEngine extends JApplet {
    UI userInterface;    ///<A call to the UI class to create the user interface.
    //!Array of shape objects which is used to generate the sequence of shapes.
    Shape[] shapes = new Shape[]{Shape.CIRCLE, Shape.PENTAGON, Shape.RHOMBUS, Shape.TRAPEZOID};
    //!used to store player's selected sequence of shapes.
    ArrayList<String> userShapeSelections = new ArrayList<String>();

    // GAME_DURATION is 20 by default
    private int gameDuration = 20;
    private final int MIN_GAME_DURATION = 1;
    private final int MAX_GAME_DURATION = 40;

    // Turns remaining
    private int turnsRemaining = this.gameDuration;

    // CAKE_SEQUENCE_LENGTH is 4 by default
    private int cakeSequenceLength = 4;
    private final int MIN_CAKE_SEQUENCE_LENGTH = 1;
    private final int MAX_CAKE_SEQUENCE_LENGTH = 8;

    /**
     *  Gets the game duration.
     *  @return gameDuration
     */
    public int getGameDuration()
    {
        return this.gameDuration;
    }

    /**
     *  Gets the turns remaining.
     *  @return this.turnsRemaining
     */
    public int getTurnsRemaining()
    {
        return this.turnsRemaining;
    }

    /**
     *  Gets the length of the sequence of cake shapes.
     *  @return cakeSequenceLength
     */
    public int getCakeSequenceLength()
    {
        return cakeSequenceLength;
    }

    /**
     *  Decrements and returns the number of turns remaining to advance to the next turn.
     *  @return --this.turnsRemaining
     */
    public int nextTurn()
    {
        return --this.turnsRemaining;
    }

    private boolean setDuration(int turns)
    {
        if (turns >= MIN_GAME_DURATION && turns <= MAX_GAME_DURATION)
        {
            this.gameDuration = turns;
            return true;
        }
        else
        {
            return false;
        }
    }

    private boolean setCakeSequenceLength(int length)
    {
        if (length >= MIN_CAKE_SEQUENCE_LENGTH && length <= MAX_CAKE_SEQUENCE_LENGTH)
        {
            this.cakeSequenceLength = length;
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Checks after each user input whether or not it is correct
     * @param correctOrder the randomly generated sequence the user needs to have correct
     * @return true if correct or false if incorrect
     */
    public boolean checkforCorrectnessSoFar(Shape[] correctOrder) {
        int i = 0;
        while (i < correctOrder.length && i < this.userShapeSelections.size()) {
            if (!correctOrder[i].getReadable().equals(this.userShapeSelections.get(i))) {
                userInterface.getButton(this.userShapeSelections.get(i)).setBackground(Color.RED);
                return false;
            }
            i++;
        }
        return true;
    }

    /**
     *  Initializes the start of the game, calls createGUI(), catches an exception if createGUI didn't complete successfully
     */
    public void init() {
        try {
            SwingUtilities.invokeAndWait(new Runnable()
            {
                /**
                 * Sets the size of the game to 800 pixels by 300 pixels, calls createGUI()
                 * and starts the game with the initial order (shapes).
                 */
                public void run() {
                    resize(800, 300);
                    createGUI();
                    userInterface.startGame(shapes);
                }
            });
        } catch (Exception e) {
            System.err.println("createGUI didn't complete successfully");
        }
    }

    //Creates the new UI, makes it visible, and sets its content to userInterface.
    private void createGUI() {
        userInterface = new UI(this);
        userInterface.setOpaque(true);
        setContentPane(userInterface);
    }

    /**
     * Clears the guesses in the user shape selections
     */
    public void clearGuesses() {
        this.userShapeSelections.clear();
    }

    /**
     * Adds user input to ArrayList used to check with generated sequence
     * @param shape String representation of user input
     */
    public void addShape(String shape) {

        userShapeSelections.add(shape);
        for (String s : userShapeSelections) {
            System.out.print(s + ", ");
        }
        System.out.println();
    }
    
}
/**
 * \mainpage Shapes-Bakery
 * \section group Group Members:
 * \subsection members Zoltan Batoczki, Preston Peterson, and Mattias Huber
 *
 * <a href="https://github.com/prestonpeterson/shape-bakery">Group GitHub Repository</a>

 * \section UserInstructions User Instructions
 * The program will display a number of different shapes, and highlight the correct sequence.
 * The user must input the sequence to be advanced to the next level.
 * If the user inputs the incorrect sequence the game will restart.
 * \section LearningObjectives Learning Objectives
 * - Image Recognition
 *
 * - Pattern Recognition
 *
 * - Memorization
 *
 * - Problem Solving
 *
 * \section section1 Describe overall state of the project. Determine what has been done.
 Application has a welcome prompt, a score field, and then shows 4 shapes and highlights them in a certain sequence.  Prompts on the bottom of the screen direct the user to the goal of the game which is to click on the shapes following the sequence given.  When we first ran the program, after selecting the correct sequence the program does not detect that the correct sequence has been entered.  Documentation was provided with a doxygen file.

 GameEngine: Main driver of the program.  Creates UI class called userInterface.  Creates a shapes array containing circle, pentagon, rhombus, and trapezoid shape.  Sets the game duration to 20 (default) and sets turns remaining to game duration, which we assume is to represent 20 'turns' or levels of the game.  Sets cakeSequenceLength to 4, which is the length of the displayed sequence of shapes.  Then initializes a SwingUtilities called run, which resizes to 1024 by 250 and calls createGUI().  Then userInterface.startRound(shapes) starts the round with the array of previously defined shapes.  There is an arrayList of strings called shapes2, which is used in the addShape method, which gets passed a string, we believe that to add a shape you should pass a shape object not a string.  Unused: getGameDuration, getTurnsRemaining, getCakeSequenceLength, nextTurn, setDuration, set CakeSequenceLength

 Shape:  Contains a public enum of shapes that have the name of each shape and the image associated with them in the /image/ directory (triangle, square, circle, rhombus, trapezoid, and pentagon).  Then there is a shape constructor with string of the name of the shape called readable and then another string called  image which is the name of the path of the image.  Then two more functions getReadable and getImage which return this.readable and this.image.  Then there is a boolean method called validate which returns true if the

 Sound: A static class that plays a sound effect when a user clicks on an image in the UI.

 ShapeQA:
 This class is currently not used by the program and seems to not be fully implemented.
 Generates and validates the question and answer sequences for shape objects.  First there is an array of Shape datatype called sequence created.  Generate method takes an int n and assigns each iteration of the sequence array a randomly generated value between 1 and the number of shapes, that determines the correct sequence to be associated with each shape.  There is a utility method called dumpReadable which dumps all the readable shape strings into a string array called readableArray and then returns that array.  The boolean validate method compares the passed string array called answer with the readableArray returned by dumpReadable, and returns true if they are the same and false if they are different.  This is intended to compare the answer sequence inputted with the correct sequence.  Then there is an unimplemented function getQuestion which returns the string "Memorize the following sequence:"

 Tracker:  Used to track correct answers and generate a score.  Most of the methods appear to not be used and the correct and incorrect methods have identical code.  Starts with private ints nQuestions, nCorrect, and nIncorrect which are the number of questions, the number correct, and the number incorrect.  Tracker constructor sets nQuestions, nCorrect, and nIncorrect to 0.  Then there are getQuestion, setnQuestions, getnCorrect, getnINcorrect which are all setters and getters which are unused.  Then the methods correct and incorrect which are supposed to be called to track when an answer is correct seem to not be implemented correctly.  They compare the questionAnswered, which is a method which returns an int of the total number of questions answered (correct+incorrect) with the total number of questions.  They then increment nCorrect or nIncorrect and does not do the intended behavior.  Then the unused methods percentCorrect, percentIncorrect, and percentComplete calculate and return a double of the percent correct/incorrect/complete.

 UI: This Class needs work in order to accept user input through the UI and advance the game.  The class extends JPanel and implements ActionListner and UIInterface.  A GameEngine engine is created, then Jlabels called score, instructions title and round.  A Jbutton array called options is created then a Shape array called order and an ImageIcon array called images.  An index int and Timer are then created.
 A constructor  method called UI which accepts a GameEngine creates the BorderLayout, engine, Jbutton, ImageIcon, instruction Jlabel, title Jlabel, score Jlabel and round Jlabel. It creates a Jpanel called titlePanel and tiles the Jpanel with a grid layout, and adds the title Jpanel to it in the north position.  It then adds the score panel in the south position, and instructions panel.  This method sets up the main menu of the game and all the panels that are displayed.
 Next is the startRound method, which accepts a Shape array called order as an argument.  It creates a new Jpanel called selectionPanel with a grid layout, and creates a Shape array called shuffled that represents the shuffled shapes to be drawn.  It then calls a shuffle method, which accepts a shape array (in this case the array of shapes called shuffled) and randomizes and then swaps the shapes to not have the same order of shapes appear each time the program is run.
 After this method returns to the startRound method, a for loop iterates through the shuffled shapes array and sets each shape's image to be displayed by getResource(shuffled[i].getImage(), then the options for each shape, such as set opaque to true, and set name by calling getReadable which returns a string of the name of the shape, and finally calls add actionListener to detect when the user inputs an action on this shape.  An int delay is set to 6000 to wait 6 seconds before drawing the shapes after the main menu.
 An ActionListener called pauseDisplay updates the Instructions by calling updateInstruction method which sets the Instructions Jpanel to the message "Memorize the order the shapes are highlighted!"  It then iterates through the options array with a for loop and adds each option button to the selectionPanel, placing the shapes on the selectionPanel Jpanel in a grid layout in the center.  Then it creates a new timer, sets its repeat to false, and starts the timer.  The int delay from earlier is set to 3000 for 3 seconds to be used later to highlight the shapes 3 seconds apart.  Index is reset to 0 to be used in the next ActionListener which is pauseMemorize.
 The pauseMemorize ActionListener goes through the order by index, and sets each shape's background, according to the order calculated earlier, to red for 3 seconds, then turns the background off again.  It iterates through each shape by the shuffled order and animates each background to red then off again.  This represents the red-blinking sequence the user must memorized.  It then starts a second timer called memTimer which tracks the time until the user selects the correct sequence.
 The next method, actionPerformed, seems to not be working correctly as the user input is not detected by the program.  ActionPerformed is attempting to match the user input with the correct sequence which was demonstrated by the user.  It does this by 4 if statements which detect if each of the 4 shapes has been clicked.
 A JButton method called getButton accepts a String called buttonName, iterates through options and compares the passed buttonName with options[i], if there is a match, it returns options[i], else returning null.
 Lastly there are updateScore, updateRound, and updateInstructions which are setter methods for the score, round, and instructions Jpanels.

 UIInterface: Interface that works with the UI class and exposes updateScore, updateRound, updateInstructions, startRound and shuffle methods.  UpdateScore and updateRound were unused.

 QAInterface: Interface that works with the ShapeQA class that exposes the generate, validate, and getQuestion methods, all unused.

 BoundsExceededException:  Used to create a bounds exceeded exception.  Has two overloaded methods, one that takes no argument and is unused, and one that takes a String message and just calls super(message).
 \section section2 Describe any specification, modularization, or implementation flaws.
 The software has many currently-unused methods, particularly in the GameEngine and Tracker classes.

 - Some methods are particularly complex, and could be better modularized. Notably, the UI.startRound(Shape[] order) method could be split up into multiple, less complex methods.

 - Classes ShapeQA and Tracker are also not being used currently.

 - Program does not advance after entering the correct sequence.  The actionPerformed method and the getButton method in the UI class are not working as intended to accept user input of the sequence and compare it with the correct sequence.

 - Even though there was documentation provided with Doxygen, there are still very little to no comments in the code describing what is going on, especially when there are method calls from other classes.

 \section section3 Determine what still needs to be done, and what your group is going to do in order to finish the project.  What additional features will be implemented?

 - When the user selects the correct sequence the program needs to detect that, increment the score, and advance the program to the next level.

 - Object highlighting needs to be more visible to the user, possibly by using thicker borders.

 - Unused classes like ShapeQA and Tracker should either be integrated and used with the other modules or they should be removed entirely in lieu of another solution.

 - A main menu should be implemented.

 - Because this is a time-based memorization game, there should be some kind of "How to Play" section accessible from the main menu.

 - For aesthetic reasons, the program should have some background fill or image, as well as (mutable) background music.

 - Added sound when player clicks on shape.

 - Most game functionality still needs to be implemented:

 - Scoring system (with high score displayed on main menu perhaps)

 - After shapes are drawn to screen, the message "Memorize the order the shapes are highlighted!" needs to be changed to instruct the user to click the shapes in the correct order.

 - Feedback for incorrect guesses and correct guesses.

 - A scaling difficulty system. E.G. user must memorize slightly longer sequences with each     successful page.

 - If time permits, unit tests should be written for the project to ensure methods produce expected results.

 \section section4 Change Log:

 Implemented sound on image click; changed color scheme

 Added documentation

 Updated actionlistener in highlightShapes to show new message

 Updated actionlistener in highlightShapes to show new message; added method to clear background color

 Added audio feedback for correct and incorrect guesses.

 Added functionality to prevent user from being able to click shapes during a highlghting sequence

 Made it so round 1 doesn't always highlight the circle first

 Added visual feedback with incorrect guess: all shape backgrounds flashed red briefly

 Removed round label. Added level label. Added label to indicate correct or incorrect guess
 *
 */