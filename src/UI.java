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
    Tracker tracker = new Tracker();

    private boolean listenForClicks = false;

    public UI(GameEngine engine) {
        super(new BorderLayout());
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

        JPanel titlePanel = new JPanel(new GridLayout(1,1));
        titlePanel.add(this.title);
        titlePanel.add(this.instructions);
        add(titlePanel, BorderLayout.NORTH);

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
        System.out.println("listenForClicks = " + listenForClicks);
        this.selectionPanel = new JPanel(new GridLayout(0,4));
        Shape[] shuffled = new Shape[order.length];
        this.order = shuffle(order);
        System.arraycopy(order, 0, shuffled, 0, shuffled.length);

        shuffled = shuffle(shuffled);

        for (int i = 0; i < shuffled.length; i++) {
            images[i] = new ImageIcon(getClass().getResource(shuffled[i].getImage()));
            options[i] = new JButton(images[i]);
            options[i].setOpaque(true);
            options[i].setName(shuffled[i].getReadable());
            options[i].addActionListener(this);
        }
        updatePanel();
        highlightShapes();
    }

    /**
     * Creates new sequence for next round
     * @param order Sequence used to UI generation
     */
    private void nextRound(Shape[] order) {
        listenForClicks = false;
        System.out.println("listenForClicks = " + listenForClicks);
        Shape[] shuffled = new Shape[order.length];
        this.order = order;
        System.arraycopy(order, 0, shuffled, 0, shuffled.length);

        shuffled = shuffle(shuffled);

        for (int i = 0; i < shuffled.length; i++) {
            images[i] = new ImageIcon(getClass().getResource(shuffled[i].getImage()));
            options[i].setIcon(images[i]);
            options[i].setOpaque(true);
            options[i].setName(shuffled[i].getReadable());
        }
        updatePanel();
        highlightShapes();
    }

    private void updatePanel() {
        updateInstructions("Memorize the order the shapes are highlighted!");
        for (int i = 0; i < options.length; i++) {
            selectionPanel.add(options[i]);
        }
        add(selectionPanel, BorderLayout.CENTER);

    }

    private void highlightShapes() {
        int delay = 1000;
        index = 0;
        ActionListener pauseMemorize = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("INDEX > " + index);
                if (index > 0) { //removes previous image background
                    getButton(order[index - 1].getReadable()).setBackground(null);
                }
                if (index < 4) {
                    getButton(order[index++].getReadable()).setBackground(Color.ORANGE);
                }
                else // if index >= 4
                {
//                    timer.stop();
                    memTimer.stop();
                    listenForClicks = true;
                    System.out.println("listenForClicks = " + listenForClicks);
                    updateInstructions("Click on the shapes in the correct order!");
                }
            }
        };
        memTimer = new Timer(delay, pauseMemorize);
        memTimer.setRepeats(true);
        memTimer.start();
    }

    //clears the image buttons from background colors
    public void clearButtonBackground(){
        for(JButton button : options)
        {
            button.setBackground(null);
        }
    }

    public void actionPerformed(ActionEvent e) {
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

    public JButton getButton(String buttonName) {
        for (int i = 0; i < options.length; i++) {
            if (options[i].getName().equals(buttonName)) {
                return options[i];
            }
        }
        return null;
    }

    public void setScore(int score) {
        this.currentScore = score;
        updateScoreText();
    }

    public void incrementScore() {
        this.currentScore++;
        tracker.correct();
        updateScoreText();
    }

    public void decrementScore() {
        if (this.currentScore > 0)
            this.currentScore--;
        tracker.incorrect();
        updateScoreText();
    }

    private void updateScoreText() {
        this.score.setText("Score: " + this.currentScore);
    }

    public void updateInstructions(String message) {
        this.instructions.setText(message);
    }

    private void updateCorrectnessMessage(String message) { this.correctnessMessage.setText(message); }
}
