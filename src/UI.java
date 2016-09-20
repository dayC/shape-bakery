import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


/**
 * Defines the interface for a user interface class that displays visual elements and listens to user input.
 */
public class UI extends JPanel implements ActionListener, UIInterface {
    private GameEngine engine;
    private JLabel score, instructions, title, level, correctnessMessage;
    JPanel selectionPanel;
    private JButton[] options;
    private Shape[] order;
    private ImageIcon[] images;

    private int index;
    private Timer memTimer;
    private int currentScore;
    private int currentLevel;

    //used to track game statistics.
    Tracker tracker = new Tracker();

    private boolean listenForClicks = false;

    /**
     * Sets up the user interface to initial conditions.
     * @param engine = The game engine which implements this UI.
     */
    public UI(GameEngine engine) {
        super(new BorderLayout());

        //initialize this UI's elements
        this.engine = engine;
        this.options = new JButton[4];
        this.images = new ImageIcon[4];
        this.currentScore = 0;
        this.currentLevel = 1;
        this.title = new JLabel("Shape Factory");
        this.instructions = new JLabel("Welcome to Shape Factory!");
        this.level = new JLabel("Level: " + this.currentLevel);
        this.score = new JLabel("Score: " + this.currentScore);
        this.correctnessMessage = new JLabel("");

        //title panel that displays the title and instructions.
        JPanel titlePanel = new JPanel(new GridLayout(1,1));
        titlePanel.add(this.title);
        titlePanel.add(this.instructions);
        add(titlePanel, BorderLayout.NORTH);

        //Lower panel that displays the score, correctness message, and level.
        JPanel lowerPanel = new JPanel(new GridLayout(2,1));
        lowerPanel.add(this.score);
        lowerPanel.add(this.correctnessMessage);
        lowerPanel.add(this.level);
        add(lowerPanel, BorderLayout.SOUTH);

    }

    /**
     * Initializes game.
     * Generates a random sequence and shows the sequence for the user to input
     * @param order An array of shapes that is already sorted in the correct memorization order.
     */
    public void startGame(Shape[] order) {
        listenForClicks = false;

        //used for debugging
        System.out.println("listenForClicks = " + listenForClicks);

        //new JPanel which we will draw our shapes onto
        this.selectionPanel = new JPanel(new GridLayout(0,4));

        //Array of shapes that we will copy the correct order into then shuffle.
        Shape[] shuffled = new Shape[order.length];

        //shuffle the order, which is the correct order of shapes.
        this.order = shuffle(order);

        //copy the newly shuffled order into shuffled.
        System.arraycopy(order, 0, shuffled, 0, shuffled.length);

        //then shuffle shuffled.
        shuffled = shuffle(shuffled);

        //get each shapes image and place them in a button, then place the button in options.
        //set options to visible, place the name of the shape on the buttons, and then add the action listener
        //to listen for user input
        for (int i = 0; i < shuffled.length; i++) {
            images[i] = new ImageIcon(getClass().getResource(shuffled[i].getImage()));
            options[i] = new JButton(images[i]);
            options[i].setOpaque(true);
            options[i].setName(shuffled[i].getReadable());
            options[i].addActionListener(this);
        }
        //updates the instructions, and draws a new selection panel of shapes.
        updatePanel();

        //highlights the shapes to show the user the correct input.
        highlightShapes();
    }

    /**
     * Creates new sequence for next round
     * @param order Sequence used to UI generation
     */
    private void nextRound(Shape[] order) {
        listenForClicks = false;

        //used for debugging
        System.out.println("listenForClicks = " + listenForClicks);

        //Array of shapes that we will copy the correct order into then shuffle.
        Shape[] shuffled = new Shape[order.length];
        //shuffle the order, which is the correct order of shapes.
        this.order = order;
        //copy the newly shuffled order into shuffled.
        System.arraycopy(order, 0, shuffled, 0, shuffled.length);
        //then shuffle shuffled.
        shuffled = shuffle(shuffled);

        //get each shapes image and place them in a button, then place the button in options.
        //set options to visible, place the name of the shape on the buttons, and then add the action listener
        //to listen for user input
        for (int i = 0; i < shuffled.length; i++) {
            images[i] = new ImageIcon(getClass().getResource(shuffled[i].getImage()));
            options[i].setIcon(images[i]);
            options[i].setOpaque(true);
            options[i].setName(shuffled[i].getReadable());
        }

        //updates the instructions, and draws a new selection panel of shapes.
        updatePanel();

        //highlights the shapes to show the user the correct input.
        highlightShapes();
    }

    /**
     * Updates the panel
     */
    private void updatePanel() {
        updateInstructions("Memorize the order the shapes are highlighted!");
        for (int i = 0; i < options.length; i++) {
            selectionPanel.add(options[i]);
        }
        add(selectionPanel, BorderLayout.CENTER);

    }

