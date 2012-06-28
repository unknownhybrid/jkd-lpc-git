package com.pixilic.javakat.mapdemo;

import java.security.InvalidParameterException;

public /*abstract*/ class Entity {  // not abstract for now.
									//but it should be, because an "Entity"
									//makes no sense by itself
	
	int width, height, zIndex;
	boolean col, npcCol, mob;
	
	public Entity() {
		
		width=0;
		height=0;
		zIndex=0;
		col=false;
		npcCol=false;
		mob=false;
		
	}
	
	public void setSize(int x,int y) {
		if ( x<0 || y<0 ) {
			throw new InvalidParameterException("Size param < 0");
		}
		width=x;
		height=y;
	}
	
	public void setZIndex(int z) {
		zIndex = z;
	}
	
	public void setParams(boolean c,boolean n,boolean m) {
		col=c;npcCol=n;mob=m;
	}
}