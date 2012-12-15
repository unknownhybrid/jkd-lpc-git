package com.pixilic.javakat.mapdemo;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.Stack;
import com.pixilic.javakat.animationdemo.PathEnum.PathName;
import com.pixilic.javakat.framework.GMan;
import com.pixilic.javakat.framework.IOen;
import com.pixilic.javakat.framework.RenderData;

public class Mapman extends GMan{

	//FIXME hardcoding
    private XMLMapReader xmlmr;
    private MapRenderer mr;
    private Map map;
    private Character player;
    private int moveTimer = 0;
	
    public Mapman(){        
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
    		map.move();
    		moveTimer = 3;
    	} else {
    		
    	}
    	moveTimer--;
    	if(evt != null){
    		PathName p = getPathNameFromKey(evt instanceof KeyEvent && isLegalKey(evt) ? (KeyEvent)evt : null);
    		if(p != null && p.equals(player.getAnimation().getCurrentPath().getPathName())){
    			player.getAnimation().next();
    		} else if (p != null){
	    		player.getAnimation().setPath(p);
	    		player.setFacing(getDirectionFromPathName(p));
    		}
    		player.getAnimation().setActive(true);
    		player.setMotion(true);//FIXME player never runs
    	} else {
    		player.getAnimation().setPath(player.getAnimation().getCurrentPath().getPathName());
    		player.setFacing(getDirectionFromPathName(player.getAnimation().getCurrentPath().getPathName()));
    		player.getAnimation().setActive(false);
    		player.setMotion(false);
    	}
	}
	private boolean isLegalKey(InputEvent evt) {
		KeyEvent e = evt instanceof KeyEvent ? (KeyEvent) evt : null;
		if(e == null) return false;
		if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_LEFT) return true;
		else return false;
	}

	private PathName getPathNameFromKey(KeyEvent e){
		if(e == null) return null;
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

	@Override
	public RenderData getRenderData() {
		return mr;
	}
	public void setRenderData(MapRenderer mr) {
		this.mr = mr;
	}

	@Override
	public GMan getSwapClass() {
		// TODO Auto-generated method stub
		return null;
	}
}
