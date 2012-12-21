package com.pixilic.javakat.basicrpg;

import java.util.ArrayList;

import com.pixilic.javakat.toolkit.Entity;
import com.pixilic.javakat.toolkit.Sprite;
import com.pixilic.javakat.toolkit.PathEnum.PathName;

public class Character extends com.pixilic.javakat.toolkit.Entity{
	String name;
	String role;
	
	int level = 1;
	int xp = 0;
	
	int HP;	//10 is default and class characteristics vary from there, e.g. warriors have 10 - 4 MDF and 10 + 4 STR
	int currentHP;
	int MP;
	int currentMP;
	int STR;
	int DEF;
	int SPD;
	int MAG;
	int MDF;
	int LCK;
	int points;
	
	AbilitySet abilityset;
	
	StatusEffect status = StatusEffect.NONE;
	
	boolean isMoving;
	boolean isInCombat = false;
	Direction currentDirection = Direction.RIGHT;
	
	int width = 32;
	int height = 32;
	Sprite sprite;
	
	public Character(){
		name = "Butts McGee";
		role = Role.CLERIC.name();
		setStatsByRole(Role.CLERIC);
		points = 10;
		abilityset = new AbilitySet(AbilityClass.ClericAbilities);
		sprite = new Sprite(Character.class.getResource("rsrc/images/female_walkcycle.png"));
		x = y = 0;
		isMoving = false;
	}
	public Character(Role r){
		name = "Felix";
		role = r.name();
		setStatsByRole(r);
		points = 10;
		abilityset = new AbilitySet(getAbilityClassFromRole(r));
		sprite = new Sprite(Character.class.getResource("rsrc/images/female_walkcycle.png"));
		isMoving = false;
	}
	
	public Character(Role r, String name){
		this.name = name;
		role = r.name();
		setStatsByRole(r);
		points = 10;
		abilityset = new AbilitySet(getAbilityClassFromRole(r));
		sprite = new Sprite(Character.class.getResource("rsrc/images/female_walkcycle.png"));
		isMoving = false;
	}
	
