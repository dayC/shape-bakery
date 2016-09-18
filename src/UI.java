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
    private JLabel score, instructions, title, round;
    JPanel selectionPanel;
    private JButton[] options;
    private Shape[] order;
    private ImageIcon[] images;

    private int index;
    private Timer memTimer;
    private int currentScore;

    public UI(GameEngine engine) {
        super(new BorderLayout());
        this.engine = engine;
        options = new JButton[4];
        images = new ImageIcon[4];
        instructions = new JLabel("Welcome to Shape Factory!");
        title = new JLabel("Shape Factory");
        this.currentScore = 0;
        score = new JLabel("Score: " + this.currentScore);
        round = new JLabel("Round: 1");

        JPanel titlePanel = new JPanel(new GridLayout(0,1));
        titlePanel.add(title);
        add(titlePanel, BorderLayout.NORTH);

        JPanel scorePanel = new JPanel(new GridLayout(0,2));
        scorePanel.add(score);
        scorePanel.add(instructions);
        add(scorePanel, BorderLayout.SOUTH);

    }

    /**
     * Initializes game.
     * Generates a random sequence and shows the sequence for the user to input
     * @param order An array of shapes that is already sorted in the correct memorization order.
     */
    public void startGame(Shape[] order) {
        this.selectionPanel = new JPanel(new GridLayout(0,4));
        Shape[] shuffled = new Shape[order.length];
        this.order = order;
        System.arraycopy(order, 0, shuffled, 0, shuffled.length);

        shuffled = shuffle(shuffled);

        for (int i = 0; i < shuffled.length; i++) {
            images[i] = new ImageIcon(getClass().getResource(shuffled[i].getImage()));
            options[i] = new JButton(images[i]);
            options[i].setOpaque(true);
            options[i].setName(shuffled[i].getReadable());
            options[i].addActionListener(this);
        }
        highlightShapes();
    }

    /**
     * Creates new sequence for next round
     * @param order Sequence used to UI generation
     */
    private void nextRound(Shape[] order) {
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
        highlightShapes();
    }

    private void highlightShapes() {
        int delay = 1000;
        ActionListener pauseDisplay = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateInstructions("Memorize the order the shapes are highlighted!");

                for (int i = 0; i < options.length; i++) {
                    selectionPanel.add(options[i]);
                }

                add(selectionPanel, BorderLayout.CENTER);
            }
        };
        Timer timer = new Timer(delay, pauseDisplay);
        timer.setRepeats(false);
        timer.start();


        // delay between showing memorization order of shapes
        //delay = 2000;

        index = 0;

        ActionListener pauseMemorize = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (index > 4) {
                    timer.stop();
                } else if (index > 0) {
                    getButton(order[index - 1].getReadable()).setBackground(null);
                }
                if (index < 4) {
                    getButton(order[index++].getReadable()).setBackground(Color.ORANGE);
                }

            }
        };

        memTimer = new Timer(delay, pauseMemorize);
        memTimer.setRepeats(true);
        memTimer.setInitialDelay(2 * delay);
        memTimer.start();
    }

    public void actionPerformed(ActionEvent e) {
        Sound.playSound();
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
            decrementScore();
            engine.clearGuesses();

            nextRound(this.order);
        }
        else if (engine.checkforCorrectnessSoFar(this.order) && this.order.length == engine.shapes2.size()) {
            this.order = shuffle(this.order);
            incrementScore();
            engine.clearGuesses();

            nextRound(this.order);
        }
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
        updateScoreText();
    }

    public void decrementScore() {
        if (this.currentScore > 0)
            this.currentScore--;
        updateScoreText();
    }

    private void updateScoreText() {
        this.score.setText("Score: " + this.currentScore);
    }

    public void updateRound(int round) {
        this.round.setText("Round: " + round);
    }

    public void updateInstructions(String message) {
        this.instructions.setText("Message: " + message);
    }
}
