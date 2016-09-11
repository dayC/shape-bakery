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
    private JLabel score;
    private JButton[] options;
    private ImageIcon[] images;

    public UI() {
        super(new BorderLayout());
        JLabel title = new JLabel("Shape Factory");
        updateScore(0);

        JPanel titlePanel = new JPanel(new GridLayout(0,1));
        titlePanel.add(title);
        add(titlePanel, BorderLayout.NORTH);

        JPanel scorePanel = new JPanel(new GridLayout(0,1));
        scorePanel.add(score);
        add(scorePanel, BorderLayout.SOUTH);
    }

    public void startRound(Shape[] order) {
        JPanel selectionPanel = new JPanel(new GridLayout(0,4));
        shuffle(order);
        options = new JButton[4];

        for (int i = 0; i < order.length; i++) {
            images[i] = new ImageIcon(order[i].getImage());
            options[i] = new JButton(images[i]);
            selectionPanel.add(options[i]);
        }

        add(selectionPanel, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent e) {

    }

    public void shuffle(Shape[] order) {
        Random rand = ThreadLocalRandom.current();
        for (int i = order.length - 1; i > 0; i--) {
            int index = rand.nextInt(i + 1);

            Shape temp = order[index];
            order[index] = order[i];
            order[i] = temp;
        }
    }

    public void updateScore(int score) {
        this.score = new JLabel("Score: " + score);
    }
}
