package com.pixilic.javakat.framework;

import java.awt.Canvas;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;


public class GFXen {
	protected Canvas canvas;//drawing surface for graphics objects; sits on the frame	
	
	private boolean isRunning;
	
	private RenderData renderdata;
	
    private BufferStrategy strategy;
    private BufferedImage background;
    private Graphics2D backgroundGraphics;
    private Graphics2D graphics;
    GraphicsConfiguration config =
        GraphicsEnvironment.getLocalGraphicsEnvironment()
                .getDefaultScreenDevice()
                .getDefaultConfiguration();
    //FIXME hardcoded fps
    int fps = 15;
    long fpsWait = (long) (1.0 / fps * 1000);
    int scale = 1;
    // create a hardware accelerated image
    public final BufferedImage create(final int width, final int height,
                final boolean alpha) {
        return config.createCompatibleImage(width, height, alpha
                        ? Transparency.TRANSLUCENT : Transparency.OPAQUE);
    }
    
    public GFXen(){
        isRunning = true;
        // Canvas
        canvas = new Canvas(config);
    }
    public void setup(int width, int height){
        canvas.setSize(width * scale, height * scale);
     // Background & Buffer
        background = create(width, height, false);
        canvas.createBufferStrategy(2);
        do {
                strategy = canvas.getBufferStrategy();
        } while (strategy == null);
        backgroundGraphics = (Graphics2D) background.getGraphics();
    }
    public void update(){
    	// Update Graphics
    	long renderStart = System.nanoTime();
        do {
                Graphics2D bg = getBuffer();
                if (!isRunning) {
                    break;
                }
                
                //this next line blanks the frame
                //it needs to "blank" to whatever the last map-render was
            	backgroundGraphics.drawImage(renderdata.render(), 0, 0, null);
            	backgroundGraphics.drawImage(renderdata.render(), 0, 0, null);
            	
                renderGame(backgroundGraphics); // this calls your draw method
                // thingy
                
                //FIXME: why not just always do this first thing; even if scale = 1 that doesn't cause problems
                if (scale != 1) {
                        bg.drawImage(background, 0, 0, background.getWidth() * scale,
                        		background.getHeight() * scale, 0, 0, background.getWidth(), background.getHeight(), null);
                } else {
                        bg.drawImage(background, 0, 0, null);
                }
                bg.dispose();
        } while (!updateScreen());
        long renderTime = (System.nanoTime() - renderStart) / 1000000;
        try {
                Thread.sleep(Math.max(0, fpsWait - renderTime));
        } catch (InterruptedException e) {
                Thread.interrupted();
                return;
        }
        renderTime = (System.nanoTime() - renderStart) / 1000000;
    }
    private void renderGame(Graphics2D g){
    	
    }
    protected void setRenderData(RenderData renderdata){
    	this.renderdata = renderdata;
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
    	if(graphics != null){
    		graphics.dispose();
        	graphics = null;
    	}
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
    public boolean isRunning(){
    	return isRunning;
    }
    protected class FrameClose extends WindowAdapter {
        @Override
        public void windowClosing(final WindowEvent e) {
                isRunning = false;
        }
    }
    protected class ComponentListener implements java.awt.event.ComponentListener {  
        public void componentResized(ComponentEvent evt) {
        	//FIXME THIS SECTION MIGHT BREAK SCALING
            Component f = (Component)evt.getSource();
            Dimension d = f.getSize();
            canvas.setSize(d);
            
            //FIXME this fucking blows all over the place
            background = create(d.width, d.height, false);
                                
            //so the renderer doesn't render onto a gfx we don't see
            backgroundGraphics = (Graphics2D) background.getGraphics();
            
            //if ( mr != null ) mr.forceRender(); //fuck
            if(renderdata != null) renderdata.forceRender();
            System.out.println(d);
        }

		@Override
		public void componentHidden(ComponentEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void componentMoved(ComponentEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void componentShown(ComponentEvent arg0) {
			// TODO Auto-generated method stub
			
		}
    }
}
