package com.pixilic.javakat.animationdemo;
import java.util.HashMap;

import static com.pixilic.javakat.animationdemo.PathEnum.*;


public class Animation {
	private HashMap<PathName, AnimationPath> paths;
	private AnimationPath currentPath = null;
	public Animation () {
		paths = new HashMap<PathName, AnimationPath>();
		//currentPath = new AnimationPath(); // why would you do this
	}
	public boolean setPath(PathName pathname){
		currentPath = paths.get(pathname);
		if(currentPath.equals(paths.get(pathname))) return true; //is this really necessary?
		else return false;
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
}