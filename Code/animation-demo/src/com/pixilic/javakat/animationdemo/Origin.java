package com.pixilic.javakat.animationdemo;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import com.pixilic.javakat.animationdemo.PathEnum.PathName;

public class Origin extends Thread implements KeyListener {
    private boolean isRunning = true;
    private Canvas canvas;
    private BufferStrategy strategy;
    private BufferedImage background;
    private Graphics2D backgroundGraphics;
    private Graphics2D graphics;
    private JFrame frame;
    
    //FIXME hardcoding
    SpriteMap testSM;
    Animation testAni;
    
    private int width = 320;
    private int height = 240;
    private int scale = 1;
    private GraphicsConfiguration config =
                GraphicsEnvironment.getLocalGraphicsEnvironment()
                        .getDefaultScreenDevice()
                        .getDefaultConfiguration();

    // create a hardware accelerated image
    public final BufferedImage create(final int width, final int height,
                final boolean alpha) {
        return config.createCompatibleImage(width, height, alpha
                        ? Transparency.TRANSLUCENT : Transparency.OPAQUE);
    }

    // Setup
    public Origin() {
        // JFrame
        frame = new JFrame();
        frame.addWindowListener(new FrameClose());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(width * scale, height * scale);
        frame.setVisible(true);

        // Canvas
        canvas = new Canvas(config);
        canvas.setSize(width * scale, height * scale);
        frame.add(canvas, 0);
        
        //add keylistener
        frame.addKeyListener(this);
        
        //load the images
        //FIXME: this is a shitty place to load images
        testSM = new SpriteMap("female_walkcycle",64,64);
        testAni = testSM.createPaths();
        //FIXME: change the path here
        testAni.setPath(PathName.RUN_LEFT);
        
        // Background & Buffer
        background = create(width, height, false);
        canvas.createBufferStrategy(2);
        do {
                strategy = canvas.getBufferStrategy();
        } while (strategy == null);
        start();
    }

    private class FrameClose extends WindowAdapter {
        @Override
        public void windowClosing(final WindowEvent e) {
                isRunning = false;
        }
    }

    // Screen and buffer stuff
    private Graphics2D getBuffer() {
        if (graphics == null) {
                try {
                        graphics = (Graphics2D) strategy.getDrawGraphics();
                } catch (IllegalStateException e) {
                        return null;
                }
        }
        return graphics;
    }

    private boolean updateScreen() {
        graphics.dispose();
        graphics = null;
        try {
                strategy.show();
                Toolkit.getDefaultToolkit().sync();
                return (!strategy.contentsLost());

        } catch (NullPointerException e) {
                return true;

        } catch (IllegalStateException e) {
                return true;
        }
    }

    public void run() {
    	
    	backgroundGraphics = (Graphics2D) background.getGraphics();
    	
        //FIXME hardcoded fps
        int fps = 15;
        long fpsWait = (long) (1.0 / fps * 1000);
        
        main: while (isRunning) {
                long renderStart = System.nanoTime();
                updateGame();

                // Update Graphics
                do {
                        Graphics2D bg = getBuffer();
                        if (!isRunning) {
                                break main;
                        }
                        
                        //this next line blanks the frame
                    	backgroundGraphics.clearRect(0, 0, width, height);
                    	
                        renderGame(backgroundGraphics); // this calls your draw method
                        // thingy
                        if (scale != 1) {
                                bg.drawImage(background, 0, 0, width * scale, height
                                                * scale, 0, 0, width, height, null);
                        } else {
                                bg.drawImage(background, 0, 0, null);
                        }
                        bg.dispose();
                } while (!updateScreen());

                // Better do some FPS limiting here
                long renderTime = (System.nanoTime() - renderStart) / 1000000;
                try {
                        Thread.sleep(Math.max(0, fpsWait - renderTime));
                } catch (InterruptedException e) {
                        Thread.interrupted();
                        break;
                }
                renderTime = (System.nanoTime() - renderStart) / 1000000;

        }
        frame.dispose();
    }

    public void updateGame() {
        // update game logic here
    }
    
    public void renderGame(Graphics2D g) {
    	//g.drawImage(testSM, 0, 0, canvas);
    	if(testAni.isActive()){
    		g.drawImage(testAni.getCurrentPath().getCurrentFrame(), 0, 0, canvas); //hahahaaha
    		testAni.next();   	
    	} else g.drawImage(testAni.getCurrentPath().getFirstFrame(), 0, 0, canvas);
    }

    
    public void keyTyped(KeyEvent e) {
       
    }
     
    public void keyPressed(KeyEvent e) {
    	testAni.setActive(true);
        switch(e.getKeyCode()){
    	case KeyEvent.VK_UP:
    		testAni.setPath(PathEnum.PathName.RUN_UP);
    		break;
        case KeyEvent.VK_RIGHT:
        	testAni.setPath(PathEnum.PathName.RUN_RIGHT);
        	break;
    	case KeyEvent.VK_DOWN:
    		testAni.setPath(PathEnum.PathName.RUN_DOWN);
    		break;
    	case KeyEvent.VK_LEFT:
    		testAni.setPath(PathEnum.PathName.RUN_LEFT);
    		break;
    	default:
    		break;
        }
    }
     
    public void keyReleased(KeyEvent e) {
        if((e.getKeyCode() == KeyEvent.VK_UP && testAni.getCurrentPathName() == PathEnum.PathName.RUN_UP) 
        		|| (e.getKeyCode() == KeyEvent.VK_RIGHT && testAni.getCurrentPathName() == PathEnum.PathName.RUN_RIGHT)
        		|| (e.getKeyCode() == KeyEvent.VK_DOWN && testAni.getCurrentPathName() == PathEnum.PathName.RUN_DOWN)
        		|| (e.getKeyCode() == KeyEvent.VK_LEFT && testAni.getCurrentPathName() == PathEnum.PathName.RUN_LEFT)){
        	testAni.setActive(false);
        }
    }
     
    public static void main(final String args[]) {
    	
        new Origin();
    }
}