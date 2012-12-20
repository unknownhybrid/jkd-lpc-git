package com.pixilic.javakat.basicrpg;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import com.pixilic.javakat.framework.GMan;
import com.pixilic.javakat.framework.RenderData;

public class BasicRPGNewCharMan extends GMan {

	Character player;
	Character playercopy;
	BasicRPGNewCharRenderData renderdata;
	int cursor;
	int rolekey;
	
	boolean isSwitching;
	boolean isSetRole = false;
	
	static final int WARR_KEY = 0;
	static final int MAGE_KEY = 1;
	static final int RGUE_KEY = 2;
	static final int BMGE_KEY = 3;
	static final int CLRC_KEY = 4;
	static final int ARCH_KEY = 5;
	
	static final int MAX_KEY = ARCH_KEY; //update this if you add more classes! it should be the largest key number
	
	static final int HP = 2;
	static final int MP = 3;
	static final int STR = 4;
	static final int DEF = 5;
	static final int MAG = 6;
	static final int MDF = 7;
	static final int SPD = 8;
	static final int LCK = 9;
	
	static final int MAX_OPTION = 10; //update this if you add more stats/options!
	
	public BasicRPGNewCharMan(){
		player = new Character(Role.WARRIOR);
		playercopy = new Character(Role.WARRIOR);
		rolekey = 0;
		renderdata = new BasicRPGNewCharRenderData(player);
		cursor = 0;
		isSwitching = false;
	}
	public BasicRPGNewCharMan(Character player, Character playercopy){
		this.player = player;
		this.playercopy = player;
		isSetRole = true;
		renderdata = new BasicRPGNewCharRenderData(player);
		
	}
	
	@Override
	public void update(InputEvent evt) {
		
		if(isSwitching) return;
		
		if(evt instanceof KeyEvent){
			if( ((KeyEvent)evt).getKeyCode() == KeyEvent.VK_UP ){
				cursor = (cursor == 0) ? MAX_OPTION : cursor - 1;
				if(cursor == MAX_OPTION && player.points != 0) cursor = MAX_OPTION-1;
			}
			//and that key is down, we want to move down or cycle around depending on where the cursor is
			else if ( ((KeyEvent)evt).getKeyCode() == KeyEvent.VK_DOWN ){
				cursor = (cursor == MAX_OPTION) ? 0 : cursor + 1;
				if(cursor == MAX_OPTION && player.points != 0) cursor = 0;
			}
			if(cursor == 0){
				if( ((KeyEvent)evt).getKeyCode() == KeyEvent.VK_ENTER ){
					
				}
			}
			if(cursor == 1 && !isSetRole){
				if( ((KeyEvent)evt).getKeyCode() == KeyEvent.VK_LEFT ){
					//Alright so this line is pretty dense. Here's the dissection of what we do:
					//1. If rolekey - 1 is less than 0, store MAX_KEY in rolekey. Otherwise, store rolekey - 1 in rolekey.
					//2. Evaluate getRoleFromKey() using the new value of rolekey.
					//And obviously for the VK_RIGHT case, we just do the same but opposite
					player = new Character(getRoleFromKey(rolekey = (--rolekey >= 0) ? rolekey : MAX_KEY), player.name);
					playercopy = new Character(getRoleFromKey(rolekey));
				} else if ( ((KeyEvent)evt).getKeyCode() == KeyEvent.VK_RIGHT ){
					player = new Character(getRoleFromKey(rolekey = (++rolekey <= MAX_KEY) ? rolekey : 0), player.name);
					playercopy = new Character(getRoleFromKey(rolekey));
				}
			}
			if(isStat()){
				if( ((KeyEvent)evt).getKeyCode() == KeyEvent.VK_LEFT ){
					System.out.println("HP: " + player.HP);
					System.out.println("HP (copy): " + playercopy.HP);
					System.out.println("Bonus: " + player.points); 
					switch(cursor){
					case HP:
						if(player.HP > playercopy.HP){
							player.HP--; 
							player.points++;
						}
						break;
					case MP:
						if(player.MP > playercopy.MP){ 
							player.MP--; 
							player.points++;
						}
						break;
					case STR:
						if(player.STR > playercopy.STR){ 
							player.STR--; 
							player.points++;
						}
						break;
					case DEF:
						if(player.DEF > playercopy.DEF){ 
							player.DEF--; 
							player.points++;
						}
						break;
					case SPD:
						if(player.SPD > playercopy.SPD){ 
							player.SPD--; 
							player.points++;
						}
						break;
					case MAG:
						if(player.MAG > playercopy.MAG){ 
							player.MAG--; 
							player.points++;
						}
						break;
					case MDF:
						if(player.MDF > playercopy.MDF){ 
							player.MDF--; 
							player.points++;
						}
						break;
					case LCK:
						if(player.LCK > playercopy.LCK){ 
							player.LCK--; 
							player.points++;
						}
						break;
					default:
						break;
					}
				}
				if( ((KeyEvent)evt).getKeyCode() == KeyEvent.VK_RIGHT ){
					switch(cursor){
					case HP:
						if(player.points > 0){ 
							player.HP++; 
							player.points--;
						}
						break;
					case MP:
						if(player.points > 0){ 
							player.MP++; 
							player.points--;
						}
						break;
					case STR:
						if(player.points > 0){ 
							player.STR++; 
							player.points--;
						}
						break;
					case DEF:
						if(player.points > 0){ 
							player.DEF++; 
							player.points--;
						}
						break;
					case SPD:
						if(player.points > 0){ 
							player.SPD++; 
							player.points--;
						}
						break;
					case MAG:
						if(player.points > 0){ 
							player.MAG++; 
							player.points--;
						}
						break;
					case MDF:
						if(player.points > 0){ 
							player.MDF++; 
							player.points--;
						}
						break;
					case LCK:
						if(player.points > 0){ 
							player.LCK++; 
							player.points--;
						}
						break;
					default:
						break;
					}
				}
			}
			if(cursor == 10 && player.points == 0){
				if( ((KeyEvent)evt).getKeyCode() == KeyEvent.VK_ENTER ){
					isSwitching = true;
				}
			} 
			updateRenderData();
		}
	}
	private boolean isStat(){
		return cursor >= 2 && cursor <= 9;
	}
	@Override
	public boolean switching() {
		return isSwitching;
	}

	@Override
	public RenderData getRenderData() {
		return renderdata;
	}
	public void updateRenderData(){
		renderdata.update(cursor, player);
	}
	@Override
	public GMan getSwapClass() {
		return new BasicRPGMan(player);
	}
	private Role getRoleFromKey(int key){
		if(key < 0) key = MAX_KEY;
		if(key > MAX_KEY) key = 0;
		switch(key){
		case WARR_KEY:
			return Role.WARRIOR;
		case MAGE_KEY:
			return Role.MAGE;
		case RGUE_KEY:
			return Role.ROGUE;
		case BMGE_KEY:
			return Role.BATTLEMAGE;
		case CLRC_KEY:
			return Role.CLERIC;
		case ARCH_KEY:
			return Role.ARCHER;
		default:
			System.err.println("ERROR: Invalid role key. Defaulting to warrior.");
			return Role.WARRIOR;
		}
	}


	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
}
