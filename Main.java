import javax.swing.JFrame;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.*;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        GamePanel gamePanel = new GamePanel () ;
        window.add(gamePanel) ;
        gamePanel.startGameThread();

        window.pack () ;
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
        window.setResizable (false) ;
        window.setTitle ("2D Adventure");
        window. setLocationRelativeTo (null) ;
        window. setVisible (true) ;
        
        gamePanel.startGameThread();
        
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("C:\\Users\\USER\\Downloads\\Setups And Extras\\darkmusic.mp3"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } 
        catch (LineUnavailableException e) {
            System.out.println("The audio line is unavailable.");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } 

        }
    	

}
