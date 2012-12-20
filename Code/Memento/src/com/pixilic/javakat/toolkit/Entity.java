package com.pixilic.javakat.toolkit;

public abstract class Entity {
	public int x;
	public int y;
	public float dx; //number between 0 and 1 denoting the visual distance
	public float dy; //an entity has moved. used for making animations from
					 //one tile to the next.
	protected int width;
	protected int height;
	
	public abstract void move(Direction d);
	public abstract void move(Direction d, boolean[][] physicalmap);
	public enum Direction{
		UP, RIGHT, DOWN, LEFT
	}
}


