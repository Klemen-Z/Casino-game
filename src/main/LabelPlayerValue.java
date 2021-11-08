package main;
import javax.swing.*;
import java.awt.*;

public class LabelPlayerValue extends JLabel
{
	//Dieser Label wird benötigt um Punktestand anzuzeigen

	LabelPlayerValue()
	{
		this.setText(Integer.toString(JackManager.getpPoints()));
		this.setBounds(610, 520, 100, 50);
		this.setHorizontalAlignment(CENTER);
		this.setVerticalAlignment(CENTER);
		this.setFont(new Font("Arial",Font.PLAIN,60));
		this.setForeground(new Color(0xFFFF00));
	}
}
