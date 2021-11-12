package main;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
public final class Main {
    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        //Hintergrundmusik wird gestartet
        Backgroundmusic music = new Backgroundmusic();
        music.music();
        //run code, if IO error is encountered print error log
        try {
            new JackManager();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}