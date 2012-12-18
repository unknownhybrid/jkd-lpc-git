package com.pixilic.javakat.toolkit;

public abstract class Entity {
	public int x;
	public int y;
	protected int width;
	protected int height;
	
	public abstract void move(Direction d);
	public abstract void move(Direction d, boolean[][] physicalmap);
	public enum Direction{
		UP, RIGHT, DOWN, LEFT
	}
}


