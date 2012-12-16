package com.pixilic.javakat.basicrpg;

public class Character {
	String name;
	String role;
	int HP;	//10 is default and class characteristics vary from there, e.g. warriors have 10 - 4 MDF and 10 + 4 STR
	int MP;
	int STR;
	int DEF;
	int SPD;
	int MAG;
	int MDF;
	int LCK;
	int points;
	
	public Character(){
		name = "Butts McGee";
		role = "Test";
		HP = MP = STR = DEF = SPD = MAG = MDF = LCK = 0;
		points = 10;
	}
	public Character(Role r){
		name = "Felix";
		setStatsByRole(r);
		points = 10;
	}
	public Character(Role r, String name){
		this.name = name;
		setStatsByRole(r);
		points = 10;
	}
	
	public void setStatsByRole(Role r){
		switch(r){
		case WARRIOR:
			role = "Warrior";
			HP = 20;
			MP = 0;
			STR = 14;
			DEF = 8;
			SPD = 8;
			MAG = 0;
			MDF = 6;
			LCK = 24;
			break;
		case MAGE:
			role = "Mage";
			HP = 8;
			MP = 12;
			STR = 4;
			DEF = 6;
			SPD = 10;
			MAG = 20;
			MDF = 12;
			LCK = 8;
			break;
		case ROGUE:
			role = "Rogue";
			HP = 10;
			MP = 4;
			STR = 6;
			DEF = 8;
			SPD = 20;
			MAG = 4;
			MDF = 8;
			LCK = 20;
			break;
		case BATTLEMAGE:
			role = "Battlemage";
			HP = 12;
			MP = 8;
			STR = 12;
			DEF = 8;
			SPD = 10;
			MAG = 12;
			MDF = 8;
			LCK = 10;
			break;
		case CLERIC:
			role = "Cleric";
			HP = 8;
			MP = 8;
			STR = 8;
			DEF = 8;
			SPD = 14;
			MAG = 14;
			MDF = 10;
			LCK = 10;
			break;
		case ARCHER:
			role = "Archer";
			HP = 10;
			MP = 6;
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
}

enum Role {
	WARRIOR, MAGE, ROGUE, BATTLEMAGE, CLERIC, ARCHER 
}
