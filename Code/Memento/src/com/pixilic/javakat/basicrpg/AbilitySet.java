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
} interface MenuOptionEnum {
	//NOTE: This may be a bad idea, but I've never done something like
	//this before so I want to try it out. This interface is going to
	//implicitly define the contents of each implementing type by 
	//enumerating (hurr hurr) retrieval (get) methods for each
	//piece of data I want the implementing type to contain.
	//============================================================
	//E.g. the Ability enum type has to be able to return 
	//an associated cost, its name, its displayable text,
	//and later, its effect.
	public String getDisplayableName(); //ex: "Devastator" or "Potion"
	public String getDisplayableText(); //ex: "Devastator		4" or "Potion	x3"
	public String getDisplayableType();
	public int getNum();
	public Effect getEffect();
} interface AbilityClassInterface {
	public String getDisplayableType();
} enum AbilityClass implements AbilityClassInterface{
	
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
} enum Ability implements MenuOptionEnum{
	//Warrior abilities
	GASH_AND_SLASH("Gash and Slash", 4, new Effect(1.2, Target.TARGETS_ENEMY, Element.NONE, StatusEffect.NONE, DependentStat.STR), AbilityClass.WarriorAbilities), 
		DEVASTATOR("Devastator", 6, new Effect(1.3, Target.TARGETS_ENEMY, Element.NONE, StatusEffect.NONE, DependentStat.STR), AbilityClass.WarriorAbilities),
	//Mage abilities
	FIRE("Fire", 4, new Effect(1.0, Target.TARGETS_ENEMY, Element.FIRE, StatusEffect.NONE, DependentStat.MAG), AbilityClass.MageAbilities), 
		WATER("Water", 4, new Effect(1.0, Target.TARGETS_ENEMY, Element.WATER, StatusEffect.NONE, DependentStat.MAG), AbilityClass.MageAbilities), 
		WIND("Wind", 4, new Effect(1.0, Target.TARGETS_ENEMY, Element.WIND, StatusEffect.NONE, DependentStat.MAG), AbilityClass.MageAbilities), 
		EARTH("Earth", 4, new Effect(1.0, Target.TARGETS_ENEMY, Element.EARTH, StatusEffect.NONE, DependentStat.MAG), AbilityClass.MageAbilities),
		DISPERSE("Disperse", 6, new Effect(0, Target.TARGETS_PLAYER, Element.NONE, StatusEffect.FLEE, DependentStat.MAG), AbilityClass.MageAbilities),
		//HOME("Home", 6, null, AbilityClass.MageAbilities), //needs a more complex Effect system
	//Rogue abilities
	//STEAL("Steal", 2, null, AbilityClass.RogueAbilities), //needs a more complex Effect system 
		BACKSTAB("Backstab", 6, new Effect(1.0, Target.TARGETS_ENEMY, Element.NONE, StatusEffect.NONE, DependentStat.SPD_LCK), AbilityClass.RogueAbilities),
	//Battlemage abilities
	FIRESWORD("Fire Sword", 4, new Effect(1.0, Target.TARGETS_ENEMY, Element.FIRE, StatusEffect.NONE, DependentStat.STR_MAG), AbilityClass.BattlemageAbilities), 
		WATERSWORD("Water Sword", 4, new Effect(1.0, Target.TARGETS_ENEMY, Element.WATER, StatusEffect.NONE, DependentStat.STR_MAG), AbilityClass.BattlemageAbilities), 
		WINDBLADE("Windblade", 4, new Effect(1.0, Target.TARGETS_ENEMY, Element.WIND, StatusEffect.NONE, DependentStat.STR_MAG), AbilityClass.BattlemageAbilities),
		EARTHBLADE("Earthblade", 4, new Effect(1.0, Target.TARGETS_ENEMY, Element.EARTH, StatusEffect.NONE, DependentStat.STR_MAG), AbilityClass.BattlemageAbilities),
	//Cleric abilities
	HEAL("Heal", 4, new Effect(-1.0, Target.TARGETS_PLAYER, Element.NONE, StatusEffect.HEAL, DependentStat.MAG), AbilityClass.ClericAbilities), 
		//MULTIHEAL("Multiheal", 8, null, AbilityClass.ClericAbilities), //what did I even want this to do 
		CURSEBREAK("Cursebreak", 3, new Effect(0, Target.TARGETS_PLAYER, Element.NONE, StatusEffect.PANACEA, DependentStat.MAG), AbilityClass.ClericAbilities),
	//Archer abilities
	DEADAIM("Deadaim", 4, new Effect(1.3, Target.TARGETS_ENEMY, Element.NONE, StatusEffect.NONE, DependentStat.STR_SPD), AbilityClass.ArcherAbilities), 
		HAWKEYE("Hawkeye", 5, new Effect(1.2, Target.TARGETS_ENEMY, Element.NONE, StatusEffect.NONE, DependentStat.SPD), AbilityClass.ArcherAbilities);
		
	private String displayText;
	private int cost;
	private Effect effect;
	private AbilityClass ac;
	
	private Ability(String displayText, int cost, Effect effect, AbilityClass ac){
		this.displayText = displayText;
		this.cost = cost;
		this.effect = effect;
		this.ac = ac;
	}
	public String getDisplayableText(){
		//negative numbers for costs signify no cost.
		//abilities will never generate mana for you unless their EFFECT
		//specifies that they do. their cost will never do it for you.
		//therefore, we want to make sure that we don't display any number
		//if the reported cost is negative.
		return (cost >= 0) ? displayText + "    " + cost : displayText;
	}
	public String getDisplayableType(){
		return ac.getDisplayableType();
	}
	@Override
	public String getDisplayableName() {
		return displayText;
	}
	@Override
	public int getNum() {
		return cost;
	}
	@Override
	public Effect getEffect() {
		return effect;
	}
}