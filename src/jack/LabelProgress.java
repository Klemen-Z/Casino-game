package jack;
import java.awt.*;
import javax.swing.*;

public class LabelProgress 
{
	JFrame startframe = new JFrame();
	JProgressBar bar = new JProgressBar(0,100);
	
	LabelProgress()
	{
		//Einstellungen für den Startscreen
		
		LabelLoad load = new LabelLoad(); //Für den Hintergrund des Loadscreens
		
		startframe.add(bar);
		startframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		startframe.setSize(1000, 730);
		startframe.add(load);
		ImageIcon icon = new ImageIcon("Logo.png");
		startframe.setIconImage(icon.getImage());
		startframe.setTitle("Blackjack");
		startframe.setLayout(null);
		startframe.setVisible(true);
		
		//Einstellungen für den Progressbar
		
		bar.setValue(0);
		bar.setBounds(250, 400, 500, 70);
		bar.setStringPainted(true);
		bar.setFont(new Font("Arial",Font.BOLD,35));
		bar.setForeground(Color.yellow);
		bar.setBackground(Color.black);
		
		barProgress();
		startframe.dispose(); //Das Ladefenster wird geschlossen

	}
	
	public void barProgress() 
	{
		int x = 0; //Fürs zählen
		
		while(x < 101) 
		{
			bar.setValue(x);
			
			//sleep damit Progressbar nicht sofort auf 100% geht
			
			try {
				Thread.sleep(40);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			x++;
		}
	}
}
