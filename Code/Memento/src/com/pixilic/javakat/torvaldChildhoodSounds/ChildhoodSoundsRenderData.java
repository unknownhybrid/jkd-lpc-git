package com.pixilic.javakat.torvaldChildhoodSounds;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import com.pixilic.javakat.framework.RenderData;

public class ChildhoodSoundsRenderData extends RenderData {

	BufferedImage background; //either the default (torvald falling in darkness or something) or the picture of the chosen song
	BufferedImage arrow; //there will be two of these, but we need only load it once; then we can flip it on the other side
	BufferedImage picturebox; //the viewport for the picture or word choices
	BufferedImage pictureshadow; //a transparent layer behind the box that has a small shadow that makes the box look a little nicer
	BufferedImage textbox; //the description for each item
	BufferedImage render; //the complete render of everything
	BufferedImage healthbar; //the bar visually representing Torvald's emotional stability
	BufferedImage defBackground; //the initial, default background
	//Game over screen
	BufferedImage endBackground;
	BufferedImage endScore;
	
	boolean gameHasEnded;
	
	public ChildhoodSoundsRenderData(){
		gameHasEnded = false;
		Graphics2D g;
		background = new BufferedImage(600, 480, BufferedImage.TYPE_INT_ARGB);
			g = (Graphics2D) background.getGraphics();
			g.setColor(Color.RED);
			g.fillRect(0, 0, background.getWidth(), background.getHeight());
		defBackground = background;
		picturebox = new BufferedImage(background.getWidth()/4, background.getHeight()/4, BufferedImage.TYPE_INT_ARGB);
			g = (Graphics2D) picturebox.getGraphics();
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, picturebox.getWidth(), picturebox.getHeight());
		healthbar = new BufferedImage(background.getWidth()/3, background.getHeight()/30, BufferedImage.TYPE_INT_ARGB);
			g = (Graphics2D) healthbar.getGraphics();
			g.setColor(Color.WHITE);
			g.drawRect(0, 0, healthbar.getWidth(), healthbar.getHeight());
			g.setColor(Color.GREEN);
			g.fillRect(2, 2, healthbar.getWidth() - 2, healthbar.getHeight() - 2);
		pictureshadow = new BufferedImage(picturebox.getWidth(), picturebox.getHeight(), BufferedImage.TYPE_INT_ARGB);
			g = (Graphics2D) pictureshadow.getGraphics();
			g.setColor(Color.GRAY);
			g.fillRect(0, 0, pictureshadow.getWidth(), pictureshadow.getHeight());
		endBackground = new BufferedImage(background.getWidth(), background.getHeight(), BufferedImage.TYPE_INT_ARGB);
			g = (Graphics2D) endBackground.getGraphics();
			g.setColor(new Color(0, 0, 0, 100));
			g.fillRect(0, 0, endBackground.getWidth(), endBackground.getHeight());
		endScore = new BufferedImage(endBackground.getWidth(), endBackground.getHeight(), BufferedImage.TYPE_INT_ARGB);
			g = (Graphics2D) endScore.getGraphics();
			g.setColor(Color.WHITE);
			g.drawString("Score: ", 10, 10);
	}
	
	@Override
	public void forceRender() {
		
	}

	@Override
	public Image render() {
		
		BufferedImage render = new BufferedImage(600, 480, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = (Graphics2D) render.getGraphics();
		BufferedImage pb = 
			picturebox.getHeight()/4 >= render.getHeight()/4 
			? 
				picturebox.getSubimage(0, 0, render.getWidth()/4, render.getHeight()/4) 
				: 
				picturebox; 
		g.drawImage(background, 0, 0, null);
		g.drawImage(pictureshadow, render.getWidth()/2 - pictureshadow.getWidth()/3, render.getHeight()/2 - pictureshadow.getHeight()/3, null);
		g.drawImage(pb, render.getWidth()/2 - pb.getWidth()/2, render.getHeight()/2 - pb.getHeight()/2, null); //kinda...in the middle...ish
		//g.drawImage(textbox, render.getWidth()/2 - textbox.getWidth()/2, render.getHeight()/2 + picturebox.getHeight()/2 + render.getHeight()/12, null);
		//arrows -- g.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, null);
		g.drawImage(healthbar, 0, healthbar.getHeight()/2, null);

		if(gameHasEnded) return renderGameEnd(render, g);
		
		return render;
	}
	public Image renderGameEnd(BufferedImage render, Graphics2D g){
		g = (Graphics2D) render.getGraphics();
		g.drawImage(endBackground, 0, 0, null);
		g.drawImage(endScore, endBackground.getWidth()/3, endBackground.getHeight()/3, null);
		return render;
	}
	public void update(PictureSong ps, SoundChunk cursor_target, int health, long score, boolean gameHasEnded) {
		//background = ps != null ? ps.getImage() : defBackground;
		picturebox = cursor_target.getImage();
		healthbar = constructHealthBar(health);
		endScore = constructEndScore(score);
		this.gameHasEnded = gameHasEnded;
	}
	private BufferedImage constructEndScore(long score){
		BufferedImage endscoreimg = new BufferedImage(endScore.getWidth(), endScore.getHeight(), BufferedImage.TYPE_INT_ARGB);
			Graphics2D g = (Graphics2D) endscoreimg.getGraphics();
			g.setColor(Color.WHITE);
			g.drawString("Score: " + String.valueOf(score), 10, 10);
		return endscoreimg;
	}
	private BufferedImage constructHealthBar(int health){
		BufferedImage healthbarRender = new BufferedImage(400, 20, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = (Graphics2D) healthbarRender.getGraphics();
		
		g.setColor(Color.WHITE);
		g.drawRect(0, 0, 400, 20);
		g.setColor(Color.GREEN);
		g.fillRect(2, 2, 396*health/200, 18); //fill in the bar an amount equal to a percentage of your max health
		
		return healthbarRender;
	}
}
