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
    int playerY = 400;
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
        double timer = 0.01666;
        while (gameThread != null) {
            x += xIncr;
            y += yIncr;
            timer += 0.01666;
            if (timer >= 30) {
            	xIncr = 0;
        		yIncr = 0;
        		playerspeedx = 0;
        		playerspeedy = 0;
            
            }
            //9.0339349424984E14
            //9.0346717425095E14
            System.out.println(timer);
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
        if (playerX >= getWidth() - 50) {
        	playerX -= playerspeedx;
        }
        if (playerX <= -1) {
        	playerX += playerspeedx;
        }
        if (playerY <= -1) {
        	playerY += playerspeedy;
        }
        if (playerY >= getHeight() - 50) {
        	playerY -= playerspeedy;
        }
        
        if (x <= 0 || x >= getWidth() - 100) {
            xIncr = -xIncr;
        }
        if (y <= 0 || y >= getHeight() - 100) {
            yIncr = -yIncr;
        }
        
        

        if(playerX >= x && playerX <= x + 100) {
        	if (playerY >= y &&  playerY <= y +100) {
        		
        		xIncr = 0;
        		yIncr = 0;
        		playerspeedx = 0;
        		playerspeedy = 0;
        	}
        	
        }
      
        

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            BufferedImage image = ImageIO.read(new File("C:\\Users\\USER\\Downloads\\Setups And Extras\\gg.png"));
            BufferedImage imaged = ImageIO.read(new File("C:\\Users\\USER\\Downloads\\Setups And Extras\\gg.png"));
            BufferedImage imagel = ImageIO.read(new File("C:\\Users\\USER\\Downloads\\Setups And Extras\\gg.png"));
            BufferedImage imager = ImageIO.read(new File("C:\\Users\\USER\\Downloads\\Setups And Extras\\gg.png"));
            BufferedImage image2 = ImageIO.read(new File("C:\\Users\\USER\\Downloads\\Setups And Extras\\scary.png"));
            g.drawImage(image, playerX, playerY, 50, 50, null);
            g.drawImage(image2, x, y, 100, 100, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    


    

}
