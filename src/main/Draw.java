package main;
import javax.swing.*;

public class Draw extends JLabel
{
    Draw()
    {
        ImageIcon imageD = new ImageIcon("draw.png");
        this.setBounds(0, 0, 1000, 700);
        this.setComponentZOrder(this, 0);
        this.setIcon(imageD);
    }
}