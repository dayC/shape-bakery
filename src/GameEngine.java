import javax.swing.*;
import java.util.ArrayList;

public class GameEngine extends JApplet {
    UI userInterface;
    Shape[] shapes = new Shape[]{Shape.CIRCLE, Shape.PENTAGON, Shape.RHOMBUS, Shape.TRAPEZOID};
    ArrayList<String> shapes2 = new ArrayList<String>();

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
