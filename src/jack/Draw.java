package jack;
import javax.swing.*;

public class Draw extends JLabel
{
    Draw()
    {
        ImageIcon image = new ImageIcon("draw.png");
        this.setBounds(0, 0, 1000, 700);
        this.setIcon(image);
    }
}