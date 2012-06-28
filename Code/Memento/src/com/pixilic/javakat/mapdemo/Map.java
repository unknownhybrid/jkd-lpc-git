package com.pixilic.javakat.mapdemo;

public class Map {

	Entity[][] ents;
	int width;
	int height;
		
	public Map(int width, int height) {
		this.ents = new Entity[width][height];
		for ( int x=0;x<width;x++ ) {
			for ( int y=0;y<height;y++ ) {
				ents[x][y]=null;
			}
		}
		this.width = width;
		this.height = height;
	}

	public void set(int x,int y,Entity en) {
		ents[x][y]=en;
	}

}