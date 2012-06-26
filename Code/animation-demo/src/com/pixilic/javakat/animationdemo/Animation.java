package com.pixilic.javakat.animationdemo;
import java.util.HashMap;

import static com.pixilic.javakat.animationdemo.PathEnum.*;


public class Animation {
	private HashMap<PathName, AnimationPath> paths;
	private AnimationPath currentPath = null;
	private boolean active = true;
	public Animation () {
		paths = new HashMap<PathName, AnimationPath>();
		//currentPath = new AnimationPath(); // why would you do this
	}
	public boolean setPath(PathName pathname){
		currentPath = paths.get(pathname);
		return (currentPath.equals(paths.get(pathname)));
	}
	public void next(){
		currentPath.next();
	}
	public boolean addPath(PathName pn, AnimationPath p) {

		if ( currentPath == null ) currentPath = p; //I SMART
		
		//FIXME really this should throw an exception
		return paths.put(pn, p) != null; // Get it? PN? P?
	}
	public AnimationPath getCurrentPath() {
		return(currentPath);
	}
	public PathName getCurrentPathName() {
		return currentPath.getPathName();
	}
	public boolean setActive(boolean isActive){
		active = isActive;
		return active == isActive;
	}
	public boolean isActive(){
		return active;
	}
}