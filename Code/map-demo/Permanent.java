package com.pixilic.javakat.mapdemo;

import com.pixilic.javakat.animationdemo.Animation;
import com.pixilic.javakat.mapdemo.PermanentType;
import com.pixilic.javakat.mapdemo.Direction;

public abstract class Permanent {
	protected int xPos;
	protected int yPos;
	protected int zPos; //can be renamed 'layer' if that makes better sense

	protected Animation anim;
	
	protected boolean hasUnitCollision;
	protected boolean hasNPCUnitCollision;
	protected boolean isMobile;

	protected String name;
	
	
	//functionality
	public static PermanentType determineType(Permanent p){
		//XMLReader bullshit. We'll want to read in the permanent-definition XML, which is an updated conceptual version of the "tile-definition" XML we were discussing yesterday.
		//Once it's read, we'll make a Hashmap of <string, PermanentType> that pulls in the name of each permanent in the file and affiliates it with its permanent type (should be defined in the XML).
		HashMap<String, PermanentType> permanentHash = new HashMap<String, PermanentType>();
		if(permanentHash.contains(p.Name)){
			return permanentHash.at(p.Name);
		} else return null;
	}
	public static Permanent producePermanent(Permanent p){
		PermanentType type = Permanent.determineType(p);
		switch(type){
		case ITEM:
			return new Item(p);
		case CHARACTER:
			return new Character(p);
		case TILE:
			return new Tile(p);
		default:
			return p; //FIXME
		}
	}
	public static Permanent producePermanent(PermanentType type){
		switch(type){
			case ITEM:
				return new Item(p);
			case CHARACTER:
				return new Character(p);
			case TILE:
				return new Tile(p);
			default:
				return new Permanent(); //FIXME
			}
	}
	public void move(Direction dir){
	//Standard coordinate plane:
	//positive y direction is UP
	//positive x direction is RIGHT
	
	//This function needs a supplementary Map function "canMove" that tests whether or not there is already a colliding object in the space this permanent is attempting to enter.
	//That way, calling this function can not end poorly.
		switch(dir){
		case UP:
			yPos++;
			break;
		case RIGHT:
			xPos++;
			break;
		case DOWN:
			yPos--;
			break;
		case LEFT:
			xPos--;
			break;
		default:
			break;
		}
		
	}
	
	
	
}