package com.pixilic.javakat.basicrpg;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import com.pixilic.javakat.framework.GMan;
import com.pixilic.javakat.framework.RenderData;
import com.pixilic.javakat.toolkit.Entity;
import com.pixilic.javakat.toolkit.Map;

public class BasicRPGMan extends GMan {

	protected BasicRPGRenderData renderdata;
	private boolean isSwitching;
	private boolean isCombatMode;
	private Character player;
    private Map map;
	
	public BasicRPGMan(){
		renderdata = new BasicRPGRenderData(null, null, 0, 0);
		isSwitching = true;
		player = null;
		map = null;
	}
	public BasicRPGMan(Character player){
		isSwitching = false;
		this.player = player;
		map = new Map(BasicRPG.class.getResource("rsrc/maps/map1/map.png"));
		renderdata = new BasicRPGRenderData(map.getRender(), player.sprite.getRender(), player.x, player.y);
	}
	
	@Override
	public void update(InputEvent evt) {
		if(evt instanceof KeyEvent){
			switch(((KeyEvent)evt).getKeyCode()){
			case KeyEvent.VK_UP:
				player.move(Entity.Direction.UP, map.getPhysicalMap());
				if(player.checkForEncounter(map.getEncounterMap(), map.getEncounterRate())){
					isCombatMode = true;
					isSwitching = true;
				}
				break;
			case KeyEvent.VK_RIGHT:
				player.move(Entity.Direction.RIGHT, map.getPhysicalMap());
				if(player.checkForEncounter(map.getEncounterMap(), map.getEncounterRate())){
					isCombatMode = true;
					isSwitching = true;
				}
				break;
			case KeyEvent.VK_DOWN:
				player.move(Entity.Direction.DOWN, map.getPhysicalMap());
				if(player.checkForEncounter(map.getEncounterMap(), map.getEncounterRate())){
					isCombatMode = true;
					isSwitching = true;
				}
				break;
			case KeyEvent.VK_LEFT:
				player.move(Entity.Direction.LEFT, map.getPhysicalMap());
				if(player.checkForEncounter(map.getEncounterMap(), map.getEncounterRate())){
					isCombatMode = true;
					isSwitching = true;
				}
				break;
			default:
				break;
			}
		}
		updateRenderData(player.sprite.getRender(), player.x + (player.dx/9), player.y + (player.dy/9), player.isMoving);
	}
	public void update(){
		//player.move(); this is dumb and should die
	}
	@Override
	public boolean switching() {
		return isSwitching;
	}

	@Override
	public RenderData getRenderData() {
		return renderdata;
	}
	public void updateRenderData(BufferedImage sprite, float x, float y, boolean isMoving){
		renderdata.update(sprite, x, y, isMoving);
	}
	@Override
	public GMan getSwapClass() {
		// TODO Auto-generated method stub
		if(player == null) return new BasicRPGNewCharMan();
		else if(isCombatMode) return new BasicRPGBattleMan(player);
		else return null;
	}


}
