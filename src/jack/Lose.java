package jack;
import javax.swing.*;

public class Lose extends JLabel
{
    Lose()
    {
        ImageIcon imageL = new ImageIcon("lose.png");
        this.setBounds(0, 0, 1000, 700);
        this.setComponentZOrder(this, 0);
        this.setIcon(imageL);
    }
}