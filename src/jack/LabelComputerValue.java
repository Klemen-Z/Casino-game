package jack;
import javax.swing.*;
import java.awt.*;

public class LabelComputerValue extends JLabel
{
	//Dieser Label wird benötigt um Punktestand anzuzeigen
	
	LabelComputerValue()
	{
		this.setText(Integer.toString(JackManager.getdPoints()));
		this.setBounds(610, 120, 100, 50);
		this.setHorizontalAlignment(CENTER);
		this.setVerticalAlignment(CENTER);
		this.setFont(new Font("Arial",Font.PLAIN,60));
		this.setForeground(new Color(0xFFFF00));
	}
}