package com.pixilic.javakat.basicrpg;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class MapTiles {
	
	private static BufferedImage Grass;
		public static BufferedImage GrassCenter;
		
	private final static int GRASS_CENTER = -16777216; //arbitrary constant; you've got to start somewhere!
		
	private static BufferedImage Barrel;
		public static BufferedImage LoneBarrel1;
	private final static int LONE_BARREL_1 = 1;
	
	public MapTiles() {
		
		URL url = BasicRPG.class.getResource("rsrc/images/grass.png");
		
		try{
			Grass = ImageIO.read(url);
		} catch (IOException e){
			System.err.println("ERROR: Failed to load game resource: rsrc/images/grass.png");
		}
		GrassCenter = Grass.getSubimage(32, 96, 32, 32);
		
		
		
		
		url = BasicRPG.class.getResource("rsrc/images/barrel.png");
		
		try{
			Barrel = ImageIO.read(url);
		} catch (IOException e){
			System.err.println("ERROR: Failed to load game resource rsrc/images/barrel.png");
		}
		
		LoneBarrel1 = Barrel.getSubimage(0, 0, 32, 32);
		
	}
	public static BufferedImage which(int key){
		switch(key){
		case GRASS_CENTER:
			return GrassCenter;
		case LONE_BARREL_1:
			return LoneBarrel1;
		default:
			System.err.println("MapTiles.java, which(int key): Invalid key value. Key does not map to any defined tile image.");
			return null;
		}
	}
}
