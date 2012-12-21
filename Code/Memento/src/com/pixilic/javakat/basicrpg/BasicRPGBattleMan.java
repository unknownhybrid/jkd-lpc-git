package com.pixilic.javakat.basicrpg;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import com.pixilic.javakat.framework.GMan;
import com.pixilic.javakat.framework.RenderData;

public class BasicRPGBattleMan extends GMan {

	private BasicRPGBattleRenderData renderdata;
	
	private Menu abilityMenu;
	private Menu itemMenu;
	
	private boolean isSwitching = false;
	private boolean isCombatComplete = false;
	private boolean isLevelingUp = false;
	private boolean isGameOver = false;
	
	private int cursor = 0;
	private final int MAX_OPTION = 3; //attack, ability, item, run (keep in mind it's 0-indexed)
	
	private Character player;
	private Character enemy;	
	
	public BasicRPGBattleMan(Character player){
		this.player = player;
		enemy = new Enemy();
		abilityMenu = new Menu(player.getAbilitiesAsMenuOptions(), 200, 150);
		renderdata = new BasicRPGBattleRenderData(this.player, this.enemy, abilityMenu, itemMenu);
	}
	public BasicRPGBattleMan(Character player, Enemy enemy){
		//in case we want to fight a specific enemy (e.g. a boss!)
		this.player = player;
		this.enemy = enemy;
		abilityMenu = new Menu(player.getAbilitiesAsMenuOptions(), 200, 150);
		renderdata = new BasicRPGBattleRenderData(this.player, this.enemy, abilityMenu, itemMenu);
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
			//TODO: REMOVE THESE TESTING LINES
			System.out.println("evt info: " + evt.toString());
			if(abilityMenu.getDisplayStatus()){
				System.out.println("Clearly abilityMenu is visible...");
				handleMenuInput((KeyEvent)evt);
				System.out.println("Should be invisible now.");
				System.out.println("Checking visibility...");
				System.out.println("   Logical visibility: " + abilityMenu.getDisplayStatus()); 
				updateRenderData();
				return;
			}
			/*if(itemMenu.getDisplayStatus()){
				handleMenuInput();
				return;
			}*/
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
			attack();
			break;
		case 1:
			//make a new menu -- abilities
			abilityMenu.toggle();
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
	public void handleMenuInput(KeyEvent evt){
		switch(evt.getKeyCode()){
		case KeyEvent.VK_BACK_SPACE:
		case KeyEvent.VK_ESCAPE:
			if(abilityMenu.getDisplayStatus()) abilityMenu.toggle();
			//if(itemMenu.getDisplayStatus()) itemMenu.toggle();
			return;
		case KeyEvent.VK_UP:
			if(abilityMenu.getDisplayStatus()) abilityMenu.cursor = (abilityMenu.cursor - 1 < 0) ? abilityMenu.getSize() - 1 : abilityMenu.cursor - 1;
			//itemMenu duplicate
			return;
		case KeyEvent.VK_DOWN:
			if(abilityMenu.getDisplayStatus()) abilityMenu.cursor = (abilityMenu.cursor + 1 >= abilityMenu.getSize()) ? 0 : abilityMenu.cursor + 1;
			return;
		case KeyEvent.VK_ENTER:
			if(abilityMenu.getDisplayStatus()){
				MenuOptionEnum moe = abilityMenu.getOptionAt(0).moe;
				int mpCheck = (player.currentMP - moe.getNum() >= 0) ? player.currentMP - moe.getNum() : player.currentMP;
				//get num on dem moes if you know what I mean
				
				//mpCheck will only differ from player.currentHP
				//if we had enough mp to use the ability
				if(mpCheck != player.currentMP) {
					player.currentMP = mpCheck;
					useAbility((Ability)moe); //TODO: MAKE THIS A GENERAL TEMPLATE FOR ALL MOEs SO WE CAN USE ITEMS IN THE SAME WAY
				}
			}
			return;
		default:
			return;
		}
	}
	public void updateRenderData(){
		renderdata.update(player, enemy, cursor, abilityMenu);
	}
	private void attack(){
		if(player.attack(enemy)){
			player.xp++;
			isLevelingUp = player.levelup(); //only levels up if meets requirements for leveling up
			isCombatComplete = true;
			isSwitching = true;
		}
	}
	private void useAbility(Ability ability) {
		if(ability.getEffect().target == Target.TARGETS_ENEMY){
			if(player.use(ability, enemy)){
				player.xp++;
				isLevelingUp = player.levelup();
				isCombatComplete = true;
				isSwitching = true;
			}
		}
		else {
			if(player.use(ability)){
				if(ability.getEffect().statuseffect == StatusEffect.FLEE) {					
					isCombatComplete = true;
					isSwitching = true;
				}
				else {
					isGameOver = true;
					isCombatComplete = true;
					isSwitching = true;
				}
			}
		}
	}
}
