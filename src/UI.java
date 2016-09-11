/**
 * Defines the interface for a user interface class that displays visual elements and listens to user input.
 */

import javax.swing.*;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class UI extends JApplet implements UIInterface, ActionListener{
    private JLabel title, score;
    private ImageIcon squareCake, circleCake, triangleCake, rhombusCake, trapezoidCake, pentagonCake;
    private JButton option1, option2, option3, option4;

    public UI() {
        title = new JLabel("Shape Bakery");
        add(title);
        updateScore(0);
        add(score);

        // images to be loaded for later use
        squareCake = new ImageIcon("../images/square.png");
        circleCake = new ImageIcon("../images/circle.png");
        triangleCake = new ImageIcon("../images/triangle.png");
        rhombusCake = new ImageIcon("../images/rhombus.jpg");
        trapezoidCake = new ImageIcon("../images/trapezoid.jpg");
        pentagonCake = new ImageIcon("../images/pentagon.png");
    }

    public void startGame() {
        updateScore(0);
    }

    public void updateScore(int score) {
        this.score = new JLabel("Score: " + String.valueOf(score));
    }

    public void displayOrder(Shape[] order) {
        // will eventually be read in from order parameter
        option1 = new JButton(squareCake);
        option2 = new JButton(triangleCake);
        option3 = new JButton(trapezoidCake);
        option4 = new JButton(pentagonCake);

        // will eventually have actions interface with game engine and communicate the type of cake pressed
        option1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Square was clicked");
            }
        });

        option2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Triangle was clicked");
            }
        });

        option3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Trapezoid was clicked");
            }
        });

        option4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Pentagon was clicked");
            }
        });

        // will eventually be added/displayed in the ui through iterating the order array
        add(option1);
        add(option2);
        add(option3);
        add(option4);
    }
}
