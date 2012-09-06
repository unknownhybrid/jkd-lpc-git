package com.pixilic.javakat.framework;

import java.awt.event.InputEvent;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Origin extends Thread{
	protected boolean isRunning;
	protected JFrame frame; //window of the game
	protected IOen io;   //input engine
	protected GFXen gfx; //graphics engine
	protected SFXen sfx; //sound engine
	
	private int width = 600;
	private int height = 480;
    
    public GMan gman; //game manager
	
	public Origin() {
		isRunning = true;
		JFrame frame = new JFrame();	
		
		io = new IOen();
		gfx = new GFXen();
		
		gman = new com.pixilic.javakat.mapdemo.Mapman();
		
		frameSetup();
		start();
	}
	public void run(){
		do {
			updateLogic();
			updateGraphicsData();
			updateGraphics();
			if(gman.switching()){
				
			}
		} while(isRunning && gfx.isRunning());
		disposeGraphics();
	}
	public void frameSetup(){
	    frame = new JFrame();
        frame.addWindowListener(gfx.new FrameClose());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(width * gfx.scale, height * gfx.scale);
        frame.addComponentListener(gfx.new ComponentListener());
        //-- instead of adding to the frame, add to the layout
        frame.add(gfx.canvas, 0);
        //container.add (canvas, BorderLayout.CENTER);
        
        //show the sucker 
        frame.setVisible(true);
        
        //do the canvas up real nice-like
		gfx.setup(width, height);
        
        //keyboard shit
        frame.addKeyListener(io);
	}
	public void updateGraphics(){
		gfx.update();
	}
	public void updateGraphicsData(){
		gfx.setRenderData(gman.getRenderData());
	}
	public InputEvent pollInput(){
		return io.update();
	}
	public void updateLogic(){
		gman.update(pollInput());
	}
	public void disposeGraphics(){
		frame.dispose(); //I would really like this to happen in the GFXen but if it can't, oh well
	}
}
