package com.pixilic.javakat.toolkit;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import com.pixilic.javakat.basicrpg.MapTiles;

public class Map {
	private BufferedImage mapfromfile;
	private BufferedImage mapimg;
	private boolean[][] physicalmap; //2 dimensions is good enough for now. we only need to store whether or not something will collide!
									 //true for collisions, false for no collision
									 //this will not contain information about moving/interacting parts
	private boolean[][] encountermap;//true if random encounters can occur in this square, false otherwise
	private final double encounterRate = 0.1; //odds of beginning a random battle when stepping on an encounter-legal tile
	//private int[][][] physicalmap; //3 dimensions! whoo!
	private MapTiles mt;
	private int width = 16; //number of pixels wide the mapfile image is
	private final boolean testing = true;
	private final int tileWidth = 32;
	private final int tileHeight = 32;
	
	public Map(URL url){
		try{
			mapfromfile = ImageIO.read(url);
		} catch (IOException e){
			System.err.println("Could not read map from file. Used URL: " + url.toString());
		} catch (IllegalArgumentException e){
			System.err.println("Could not read map from file. Used URL: " + url.toString());
			e.printStackTrace();
		}
		mt = new MapTiles();
		physicalmap = new boolean[width][width]; //in the first test, it's square; it need not be
		encountermap = new boolean[width][width];
		generateMapRenderAndGeography();
	}
	
	private void generateMapRenderAndGeography(){
		if(testing){
			mapimg = new BufferedImage(600, 480, BufferedImage.TYPE_INT_ARGB);
			generateTestMapWithGeography();
			return;
		}
		mapimg = new BufferedImage(600, 480, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g = mapimg.createGraphics();
			byte[] pixels = ((DataBufferByte)mapfromfile.getRaster().getDataBuffer()).getData();
			for(int pixel : pixels){
				int a, r, gr, b;
				//TODO: REMOVE THIS TEST LINE
				System.out.println("Pixel (int): " + pixel);
				if(pixel < 0 || pixel > pixels.length) continue;
	            a = (((int) pixels[pixel] & 0xff) << 24); // alpha
	            b = ((int) pixels[pixel + 1] & 0xff); // blue
	            gr = (((int) pixels[pixel + 2] & 0xff) << 8); // green
	            r = (((int) pixels[pixel + 3] & 0xff) << 16); // red
	            System.out.println("alpha: " + a + ", blue: " + b + ", green: " + gr + ", red: " + r);
	            g.drawImage(MapTiles.which(a), pixel % width, pixel / width, null);
	            physicalmap[pixel % width][pixel / width] = (b % 2 == 1); //if blue value is odd, collision will occur; otherwise it won't
			}
            //TODO: REMOVE THIS TEST LINE
            printMap();
	}
	
	public BufferedImage getRender(){
		return mapimg;
	}
	
	private void generateTestMapWithGeography(){
		//THIS METHOD IS FOR TESTING ONLY
		for(int y = 0; y < width; y++){
			for(int x = 0; x < width; x++){
				physicalmap[x][y] = true;
				encountermap[x][y] = false;
			}
		}

		for(int y = 0; y < 5; y++){
			for(int x = 0; x < 5; x++){
				physicalmap[x][y] = false;
				encountermap[x][y] = true;
			}
		}
		
		Graphics2D g = mapimg.createGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, mapimg.getWidth(), mapimg.getHeight());
		for(int y = 0; y < width; y++){
			for(int x = 0; x < width; x++){
				if(!physicalmap[x][y]) 
					g.drawImage(MapTiles.GrassCenter, x*32, y*32, null);
				else g.drawImage(MapTiles.LoneBarrel1, x*32, y*32, null);
			}
		}
	}
	public boolean[][] getPhysicalMap(){
		return physicalmap;
	}
	private void printMap(){
		//THIS METHOD IS FOR TESTING ONLY
		System.out.println("=================");
		System.out.println("PHYSICAL MAP TEST");
		System.out.println("=================");
		
		for(boolean[] row : physicalmap){
			for(boolean collision : row){
				System.out.print(collision + "    ");
			}
			System.out.println();
		}
		System.out.println("=================");
		System.out.println("    END TEST");
		System.out.println("=================");
		
	}

	public boolean[][] getEncounterMap() {
		return encountermap;
	}

	public double getEncounterRate() {
		return encounterRate;
	}
}