	private void setStatsByRole(Role r){
		switch(r){
		case WARRIOR:
			role = "Warrior";
			HP = currentHP = 20;
			MP = currentMP = 0;
			STR = 14;
			DEF = 8;
			SPD = 8;
			MAG = 0;
			MDF = 6;
			LCK = 24;
			break;
		case MAGE:
			role = "Mage";
			HP = currentHP = 8;
			MP = currentMP = 12;
			STR = 4;
			DEF = 6;
			SPD = 10;
			MAG = 20;
			MDF = 12;
			LCK = 8;
			break;
		case ROGUE:
			role = "Rogue";
			HP = currentHP = 10;
			MP = currentMP = 4;
			STR = 6;
			DEF = 8;
			SPD = 20;
			MAG = 4;
			MDF = 8;
			LCK = 20;
			break;
		case BATTLEMAGE:
			role = "Battlemage";
			HP = currentHP = 12;
			MP = currentMP = 8;
			STR = 12;
			DEF = 8;
			SPD = 10;
			MAG = 12;
			MDF = 8;
			LCK = 10;
			break;
		case CLERIC:
			role = "Cleric";
			HP = currentHP = 8;
			MP = currentMP = 8;
			STR = 8;
			DEF = 8;
			SPD = 14;
			MAG = 14;
			MDF = 10;
			LCK = 10;
			break;
		case ARCHER:
			role = "Archer";
			HP = currentHP = 10;
			MP = currentMP = 6;
			STR = 12;
			DEF = 4;
			SPD = 18;
			MAG = 4;
			MDF = 8;
			LCK = 18;
			break;
		default:
			System.err.println("ERROR: Class choice invalid");
			break;
		}
	}
	private AbilityClass getAbilityClassFromRole(Role r) {
		switch(r){
		case WARRIOR:
			return AbilityClass.WarriorAbilities;
		case MAGE:
			return AbilityClass.MageAbilities;
		case ROGUE:
			return AbilityClass.RogueAbilities;
		case BATTLEMAGE:
			return AbilityClass.BattlemageAbilities;
		case CLERIC:
			return AbilityClass.ClericAbilities;
		case ARCHER:
			return AbilityClass.ArcherAbilities;
		default:
			return null;	
		}
	}
	@Override
	public void move(Entity.Direction d) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void move(Entity.Direction d, boolean[][] physicalmap) {
		switch(d){
		case UP:
			//the subtraction may seem counter-intuitive at first, but
			//recall that the java graphics API defines the origin to be
			//the top-left corner; therefore smaller values of y are 
			//physically positioned above larger values of y
			if(y - 1 < 0 || physicalmap[x][y-1]){
				isMoving = false;
				return;
			}
			y--;
			if(sprite.getCurrentPathName() == PathName.RUN_UP) sprite.nextFrame();
			else sprite.setCurrentPath(getPathNameFromDirection(d));
			isMoving = true;
			break;
		case RIGHT:
			if(x + 1 >= physicalmap.length || physicalmap[x+1][y]){
				isMoving = false;
				return; //FIXME: This length may not be the value I want. I want the width of a single row of the array (i.e. the number of columns)
			}
			x++;
			if(sprite.getCurrentPathName() == PathName.RUN_RIGHT) sprite.nextFrame();
			else sprite.setCurrentPath(getPathNameFromDirection(d));
			isMoving = true;
			break;
		case DOWN:
			if(y + 1 >= physicalmap[x].length || physicalmap[x][y+1]){
				isMoving = false;
				return;
			}
			y++;
			if(sprite.getCurrentPathName() == PathName.RUN_DOWN) sprite.nextFrame();
			else sprite.setCurrentPath(getPathNameFromDirection(d));
			isMoving = true;
			break;
		case LEFT:
			if(x - 1 < 0 || physicalmap[x-1][y]){
				isMoving = false;
				return;
			}
			x--;
			if(sprite.getCurrentPathName() == PathName.RUN_LEFT) sprite.nextFrame();
			else sprite.setCurrentPath(getPathNameFromDirection(d));
			isMoving = true;
			break;
		}
	}
	private PathName getPathNameFromDirection(Direction d){
		switch(d){
		case UP:
			return PathName.RUN_UP;
		case RIGHT:
			return PathName.RUN_RIGHT;
		case DOWN:
			return PathName.RUN_DOWN;
		case LEFT:
			return PathName.RUN_LEFT;
		default:
			return null;
		}
	}
	public boolean checkForEncounter(boolean[][] encountermap, double encounterRate) {
		if(isMoving){
			isMoving = false;
			if(encountermap[x][y] && Math.random() < encounterRate) return true;
		}
		return false;
	}
	public boolean attack(Character enemy) {
		int effectiveSTR;
		effectiveSTR = (Math.random()*100 <= (LCK - enemy.LCK)) ? STR*3 : STR;
		enemy.currentHP = (effectiveSTR - enemy.DEF) > 0 ? enemy.currentHP - (effectiveSTR - enemy.DEF) : enemy.currentHP;
		enemy.currentHP = (enemy.currentHP < 0) ? 0 : enemy.currentHP;
		return enemy.currentHP <= 0;
	}
	public boolean use(Ability ability){
		
		Effect effect = ability.getEffect();
		if(effect.statuseffect == StatusEffect.FLEE) return true;
		if(effect.target == Target.TARGETS_ENEMY){
			System.err.println("How did this even HAPPEN?");
			System.err.println("Check the code for handling " + ability.getDisplayableName() + ", you apparently");
			System.err.println("used the targetless version of use when you clearly had a target.");
			return false;
		}
		//right now, I'm just handling abilities that target the player.
		//later, I should add handles for abilities that have no target
		//(global abilities).
		
		int damage;
		//make sure healing effects aren't reduced by high MDF
		damage = (effect.statuseffect == StatusEffect.HEAL) ? (int)(valueOf(effect.dependentstat)*effect.damageRatio) : (int)(valueOf(effect.dependentstat)*effect.damageRatio) - defenseFor(effect.dependentstat);
		currentHP = (currentHP - damage > 0) ? currentHP - damage : 0;
		
		status = effect.statuseffect;
		
		return currentHP <= 0;
	}
	private int defenseFor(DependentStat dependentstat) {
		switch(dependentstat){
		case STR:
			return DEF;
		case DEF:
			return DEF;
		case SPD:
			return DEF;
		case MAG:
			return MDF;
		case MDF:
			return MDF;
		case LCK:
			return DEF;
		case STR_MAG:
			return (DEF + MDF)/2;
		case STR_SPD:
			return DEF;
		case SPD_LCK:
			return DEF;
		case NONE:
		default:
			return 0;
		}
	}
	private int valueOf(DependentStat dependentstat) {
		switch(dependentstat){
		case STR:
			return STR;
		case DEF:
			return DEF;
		case SPD:
			return SPD;
		case MAG:
			return MAG;
		case MDF:
			return MDF;
		case LCK:
			return LCK;
		case STR_MAG:
			return (STR + MAG)/2;
		case STR_SPD:
			return (STR + SPD)/2;
		case SPD_LCK:
			return (SPD + LCK)/2;
		case NONE:
		default:
			return 0;
		}
	}
	public boolean use(Ability ability, Character enemy){
		
		Effect effect = ability.getEffect();
		
		if(effect.target != Target.TARGETS_ENEMY){
			System.err.println("How did this even HAPPEN?");
			System.err.println("Check the code for handling " + ability.getDisplayableName() + ", you apparently");
			System.err.println("used the targetless version of use when you clearly had a target.");
			return false;
		}
		
		int damage;
		//make sure healing effects aren't reduced by high MDF
		damage = (effect.statuseffect == StatusEffect.HEAL) ? (int)(valueOf(effect.dependentstat)*effect.damageRatio) : (int)(valueOf(effect.dependentstat)*effect.damageRatio) - enemy.defenseFor(effect.dependentstat);
		enemy.currentHP = (enemy.currentHP - damage > 0) ? enemy.currentHP - damage : 0;
		
		enemy.status = effect.statuseffect;
		return  enemy.currentHP <= 0;
	}
	public boolean runFrom(Character enemy) {
		int effectiveSPD;
		effectiveSPD = (Math.random()*100 <= (LCK - enemy.LCK)) ? SPD*3 : SPD;
		//just some shitty lenient escape equation.
		return (effectiveSPD - enemy.SPD + (Math.random()*(100 - (effectiveSPD - enemy.SPD))) > Math.random()*100);
	}
	public boolean levelup() {
		if (xp >= level*10){
			xp = xp - level*10;
			points += 5*level;
			level++;
			return true;
		}
		return false;
	}
	public ArrayList<MenuOption> getAbilitiesAsMenuOptions(){
		ArrayList<MenuOption> menuOptions = new ArrayList<MenuOption>();
		for(Ability a : abilityset.availableAbilities){
			menuOptions.add(new MenuOption(a));
		}
		return menuOptions;
	}
}

enum Role {
	WARRIOR, MAGE, ROGUE, BATTLEMAGE, CLERIC, ARCHER 
}
