import javax.swing.*;

public class GameEngine extends JApplet {
    UI userInterface;

    public void init() {
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                public void run() {
                    createGUI();
                    userInterface.startRound();
                }
            });
        } catch (Exception e) {
            System.err.println("createGUI didn't complete successfully");
        }
    }

    private void createGUI() {
        //Create and set up the content pane.
        userInterface = new UI();
        userInterface.setOpaque(true);
        setContentPane(userInterface);
    }
}