    /**
     * Highlights the correct order of the shapes for the user.
     */
    private void highlightShapes() {
        int delay = 1000;   //the length of time in milliseconds the shape is highlighted.
        index = 0;
        ActionListener pauseMemorize = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("INDEX > " + index);
                if (index > 0) { //removes previous image background
                    getButton(order[index - 1].getReadable()).setBackground(null);
                }
                if (index < 4) {
                    getButton(order[index++].getReadable()).setBackground(Color.ORANGE); //highlight the button in orange.
                }
                else // if index >= 4
                {
//                    timer.stop();
                    memTimer.stop();
                    listenForClicks = true; //done highlighting, allow the user to input.
                    //used for debugging
                    System.out.println("listenForClicks = " + listenForClicks);
                    //updates the instructions, instructing the user to input the correct order.
                    updateInstructions("Click on the shapes in the correct order!");
                }
            }
        };
        //start the timer
        memTimer = new Timer(delay, pauseMemorize);
        memTimer.setRepeats(true);
        memTimer.start();
    }

    /**
     * Clears the image buttons from background colors
     */
    public void clearButtonBackground(){
        for(JButton button : options)
        {
            button.setBackground(null);
        }
    }

    /**
     * Detects if an ActionEven has occurred, and compares the event (user input)
     * with the options array (correct input).  If the two match, set the shapes background color to green
     * to signify correct input by the user.  Otherwise the user has inputted incorrectly, call incorrectGuess()
     * to reset the game.  If the user has inputted all 4 correctly, call correctGuess() and advance the game to
     * the next level.
     * @param e = An ActionEvent that has occurred.
     */
    public void actionPerformed(ActionEvent e) {
        //listenForClicks = true when the user is allowed to input (not allowed when program is highlighting shapes)
        if (listenForClicks) {
            if (e.getSource() == options[0]) {
                engine.addShape(options[0].getName());
                options[0].setBackground(Color.GREEN);
            } else if (e.getSource() == options[1]) {
                engine.addShape(options[1].getName());
                options[1].setBackground(Color.GREEN);
            } else if (e.getSource() == options[2]) {
                engine.addShape(options[2].getName());
                options[2].setBackground(Color.GREEN);
            } else if (e.getSource() == options[3]) {
                engine.addShape(options[3].getName());
                options[3].setBackground(Color.GREEN);
            }

            if (!engine.checkforCorrectnessSoFar(this.order)) {
                // Incorrect guess sequence
                incorrectGuess();
            } else if (engine.checkforCorrectnessSoFar(this.order) && this.order.length == engine.userShapeSelections.size()) {
                // Correct guess sequence
                correctGuess();
            } else {
                // Play generic click sound
                Sound.playSound("audio/click.wav");
            }
        }
    }

    /**
     * The user has entered an incorrect sequence.  Play a sound, display a "incorrect" message to the user
     * Briefly flash all the buttons red, shuffle the order, and start another game with the shuffled order.
     */
    private void incorrectGuess() {
        listenForClicks = false;
        Sound.playSound("audio/incorrect_beep.wav");
        decrementScore();
        updateCorrectnessMessage("Woops! Incorrect");

        // Briefly flash red
        for (Shape s : order) {
            getButton(s.getReadable()).setBackground(Color.RED);
        }

        int delay = 1500;
        index = 0;
        Timer timer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Shape s : order) {
                    getButton(s.getReadable()).setBackground(null);
                }
                updateCorrectnessMessage("");
                engine.clearGuesses();
                clearButtonBackground();
                nextRound(order);
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    /**
     * The user has entered a correct sequence.  Increment their score, display a "correct" message, play a sound,
     * shuffle the order, and advance to the next round with the shuffled order.
     */
    private void correctGuess() {
        listenForClicks = false;
        incrementScore();
        updateCorrectnessMessage("Correct!");
        int delay = 1000;
        Sound.playSound("audio/correct_beep.wav");
        ActionListener pause = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateCorrectnessMessage("");
                order = shuffle(order);
                engine.clearGuesses();
                clearButtonBackground();
                nextRound(order);
            }
        };
        Timer pauseTimer = new Timer(delay, pause);
        pauseTimer.setRepeats(false);
        pauseTimer.start();
    }

    /**
     *
     * @param order = An array of shapes that is already sorted in the correct memorization order.
     * @return order = An array of shapes that have now been shuffled in order to be reused.
     */
    public Shape[] shuffle(Shape[] order) {
        Random rand = ThreadLocalRandom.current();
        for (int i = order.length - 1; i > 0; i--) {
            int index = rand.nextInt(i + 1);

            Shape temp = order[index];
            order[index] = order[i];
            order[i] = temp;
        }
        return order;
    }

    /**
     * Gets a JButton from options (an array of buttons) given a button name.  If no button found, return null.
     * @param buttonName = The name of the button to retrieve.
     * @return The JButton in options[] that matches the given buttonName.  Returns null if no button found.
     */
    public JButton getButton(String buttonName) {
        for (int i = 0; i < options.length; i++) {
            if (options[i].getName().equals(buttonName)) {
                return options[i];
            }
        }
        return null;
    }

    /**
     * Sets the score to the integer given, and updates the score label.
     * @param score The score to be updated.
     */
    public void setScore(int score) {
        this.currentScore = score;
        updateScoreText();
    }

    /**
     * Increments the score and updates the score label.  Gives a correct() call to tracker to track correct answers.
     */
    public void incrementScore() {
        this.currentScore++;
        tracker.correct();
        updateScoreText();
    }

    /**
     * Decrements the score and updates the score label.  Gives an incorrect() call to tracker to track incorrect answers.
     */
    public void decrementScore() {
        if (this.currentScore > 0)
            this.currentScore--;
        tracker.incorrect();
        updateScoreText();
    }

    /**
     * Updates the score label to show the changed score.
     */
    private void updateScoreText() {
        this.score.setText("Score: " + this.currentScore);
    }

    /**
     * Updates the instructions label to the passed string message.
     * @param message = The message to be displayed.
     */
    public void updateInstructions(String message) {
        this.instructions.setText(message);
    }

    /**
     * Updates the message that shows the user if they are correct or incorrect, to the passed string message.
     * @param message = The message to be displayed.
     */
    private void updateCorrectnessMessage(String message) { this.correctnessMessage.setText(message); }
}
