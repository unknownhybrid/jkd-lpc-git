package com.pixilic.javakat.mapdemo;
import java.awt.Canvas;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
//import java.io.IOException;
//import java.net.URL;
//import java.util.Stack;

//import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

//import com.pixilic.javakat.animationdemo.PathEnum.PathName;

public class Origin extends Thread implements KeyListener {
    private boolean isRunning = true;
    private Canvas canvas;
    private BufferStrategy strategy;
    private BufferedImage background;
    private Graphics2D backgroundGraphics;
    private Graphics2D graphics;
    private JFrame frame;
    
    //FIXME hardcoding
    private XMLMapReader xmlmr;
    private MapRenderer mr;
    //keyboard
    //boolean[] keys;  
    //int lastDown=0; //the last key pressed because java fucking sucks
    //private Stack<PathName> arrowDown;
    
    private int width = 600;
    private int height = 480;
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
        
        //keyboard shit
        //frame.addKeyListener(this);
        //arrowDown = new Stack<PathName>();
        
        //load the images
        //FIXME: this is a shitty place to load images
        xmlmr = new XMLMapReader();
		mr = new MapRenderer(xmlmr.readMap("Cloverfield"));
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
        mr.renderMap(backgroundGraphics);
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
                        //it needs to "blank" to whatever the last map-render was
                    	mr.renderMap(backgroundGraphics);
                    	
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
    	
	}
    
    public void renderGame(Graphics2D g) {
    	//g.drawImage(testSM, 0, 0, canvas);
    	mr.renderMap(g);
    }

    
    //we don't need this
    public void keyTyped(KeyEvent e) {    }
     
    public void keyPressed(KeyEvent e) {
    	//PathName pathname = getPathNameFromKey(e);
    	//if(pathname != null){
    		//if(!arrowDown.contains(pathname)){
    			//arrowDown.push(pathname);
    		//}
    	//}
    	//int kc = e.getKeyCode();
    	//keys[kc] = true;
    	//lastDown=kc;
    	//we set the last key to be pressed because java has no fucking way
    	//to disable repeating key presses meaning that if you use a 
    	//stack here they infinitely fill up the stack with duplicate keypresses
    	//for as long as you hold the key. this is the hackiest workaround
    	//ever but it's all I can figure to do and it gives the 
    	//desired behavior so at this point fuck it
    }

    public void keyReleased(KeyEvent e) { 
    	//PathName pathname = getPathNameFromKey(e);
    	//if(arrowDown.contains(pathname)){
    		//arrowDown.remove(pathname);
    	//}
    } 
    /*private PathName getPathNameFromKey(KeyEvent e){
    	switch(e.getKeyCode()){
		case KeyEvent.VK_UP:
			return PathEnum.PathName.RUN_UP;
		case KeyEvent.VK_RIGHT:
			return PathEnum.PathName.RUN_RIGHT;
		case KeyEvent.VK_DOWN:
			return PathEnum.PathName.RUN_DOWN;
		case KeyEvent.VK_LEFT:
			return PathEnum.PathName.RUN_LEFT;
		default:
			return null;
		}
    }*/
    public static void main(final String args[]) {
        new Origin();
    }
}