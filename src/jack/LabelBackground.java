package jack;
import javax.swing.*;
public class LabelBackground extends JLabel
{
	LabelBackground()
	{
		//Label f�r den Hintergrund
		
		ImageIcon image = new ImageIcon("Background.png");
		this.setIcon(image);
		this.setBounds(0, 0, 1000, 700);
	}
}
