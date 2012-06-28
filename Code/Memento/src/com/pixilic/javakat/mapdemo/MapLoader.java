package com.pixilic.javakat.mapdemo;

import static com.pixilic.javakat.*;

/**
 * @author clay
 */

/** a loader for XML maps of game areas*/
public class MapLoader {
 
 /** this map's width */
 int width;
 /** this map's height */
 int height;
 /** this map's grid of Permanents */
 Permanent[][] permanents;

 
 /** creates a new map with given width and height */
 MapLoader(int width, int height) {
  this.width = width;
  this.height = height;
 }
 
 /** 
  * adds a Permanent to this MapLoader's map with given parameters
  * @param String the Permanent's name
  * @param int the x-value of the top left corner
  * @param int the y-value of the top left corner
  * @param int the x-value of the bottom right corner
  * @param int the y-value of the bottom right corner
  * @param int the Permanent's height - -1=floor, 1=ceiling, 0=wall
  * @param boolean does this Permanent have unit collision?
  * @param boolean does this Permanent have NPC collision?
  * @param boolean is this Permanent mobile?
  * ("Wall",2,4,2,10,0)
  */
 void addPermanent(String name, int tlX, int tlY, int brX, int brY, int z, 
   boolean col, boolean npcCol, boolean mob){
  // FIXME
  // correct this constructor
  permanents[tlX][tlY] = new Permanent(name);
 }