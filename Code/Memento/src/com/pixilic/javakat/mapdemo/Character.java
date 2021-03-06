package com.pixilic.javakat.mapdemo;

import com.pixilic.javakat.animationdemo.Animation;
import com.pixilic.javakat.animationdemo.SpriteMap;

public class Character extends Entity {
	
	private boolean isMoving, isRunning, isPlayer;
	private Direction facing;
    private SpriteMap spriteMap;
    private Animation anim;
	
	public Character(){
		this(false);
	}
	public Character(boolean isPlayer){
		facing = Direction.DOWN;
		isMoving = false;
		isRunning = false;
		this.isPlayer = isPlayer;
		//FIXME: HARDCODING THE IMAGE IS BAAAAAAAD
        spriteMap = new SpriteMap("female_walkcycle",64,64);
        anim = spriteMap.createPaths();
	}
	public void setFacing(Direction facing){
		this.facing = facing;
	}
	public Direction getFacing(){
		return facing;
	}
	
	public void setMotion(boolean isMoving, boolean isRunning){
		this.isMoving = isMoving;
		this.isRunning = isRunning;
	}
	public void setMotion(boolean isMoving) {
		this.setMotion(isMoving,false);
	}
	
	public boolean isMoving(){
		return isMoving;
	}
	public boolean isRunning(){
		return isRunning;
	}
	public boolean isPlayer(){
		return isPlayer;
	}
	public Animation getAnimation(){
		return anim;
	}
}
