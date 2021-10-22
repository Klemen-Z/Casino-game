package jack;
import javax.swing.*;

public class Lose extends JLabel
{
    Lose()
    {
        ImageIcon image = new ImageIcon("lose.png");
        this.setBounds(0, 0, 1000, 700);
        this.setIcon(image);
    }
}