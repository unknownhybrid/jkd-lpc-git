package com.pixilic.javakat.animationdemo;
import java.awt.image.BufferedImage;
import java.util.LinkedList;


public class AnimationPath {
	LinkedList<BufferedImage> path;
	BufferedImage currentFrame = null;
	
	public AnimationPath(){
		path = new LinkedList<BufferedImage>();		
	}
	public boolean add(BufferedImage sprite){
		//FIXME really this should throw an exception
		return path.add(sprite);
	}
	public void next(){
		//jake this is super clever. <3 --HF
		currentFrame = path.pop();
		path.push(currentFrame);
	}
	public BufferedImage getCurrentFrame() {
		if ( currentFrame == null ) next(); //I SMART
		return currentFrame;
	}
}
