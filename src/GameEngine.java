import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameEngine extends JApplet {
    UI userInterface;
    Shape[] shapes = new Shape[]{Shape.CIRCLE, Shape.PENTAGON, Shape.RHOMBUS, Shape.TRAPEZOID};
    ArrayList<String> shapes2 = new ArrayList<String>(); //used to store player's sequence

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

    public int getGameDuration()
    {
        return this.gameDuration;
    }

    public int getTurnsRemaining()
    {
        return this.turnsRemaining;
    }

    public int getCakeSequenceLength()
    {
        return cakeSequenceLength;
    }

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


    public boolean checkforCorrectnessSoFar(Shape[] correctOrder) {
        int i = 0;
        while (i < correctOrder.length && i < this.shapes2.size()) {
            if (!correctOrder[i].getReadable().equals(this.shapes2.get(i))) {
                userInterface.getButton(this.shapes2.get(i)).setBackground(Color.RED);
                return false;
            }
            i++;
        }
        return true;
    }

    public void init() {
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
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

    private void createGUI() {
        userInterface = new UI(this);
        userInterface.setOpaque(true);
        setContentPane(userInterface);
    }

    public void clearGuesses() {
        this.shapes2.clear();
    }

    public void addShape(String shape) {

        shapes2.add(shape);
        for (String s : shapes2) {
            System.out.print(s + ", ");
        }
        System.out.println();
    }
    
}
