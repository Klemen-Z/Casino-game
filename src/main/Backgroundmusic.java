package main;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class Backgroundmusic
{
	 public void music() throws UnsupportedAudioFileException, IOException, LineUnavailableException
	 {
		//Java liest Musik 
		
		File file = new File("Casinomusic.wav");

		AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
		Clip clip = AudioSystem.getClip();
		clip.open(audioStream);
		clip.start();
	 }
}
