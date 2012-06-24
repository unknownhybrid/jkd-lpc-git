package com.pixilic.javakat.animationdemo;
import java.util.HashMap;

public class Animation {
	protected HashMap<PathName, AnimationPath> paths;
	protected AnimationPath currentPath;
	public enum PathName {
		RUN_UP, RUN_RIGHT, RUN_DOWN, RUN_LEFT
	}
	public enum SpriteName {
		FACE_UP, FACE_RIGHT, FACE_DOWN, FACE_LEFT
	}
	public Animation () {
		paths = new HashMap<PathName, AnimationPath>();
		currentPath = new AnimationPath();
	}
	public boolean setPath(PathName pathname){
		currentPath = paths.get(pathname);
		if(currentPath.equals(paths.get(pathname))) return true;
		else return false;
	}
	public void next(){
		currentPath.next();
	}
}