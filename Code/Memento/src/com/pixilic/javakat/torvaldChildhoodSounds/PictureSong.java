package com.pixilic.javakat.torvaldChildhoodSounds;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class PictureSong extends SoundChunk{
	protected PictureSongName psn;
	private Image picture;
	//private Sound song;

	public PictureSong(PictureSongName psn){
		this.psn = psn;
		picture = createImage(psn);
	}
	public BufferedImage getImage() {
		return (BufferedImage)picture;
	}
	private BufferedImage createImage(PictureSongName psn){
		BufferedImage img = new BufferedImage(600, 480, BufferedImage.TYPE_INT_ARGB); //FIXME:abstract the sizes out
			Graphics2D g = (Graphics2D) img.getGraphics();
			switch(psn){
			case TEST_BLUE:
				g.setColor(Color.BLUE);
				break;
			case TEST_BLACK:
				g.setColor(Color.BLACK);
				break;
			case TEST_WHITE:
				g.setColor(Color.WHITE);
				break;
			default:
				g.setColor(Color.GRAY);
				System.err.println("shit went down in: PictureSong.createImage(PictureSongName psn).\nproblem: the psn wasn't a valid picture song name");
				break;
			}
			g.fillRect(0, 0, img.getWidth(), img.getHeight());
		return img;
	}
}
