//Give me a Persian rug where the center looks like Galaga
package com.pixilic.javakat.mapdemo;

import java.security.InvalidParameterException;

<<<<<<< HEAD
public abstract class Entity {  // not abstract for now.
									//but it should be, because an "Entity"
									//makes no sense by itself
=======
public abstract class Entity implements Comparable<Entity> {
	//comparable is my favorite comparable
	
	protected int width, height, zIndex;
	protected boolean col, npcCol, mob;
	protected String name;
	public boolean isRendered = false;
>>>>>>> 73b511afa2a881962a5f7d5643c6a7b36114dbf1
	
	
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

	public void setName(String n) {
				name=n;
				//I love the tab key
	}
	
	public String getName() {
		return name;
	}
}