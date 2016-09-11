import javax.swing.*;
import java.util.ArrayList;

public class GameEngine extends JApplet {
    UI userInterface;
    Shape[] shapes = new Shape[]{Shape.CIRCLE, Shape.PENTAGON, Shape.RHOMBUS, Shape.TRAPEZOID};
    ArrayList<String> shapes2 = new ArrayList<String>();

    // GAME_DURATION is 20 by default
    private int gameDuration = 20;
    private final int MIN_GAME_DURATION = 1;
    private final int MAX_GAME_DURATION = 40;

    // CAKE_SEQUENCE_LENGTH is 4 by default
    private int cakeSequenceLength = 4;
    private final int MIN_CAKE_SEQUENCE_LENGTH = 1;
    private final int MAX_CAKE_SEQUENCE_LENGTH = 8;

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

    public void init() {
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                public void run() {
                    resize(1024, 250);
                    createGUI();
                    userInterface.startRound(shapes);
                }
            });
        } catch (Exception e) {
            System.err.println("createGUI didn't complete successfully");
        }
    }

    private void createGUI() {
        userInterface = new UI(this);
        userInterface.setOpaque(true);
        setContentPane(userInterface);
    }

    public void addShape(String shape) {
        shapes2.add(shape);
    }
}
