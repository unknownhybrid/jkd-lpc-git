package com.pixilic.javakat.torvaldChildhoodSounds;

import java.awt.Image;
import java.awt.image.BufferedImage;

public class PictureSong extends SoundChunk{
	private Image picture;
	//private Sound song;

	public BufferedImage getImage() {
		return (BufferedImage)picture;
	}
}
