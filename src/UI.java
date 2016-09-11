import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Defines the interface for a user interface class that displays visual elements and listens to user input.
 */
public class UI extends JPanel implements ActionListener, UIInterface {
    private JLabel score;
    private JButton option1, option2, option3, option4;
    private ImageIcon squareCake, circleCake, triangleCake, rhombusCake, trapezoidCake, pentagonCake;

    public UI() {
        super(new BorderLayout());
        JLabel title = new JLabel("Shape Factory");
        updateScore(0);

        JPanel titlePanel = new JPanel(new GridLayout(0,1));
        titlePanel.add(title);
        add(titlePanel, BorderLayout.NORTH);

        squareCake = new ImageIcon(this.getClass().getResource("images/square.png"));
        circleCake = new ImageIcon(this.getClass().getResource("images/circle.png"));
        triangleCake = new ImageIcon(this.getClass().getResource("images/triangle.png"));
        rhombusCake = new ImageIcon(this.getClass().getResource("images/rhombus.jpg"));
        trapezoidCake = new ImageIcon(this.getClass().getResource("images/trapezoid.jpg"));
        pentagonCake = new ImageIcon(this.getClass().getResource("images/pentagon.png"));

        JPanel scorePanel = new JPanel(new GridLayout(0,1));
        scorePanel.add(score);
        add(scorePanel, BorderLayout.SOUTH);
    }

    public void startRound(Shape[] order) {
        JPanel selectionPanel = new JPanel(new GridLayout(0,4));

        for (int i = 0; i < order.length; i++) {

        }

        option1 = new JButton(squareCake);
        option2 = new JButton(circleCake);
        option3 = new JButton(pentagonCake);
        option4 = new JButton(trapezoidCake);

        selectionPanel.add(option1);
        selectionPanel.add(option2);
        selectionPanel.add(option3);
        selectionPanel.add(option4);
        add(selectionPanel, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent e) {

    }

    public void updateScore(int score) {
        this.score = new JLabel("Score: " + score);
    }
}
