package com.pixilic.javakat.toolkit;

import java.awt.event.KeyEvent;

public class PathEnum {
	public enum PathName {
		RUN_UP, RUN_RIGHT, RUN_DOWN, RUN_LEFT
	}
	public enum SpriteName {
		FACE_UP, FACE_RIGHT, FACE_DOWN, FACE_LEFT
	}
	public PathName getPathNameFromKey(KeyEvent e){
    	switch(e.getKeyCode()){
		case KeyEvent.VK_UP:
			return PathEnum.PathName.RUN_UP;
		case KeyEvent.VK_RIGHT:
			return PathEnum.PathName.RUN_RIGHT;
		case KeyEvent.VK_DOWN:
			return PathEnum.PathName.RUN_DOWN;
		case KeyEvent.VK_LEFT:
			return PathEnum.PathName.RUN_LEFT;
		default:
			return null;
		}
    }
}
