import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {

    // SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;
    final int tileSize = originalTileSize * scale; // 48x48 tile
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    // frames oer sec

    int FPS = 60;

    KeyHandler keyH = new KeyHandler();
    
    

    Thread gameThread;

    //player stuff

    int playerX = 100;
    int playerY = 100;
    int playerspeedx = 4;
    int playerspeedy = 4;
    int y = 0, x = 0;
    int xIncr = 6, yIncr = 6;



    public GamePanel() {
        
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        this.requestFocusInWindow();
    }
    


    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
        
        
    }

    @Override
    public void run() {
    	

        double drawInterval = 1000000000 / FPS; // 0.01666 seconds
        double nextDrawTime = System.nanoTime() + drawInterval;
        while (gameThread != null) {
            System.out.println("game is running");
            update();
            repaint();
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;
                if (remainingTime < 0) {
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public void update() {
        if (keyH.upPressed == true) {
            playerY -= playerspeedy;
        }
        if (keyH.downPressed == true) {
            playerY += playerspeedy;
        }
        if (keyH.leftPressed == true) {
            playerX -= playerspeedx;
        }
        if (keyH.rightPressed == true) {
            playerX += playerspeedx;
        }
        if (playerX >= 721) {
        	playerX -= playerspeedx;
        }
        if (playerX <= -1) {
        	playerX += playerspeedx;
        }
        if (playerY <= -1) {
        	playerY += playerspeedy;
        }
        if (playerY >= 530) {
        	playerY -= playerspeedy;
        }
        

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            BufferedImage image = ImageIO.read(new File("C:\\Users\\USER\\Downloads\\Setups And Extras\\gg.png"));
            g.drawImage(image, playerX, playerY, 50, 50, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
   
    

    

}
