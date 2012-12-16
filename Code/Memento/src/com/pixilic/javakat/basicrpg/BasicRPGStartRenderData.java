package com.pixilic.javakat.basicrpg;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import com.pixilic.javakat.framework.RenderData;

public class BasicRPGStartRenderData extends RenderData {

	private int cursor;
	private int opacity;
	
	private BufferedImage background;
	private BufferedImage cursorimg;
	private BufferedImage newgametext;
	private BufferedImage continuetext;
	private BufferedImage settingstext;
	private BufferedImage title;
	
	public BasicRPGStartRenderData(){
		//TODO: make all of this resolution shit 100% less hard-codey
		cursor = 0;
		opacity = 0;
		Graphics2D g;
		background = new BufferedImage(600, 480, BufferedImage.TYPE_INT_ARGB);
			g = (Graphics2D) background.getGraphics();
			g.setColor(Color.CYAN);
			g.fillRect(0, 0, 600, 360);
			g.setColor(Color.GREEN);
			g.fillRect(0, 361, 600, 120);
		cursorimg = new BufferedImage(background.getWidth()/120, background.getWidth()/120, BufferedImage.TYPE_INT_ARGB);
			g = (Graphics2D) cursorimg.getGraphics();
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, cursorimg.getWidth(), cursorimg.getHeight());
		newgametext = new BufferedImage(background.getWidth()/6, background.getWidth()/20, BufferedImage.TYPE_INT_ARGB);
			g = (Graphics2D) newgametext.createGraphics();
			g.setColor(Color.WHITE);
			g.drawString("New Game", 0, newgametext.getHeight()*2/3);
		continuetext = new BufferedImage(background.getWidth()/6, background.getWidth()/20, BufferedImage.TYPE_INT_ARGB);
			g = (Graphics2D) continuetext.getGraphics();
			g.setColor(Color.WHITE);
			g.drawString("Continue", 0, newgametext.getHeight()*2/3);
		settingstext = new BufferedImage(background.getWidth()/6, background.getWidth()/20, BufferedImage.TYPE_INT_ARGB);
			g = (Graphics2D) settingstext.getGraphics();
			g.setColor(Color.WHITE);
			g.drawString("Settings", 0, newgametext.getHeight()*2/3);
		title = new BufferedImage(background.getWidth()*1/2, background.getHeight()*1/10, BufferedImage.TYPE_INT_ARGB);
			g = (Graphics2D) title.getGraphics();
			g.setColor(Color.BLACK);
			g.drawString("BASIC RPG QUEST: THE GAME: THE MOVIE", 0, title.getHeight());
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
			g.drawImage(cursorimg, (background.getWidth()/2) - (newgametext.getWidth()/2) - (cursorimg.getWidth()*2), (background.getHeight()/2) + (30*cursor) + (newgametext.getHeight()/2), null);
			g.drawImage(newgametext, (background.getWidth()/2) - (newgametext.getWidth()/2), (background.getHeight()/2), null);
			g.drawImage(continuetext, (background.getWidth()/2) - (continuetext.getWidth()/2), (background.getHeight()/2) + 30, null);
			g.drawImage(settingstext, (background.getWidth()/2) - (settingstext.getWidth()/2), (background.getHeight()/2) + 60, null);
			g.drawImage(title, (background.getWidth()/2) - (title.getWidth()/2), (background.getHeight()/4), null);
			g.setColor(new Color(0, 0, 0, opacity));
			g.fillRect(0, 0, background.getWidth(), background.getHeight());
		return render;
	}
	
	public void update(int cursor){
		this.cursor = cursor;
	}

	public void transition(int opacity) {
		this.opacity = opacity;
	}
	
}
