import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;


/**
 * Defines the interface for a user interface class that displays visual elements and listens to user input.
 */
public class MainMenuUI extends JPanel implements UI {
    private GameEngine engine;
    private JButton playButton;
    private JLabel title;


    public MainMenuUI(GameEngine engine) {
        super(new BorderLayout());
        this.engine = engine;

        constructUI();
    }

    public void constructUI() {
        this.title = new JLabel("Shape Factory");
        playButton = new JButton();
        JPanel titlePanel = new JPanel(new GridLayout(1,1));
        titlePanel.add(this.title);
        add(titlePanel, BorderLayout.NORTH);
        try {
            Image playButtonImage = ImageIO.read(getClass().getResource("images/play_button.png"));
            playButton.setIcon(new ImageIcon(playButtonImage));
        } catch (IOException ioe) {
            System.out.println(ioe.getCause());
        }
        playButton.addActionListener((ActionEvent e) -> {
            engine.createGameGUI();
        });

        JPanel menuPanel = new JPanel(new GridLayout(1,0));
        JPanel howToPlayPanel = new JPanel(new GridLayout(3, 0));
        howToPlayPanel.setBackground(Color.cyan);

        JLabel howToPlayText = new JLabel("During each round, memorize the\n shapes in the order that they are highlighted.");
        JLabel howToPlayText2 = new JLabel("Then click the shapes in the order that you remember!");
        howToPlayText.setHorizontalAlignment(SwingConstants.CENTER);
        howToPlayText2.setHorizontalAlignment(SwingConstants.CENTER);

        menuPanel.add(playButton);
        howToPlayPanel.add(howToPlayText);
        howToPlayPanel.add(howToPlayText2);

        add(menuPanel, BorderLayout.CENTER);
        add(howToPlayPanel, BorderLayout.SOUTH);
    }
}
