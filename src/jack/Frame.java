package jack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame extends JFrame implements ActionListener
{
	JButton hitbutton; //Damit hitbutton �berall verwendet werden kann
	JButton standbutton;
	Frame()
	{
		//Einstellungen f�r das Hauptfenster
		LabelBackground background = new LabelBackground(); //Label f�r Background
		LabelPlayerValue value = new LabelPlayerValue(); //Label f�r visuellen Punktewert
		ImageIcon icon = new ImageIcon("Logo.png");
		
		this.setTitle("Blackjack");
		this.setSize(1000, 800);
		this.setIconImage(icon.getImage());
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBackground(Color.black);
		this.setResizable(false);
		
		//Hier wird der Hit Button definiert
		
		hitbutton = new JButton();
		
		ImageIcon hit = new ImageIcon("Hit.png");
		hitbutton.setBounds(150, 560, 200, 100);
		hitbutton.setIcon(hit);
		hitbutton.setOpaque(false);
		hitbutton.setContentAreaFilled(false);
		hitbutton.setBorderPainted(false);
		hitbutton.addActionListener(this);
		
		//Hier wird der Stand Button definiert
		
		standbutton = new JButton();
		
		ImageIcon stand = new ImageIcon("Stand.png");
		standbutton.setBounds(150, 430, 200, 100);
		standbutton.setIcon(stand);
		standbutton.setOpaque(false);
		standbutton.setContentAreaFilled(false);
		standbutton.setBorderPainted(false);
		standbutton.addActionListener(this);
		
		//Zuordnung der Labels und Buttons in JFrame
		
		this.add(value);
		this.add(hitbutton);
		this.add(standbutton);
		this.add(background);
	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==hitbutton)
		{
			hitbutton.setEnabled(false); //Verhindert spaming
			standbutton.setEnabled(false);
			//Do actions here when hitbutton is pressed
		}
		if(e.getSource()==standbutton) 
		{
			standbutton.setEnabled(false); //Verhindert spaming
			hitbutton.setEnabled(false);
		}
	}
}
