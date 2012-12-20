package com.pixilic.javakat.basicrpg;

import java.util.ArrayList;

public class AbilitySet {
	
	ArrayList<Ability> availableAbilities;
	
	public AbilitySet(AbilityClass ac){
		availableAbilities = new ArrayList<Ability>();
		for(Ability a : Ability.values()){
			if(a.getDisplayableType() == ac.getDisplayableType()) //add more conditions here, like level requirements
				availableAbilities.add(a);
		}
	}
	public ArrayList<Ability> getAvailableAbilities(){
		return availableAbilities;
	}
} interface AbilityInterface{ 
	public String getDisplayableText();
	public String getDisplayableType();
} enum AbilityClass implements AbilityInterface{
	
	WarriorAbilities("Ability"),
	MageAbilities("Spell"),
	RogueAbilities("Trick"),
	BattlemageAbilities("Mageblade"),
	ClericAbilities("Chant"),
	ArcherAbilities("Skill");
	
	private String displayText;
	
	private AbilityClass(String displayText){
		this.displayText = displayText;
	}
	
	public String getDisplayableText(){
		return displayText;
	}
	public String getDisplayableType(){
		return displayText;
	}
} enum Ability implements AbilityInterface{
	//Warrior abilities
	GASH_AND_SLASH("Gash and Slash", AbilityClass.WarriorAbilities), 
		DEVASTATOR("Devastator",AbilityClass.WarriorAbilities),
	//Mage abilities
	FIRE("Fire", AbilityClass.MageAbilities), 
		WATER("Water", AbilityClass.MageAbilities), 
		WIND("Wind", AbilityClass.MageAbilities), 
		EARTH("Earth", AbilityClass.MageAbilities), 
		HOME("Home", AbilityClass.MageAbilities),
	//Rogue abilities
	STEAL("Steal", AbilityClass.RogueAbilities), 
		BACKSTAB("Backstab", AbilityClass.RogueAbilities),
	//Battlemage abilities
	FIRESWORD("Fire Sword", AbilityClass.BattlemageAbilities), 
		WATERSWORD("Water Sword", AbilityClass.BattlemageAbilities), 
		WINDBLADE("Windblade", AbilityClass.BattlemageAbilities),
		EARTHBLADE("Earthblade", AbilityClass.BattlemageAbilities),
	//Cleric abilities
	HEAL("Heal", AbilityClass.ClericAbilities), 
		MULTIHEAL("Multiheal", AbilityClass.ClericAbilities), 
		CURSEBREAK("Cursebreak", AbilityClass.ClericAbilities),
	//Archer abilities
	DEADAIM("Deadaim", AbilityClass.ArcherAbilities), 
		HAWKEYE("Hawkeye", AbilityClass.ArcherAbilities);
		
	private String displayText;
	private AbilityClass ac;
	
	private Ability(String displayText, AbilityClass ac){
		this.displayText = displayText;
		this.ac = ac;
	}
	public String getDisplayableText(){
		return displayText;
	}
	public String getDisplayableType(){
		return ac.getDisplayableType();
	}
}
/*
enum ArcherAbilities{
	
}*/