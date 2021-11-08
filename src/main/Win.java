package main;
import javax.swing.*;

public class Win extends JLabel
{
    Win()
    {
        ImageIcon imageW = new ImageIcon("win.png");
        this.setBounds(0, 0, 1000, 700);
        this.setComponentZOrder(this, 0);
        this.setIcon(imageW);
    }
}
