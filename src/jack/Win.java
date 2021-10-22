package jack;
import javax.swing.*;

public class Win extends JLabel
{
    Win()
    {
        ImageIcon image = new ImageIcon("win.png");
        this.setBounds(0, 0, 1000, 700);
        this.setIcon(image);
    }
}
