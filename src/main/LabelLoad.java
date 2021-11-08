package main;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
public class LabelLoad extends JLabel
{
	LabelLoad()
	{
		//Label für den Hintergrund
		ImageIcon image = new ImageIcon("Loading.png");
		this.setIcon(image);
		this.setBounds(0, 0, 1000, 700);
	}
}
