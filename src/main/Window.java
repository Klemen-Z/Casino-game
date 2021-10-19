package main;
import javax.swing.*;
import java.awt.Color;
public class Window extends JFrame
{
    Window()
    {
        ImageIcon image = new ImageIcon("Logo.png");

        this.setTitle("Blackjack");
        this.setIconImage(image.getImage());
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.black);
    }
}