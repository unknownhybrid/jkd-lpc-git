package com.pixilic.javakat.toolkit;
import java.util.HashMap;

import com.pixilic.javakat.toolkit.PathEnum.PathName;

import static com.pixilic.javakat.toolkit.PathEnum.*;


public class Animation {
	private HashMap<PathName, AnimationPath> paths;
	private AnimationPath currentPath = null;
	private boolean active = true;
	public Animation () {
		paths = new HashMap<PathName, AnimationPath>();
	}
	public boolean setPath(PathName runLeft){
		currentPath = paths.get(runLeft);
		return (currentPath.equals(paths.get(runLeft)));
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