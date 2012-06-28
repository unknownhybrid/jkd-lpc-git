package com.pixilic.javakat.mapdemo;

import java.lang.Class;

/**
 * @author clay
 */

/** a loader for XML maps of game areas*/
public class MapLoader {

	/** this map's width */
	int width;
	/** this map's height */
	int height;
	//the map
	Map theMap;


	/** creates a new map with given width and height */
	MapLoader(int width, int height) {
		theMap = new Map(width,height);
	}

	/** 
	 * adds a Entity to this MapLoader's map with given parameters
	 * @param String the Entity's type
	 * @param int the x-value of the top left corner
	 * @param int the y-value of the top left corner
	 * @param int the x-value of the bottom right corner
	 * @param int the y-value of the bottom right corner
	 * @param int the Entity's height - -1=floor, 1=ceiling, 0=wall
	 * @param boolean does this Entity have unit collision?
	 * @param boolean does this Entity have NPC collision?
	 * @param boolean is this Entity mobile?
	 * ("Wall",2,4,2,10,0)
	 */
	void addEntity(String type, int tlX, int tlY, int brX, int brY, int z, 
			boolean col, boolean npcCol, boolean mob){

		/*
		 * Okay I know this looks really confusing. But what is going on is
		 * I am using the "Class" class in Java to get a Class object,
		 * referencing the class that is in the "type" string (which will
		 * be something like "Item" or "Character"). Then, on this Class,
		 * I am calling newInstance() and storing the newly-constructed
		 * instance in the "temp" var. Basically if "type" == "Item" it will
		 * try to make a new Item object.
		 * We will of course throw exceptions if someone tries to load the
		 * "Fuck" class and java doesn't know where that class is. This is also oh
		 * exploitable but we dont' care because to exploit it you have to modify
		 * the xml in which case you could have just modified the class
		 * files if you wanted to anyway!
		 */
		
		String classToLoad = this.getClass().getPackage().getName() + "." + type;
		System.out.println("Loading " + classToLoad);
		
		Entity temp = null;
		try {
			temp = (Entity) Class.forName(classToLoad).newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if ( temp == null ) return;
		
		temp.setSize( ((brX-tlX)+1) , ((brY-tlY)+1 ) );
		temp.setZIndex(z);
		temp.setParams(col,npcCol,mob);
		
		theMap.set(tlX, tlY, temp);

	}
}