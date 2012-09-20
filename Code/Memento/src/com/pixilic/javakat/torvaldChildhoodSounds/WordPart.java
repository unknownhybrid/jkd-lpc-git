package com.pixilic.javakat.torvaldChildhoodSounds;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class WordPart extends SoundChunk{
	//some sound bullshit
	protected WordPartName wpn;
	private Image picture;
	
	public WordPart(WordPartName wpn){
		this.wpn = wpn;
		picture = createImage(wpn);
	}
	private BufferedImage createImage(WordPartName wpn){
		BufferedImage img = new BufferedImage(600/10, 480/4, BufferedImage.TYPE_INT_ARGB); //FIXME:abstract the sizes out
		Graphics2D g = (Graphics2D) img.getGraphics();
		String word = "";
		switch(wpn){
		case TEST_DEATH:
			g.setColor(Color.BLACK);
			word = "Death";
			break;
		case TEST_LIFE:
			g.setColor(Color.WHITE);
			word = "Life";
			break;
		case TEST_NATURE:
			g.setColor(Color.GREEN);
			word = "Nature";
			break;
		case TEST_WATER:
			g.setColor(Color.BLUE);
			word = "Water";
			break;
		default:
			g.setColor(Color.GRAY);
			word = "ERROR";
			System.err.println("shit went down in: PictureSong.createImage(PictureSongName psn).\nproblem: the psn wasn't a valid picture song name");
			break;
		}
		g.drawString(word, 10, 10);
		return img;
	}
	public BufferedImage getImage(){
		return (BufferedImage)picture;
	}
}
