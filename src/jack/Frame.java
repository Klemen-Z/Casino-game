package jack;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame extends JFrame implements ActionListener
{
	//image get from JackManager
	JButton hitbutton; //Damit hitbutton überall verwendet werden kann
	JButton standbutton;
	JLabel cards;
	LabelPlayerValue value;
	LabelComputerValue valuec;
	private static String button;
	//get the button output
	public static String getButton(){
		return button;
	}
	//set the button value
	public static void setButton(String Button){
		button = Button;
	}
	Frame() {
		//Einstellungen für das Hauptfenster

		LabelBackground background = new LabelBackground(); //Label für Background
		cards = new JLabel(); //Label für Karte
		value = new LabelPlayerValue(); //Label für visuellen Punktewert
		valuec = new LabelComputerValue(); //Label für Computerpunktestand
		ImageIcon icon = new ImageIcon("Logo.png");

		this.setTitle("Blackjack");
		this.setSize(1000, 730);
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
		this.add(valuec);
		this.add(cards);
		this.add(hitbutton);
		this.add(standbutton);
		this.add(background);
		cards.setVisible(true);
		//Wert im Label am start ändern
		value.setText(Integer.toString(JackManager.getpPoints()));
	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==hitbutton)
		{
			cards.setVisible(true);
			setButton("1");
			//Do actions here when hitbutton is pressed
		}
		else if(e.getSource()==standbutton)
		{
			setButton("0");
			standbutton.setEnabled(false); //Verhindert spaming
			hitbutton.setEnabled(false);
		}
	}
	public void getComponentZOrder(Draw draw, int i) {
	}
	public void getComponentZOrder(Win win, int i) {
	}
	public void getComponentZOrder(Lose Lose, int i) {
	}
}