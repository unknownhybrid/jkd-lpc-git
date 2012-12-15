package com.pixilic.javakat.basicrpg;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import com.pixilic.javakat.framework.RenderData;

public class BasicRPGStartRenderData extends RenderData {

	private int cursor;
	
	private BufferedImage background;
	private BufferedImage cursorimg;
	private BufferedImage newgametext;
	private BufferedImage continuetext;
	private BufferedImage settingstext;
	
	public BasicRPGStartRenderData(){
		//TODO: make all of this resolution shit 100% less hard-codey
		cursor = 0;
		Graphics2D g;
		background = new BufferedImage(600, 480, BufferedImage.TYPE_INT_ARGB);
			g = (Graphics2D) background.getGraphics();
			g.setColor(Color.CYAN);
			g.fillRect(0, 0, 600, 360);
			g.setColor(Color.GREEN);
			g.fillRect(0, 361, 600, 120);
		cursorimg = new BufferedImage(background.getWidth()/30, background.getWidth()/30, BufferedImage.TYPE_INT_ARGB);
			g = (Graphics2D) cursorimg.getGraphics();
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, 20, 20);
		newgametext = new BufferedImage(background.getWidth()/2, background.getWidth()/20, BufferedImage.TYPE_INT_RGB);
			g = (Graphics2D) newgametext.getGraphics();
			g.setColor(Color.WHITE);
			g.drawString("New Game", 20, 20);
		continuetext = new BufferedImage(background.getWidth()/6, background.getWidth()/20, BufferedImage.TYPE_INT_ARGB);
			g = (Graphics2D) continuetext.getGraphics();
			g.setColor(Color.WHITE);
			g.drawString("Continue", 20, 20);
		settingstext = new BufferedImage(background.getWidth()/6, background.getWidth()/20, BufferedImage.TYPE_INT_ARGB);
			g = (Graphics2D) settingstext.getGraphics();
			g.setColor(Color.WHITE);
			g.drawString("Settings", 20, 20);
	}
	
	@Override
	public void forceRender() {
		// TODO Auto-generated method stub

	}

	@Override
	public Image render() {
		BufferedImage render = new BufferedImage(600, 480, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g;
			g = (Graphics2D) render.getGraphics();
			g.drawImage(background, 0, 0, 600, 480, null);
			g.drawImage(cursorimg, (background.getWidth()/2) - (newgametext.getWidth()/2) - 30, (background.getHeight()*1/2) + (30*cursor) + 5, cursorimg.getWidth(), cursorimg.getHeight(), null);
			g.drawImage(newgametext, (background.getWidth()/2) - (newgametext.getWidth()/2), (background.getHeight()*1/2), newgametext.getWidth(), newgametext.getHeight(), null);
			//g.drawImage(continuetext, (background.getWidth()/2) - (newgametext.getWidth()/2), (background.getHeight()*1/2) + 20, continuetext.getWidth(), continuetext.getHeight(), null);
			//g.drawImage(settingstext, (background.getWidth()/2) - (newgametext.getWidth()/2), (background.getHeight()*1/2) + 40, settingstext.getWidth(), continuetext.getHeight(), null);
		return render;
	}
	
	public void update(int cursor){
		this.cursor = cursor;
	}
	
}
