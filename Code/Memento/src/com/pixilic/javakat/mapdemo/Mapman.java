package com.pixilic.javakat.mapdemo;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.Stack;
import com.pixilic.javakat.animationdemo.PathEnum.PathName;
import com.pixilic.javakat.framework.GMan;
import com.pixilic.javakat.framework.IOen;

public class Mapman extends GMan{

	//FIXME hardcoding
    private XMLMapReader xmlmr;
    private MapRenderer mr;
    private Map map;
    private Character player;
    private Stack<PathName> arrowDown;
    private int moveTimer = 0;
	
    public Mapman(){
    	arrowDown = new Stack<PathName>();
        
        //load the images
        //FIXME: this is a shitty place to load images
        xmlmr = new XMLMapReader();
        map = xmlmr.readMap("realtestmap");
		mr = new MapRenderer(map);
		
		//player = new Character(true); //the true means he's the player character!
		player = map.getPlayerCharacter();
    }
    
	@Override
	public boolean switching() {
		return false;
	}
	
	@Override
	public void update(InputEvent evt) {
		if(moveTimer <= 0) {
    		System.out.print("!");
    		map.move();
    		moveTimer = 1;
    	} else {
    		System.out.print(".");
    	}
    	moveTimer--;
    	if(evt != null){
    		PathName p = getPathNameFromKey(evt instanceof KeyEvent ? (KeyEvent)evt : null);
    		if(p.equals(player.getAnimation().getCurrentPath().getPathName())){
    			player.getAnimation().next();
    		} else {
	    		player.getAnimation().setPath(p);
	    		player.setFacing(getDirectionFromPathName(p));
    		}
    		player.getAnimation().setActive(true);
    		player.setMotion(true);//FIXME player never runs
    	} else {
    		player.getAnimation().setActive(false);
    		player.setMotion(false);
    	}
	}
	private PathName getPathNameFromKey(KeyEvent e){
    	switch(e.getKeyCode()){
		case KeyEvent.VK_UP:
			return PathName.RUN_UP;
		case KeyEvent.VK_RIGHT:
			return PathName.RUN_RIGHT;
		case KeyEvent.VK_DOWN:
			return PathName.RUN_DOWN;
		case KeyEvent.VK_LEFT:
			return PathName.RUN_LEFT;
		default:
			return null;
		}
    }
	private Direction getDirectionFromPathName(PathName p) {
    	switch(p){
			case RUN_UP:
				return Direction.UP;
			case RUN_RIGHT:
				return Direction.RIGHT;
			case RUN_DOWN:
				return Direction.DOWN;
			case RUN_LEFT:
				return Direction.LEFT;
			default:
				return null;
		}
    }
}
