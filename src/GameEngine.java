import javax.swing.*;

public class GameEngine extends JApplet {
    UI userInterface;
    Shape[] shapes = new Shape[]{Shape.CIRCLE, Shape.PENTAGON, Shape.RHOMBUS, Shape.TRAPEZOID};

    public void init() {
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                public void run() {
                    createGUI();
                    userInterface.startRound(shapes);
                }
            });
        } catch (Exception e) {
            System.err.println("createGUI didn't complete successfully");
        }
    }

    private void createGUI() {
        userInterface = new UI();
        userInterface.setOpaque(true);
        setContentPane(userInterface);
    }
}
