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
    private JLabel score, instructions, title;
    private JButton[] options;
    private ImageIcon[] images;

    private int index;

    public UI(GameEngine engine) {
        super(new BorderLayout());
        this.engine = engine;
        options = new JButton[4];
        images = new ImageIcon[4];
        instructions = new JLabel("Welcome to Shape Factory!");
        title = new JLabel("Shape Factory");
        score = new JLabel("Score: 0");

        JPanel titlePanel = new JPanel(new GridLayout(0,1));
        titlePanel.add(title);
        add(titlePanel, BorderLayout.NORTH);

        JPanel scorePanel = new JPanel(new GridLayout(0,2));
        scorePanel.add(score);
        scorePanel.add(instructions);
        add(scorePanel, BorderLayout.SOUTH);
    }

    public void startRound(Shape[] order) {
        JPanel selectionPanel = new JPanel(new GridLayout(0,4));
        Shape[] shuffled = new Shape[order.length];

        for (int i = 0; i < order.length; i++) {
            shuffled[i] = order[i];
        }
        shuffle(shuffled);

        int delay = 6000;

        Timer timer = new Timer(delay, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateInstructions("Watch the order that the shapes are highlighted carefully and try to remember it!");

                for (int i = 0; i < shuffled.length; i++) {
                    images[i] = new ImageIcon(getClass().getResource(shuffled[i].getImage()));
                    options[i] = new JButton(images[i]);
                    options[i].setOpaque(true);
                    options[i].setName(shuffled[i].getReadable());
                    options[i].addActionListener(this);
                    selectionPanel.add(options[i]);
                }

                add(selectionPanel, BorderLayout.CENTER);
            }
        });
        timer.setRepeats(false);
        timer.start();

 /*       // delay between showing memorization order of shapes
        delay = 2000;

        index = 0;*/

        options[0].setBackground(Color.RED);

  /*      ActionListener memorize = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                options[index].setBackground(null);
            }
        };

        Timer timer2 = new Timer(delay, memorize);
        timer2.setRepeats(false);
        timer2.start();

        System.out.println(options[index].getName());
        System.out.println(order[index].getReadable());*/
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == options[0]) {
            engine.addShape(options[0].getName());
        } else if (e.getSource() == options[1]) {
            engine.addShape(options[1].getName());
        } else if (e.getSource() == options[2]) {
            engine.addShape(options[2].getName());
        } else if (e.getSource() == options[3]) {
            engine.addShape(options[3].getName());
        }
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
        this.score.setText("Score: " + score);
    }

    public void updateInstructions(String message) {
        this.instructions.setText("Message: " + message);
    }
}
