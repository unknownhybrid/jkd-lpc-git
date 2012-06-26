package com.pixilic.javakat.animationdemo;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import com.pixilic.javakat.animationdemo.PathEnum.PathName;


public class AnimationPath {
	LinkedList<BufferedImage> path;
	BufferedImage currentFrame = null;
	BufferedImage firstFrame = null;
	PathName pathname = null;
	public AnimationPath(){
		path = new LinkedList<BufferedImage>();
	}
	public boolean add(BufferedImage sprite){
		//FIXME really this should throw an exception
		return path.add(sprite);
	}
	public void setFirstFrame(BufferedImage sprite){
		firstFrame = sprite;
	}
	public void next(){
		//jake this is super clever. <3 --HF
		currentFrame = path.remove();
		path.add(currentFrame);
	}
	public BufferedImage getCurrentFrame() {
		if ( currentFrame == null ) next(); //I SMART
		return currentFrame;
	}
	public BufferedImage getFirstFrame() {
		return firstFrame;
	}
	public PathName getPathName(){
		return pathname;
	}
	public boolean setPathName(PathName pathname){
		this.pathname = pathname;
		return this.pathname == pathname;
	}
}
