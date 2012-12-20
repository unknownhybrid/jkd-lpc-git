package com.pixilic.javakat.basicrpg;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import com.pixilic.javakat.framework.GMan;
import com.pixilic.javakat.framework.RenderData;

public class BasicRPGBattleMan extends GMan {

	private BasicRPGBattleRenderData renderdata;
	
	private boolean isSwitching = false;
	private boolean isCombatComplete = false;
	private boolean isLevelingUp = false;
	
	private int cursor = 0;
	private final int MAX_OPTION = 3; //attack, ability, item, run (keep in mind it's 0-indexed)
	
	private Character player;
	private Character enemy;	
	
	public BasicRPGBattleMan(Character player){
		this.player = player;
		enemy = new Enemy();
		renderdata = new BasicRPGBattleRenderData(this.player, this.enemy);
	}
	public BasicRPGBattleMan(Character player, Enemy enemy){
		//in case we want to fight a specific enemy (e.g. a boss!)
		this.player = player;
		this.enemy = enemy;
		renderdata = new BasicRPGBattleRenderData(this.player, this.enemy);
	}
	@Override
	public RenderData getRenderData() {
		return renderdata;
	}

	@Override
	public GMan getSwapClass() {
		if(isSwitching && isCombatComplete && isLevelingUp) return new BasicRPGNewCharMan(player, player);
		if(isSwitching && isCombatComplete) return new BasicRPGMan(player);
		else return null;
	}

	@Override
	public boolean switching() {
		return isSwitching;
	}

	@Override
	public void update(InputEvent evt) {
		if(evt instanceof KeyEvent){
			switch(((KeyEvent)evt).getKeyCode()){
			case KeyEvent.VK_UP:
				cursor = (cursor - 1 >= 0) ? cursor - 1 : MAX_OPTION;
				break;
			case KeyEvent.VK_DOWN:
				cursor = (cursor + 1 <= MAX_OPTION) ? cursor + 1 : 0;
				break;
			case KeyEvent.VK_ENTER:
				act();
			default:
				break;
			}
		}
		updateRenderData();
	}

	private void act() {
		switch(cursor){
		case 0:
			//attack the enemy. the boolean returned signifies whether or not the enemy is dead
			if(player.attack(enemy)){
				player.xp++;
				isLevelingUp = player.levelup(); //only levels up if meets requirements for leveling up
				isCombatComplete = true;
				isSwitching = true;
			}
			break;
		case 1:
			//make a new menu -- abilities
			break;
		case 2:
			//make a new menu -- items
			break;
		case 3:
			if(player.runFrom(enemy)) {
				isCombatComplete = true;
				isSwitching = true;
			}
			break;
		}
	}
	@Override
	public void update() {
		
	}
	public void updateRenderData(){
		renderdata.update(player, enemy, cursor);
	}
}
