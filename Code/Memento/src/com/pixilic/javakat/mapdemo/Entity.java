//Give me a Persian rug where the center looks like Galaga
package com.pixilic.javakat.mapdemo;

import java.security.InvalidParameterException;

public abstract class Entity implements Comparable<Entity> {
	//comparable is my favorite comparable
	
	protected int width, height, zIndex;
	protected boolean col, npcCol, mob;
	
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
	
	public int getZIndex() {
		return(zIndex);
	}
	
	public void setParams(boolean c,boolean n,boolean m) {
		col=c;npcCol=n;mob=m;
	}
	
	public int compareTo(Entity o) {
		return ((Entity) o).getZIndex() - zIndex;
		
	}
}