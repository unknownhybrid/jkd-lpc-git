package com.pixilic.javakat.basicrpg;

public class Effect {
	double damageRatio; //can be 0 (in the case of utility abilities)
						//or even negative (in the case of healing abilities)
	
	Target target;
	Element element;
	StatusEffect statuseffect;
	DependentStat dependentstat;
	
	public Effect(double damageRatio, Target target, Element element, StatusEffect statuseffect, DependentStat dependentstat){
		this.damageRatio = damageRatio;
		this.target = target;
		this.element = element;
		this.statuseffect = statuseffect;
		this.dependentstat = dependentstat;
	}
	
} enum Element {
	NONE, 
	FIRE, WATER, EARTH, WIND;
} enum StatusEffect {
	NONE, 
	POISON, PARALYSIS, SLEEP, 
	HEAL, CURE_POISON, CURE_PARALYSIS, CURE_SLEEP, PANACEA,
	FLEE;
} enum Target {
	NONE, 
	TARGETS_PLAYER, TARGETS_ENEMY;
} enum DependentStat {
	NONE,
	HP, MP, STR, DEF, SPD, MAG, MDF, LCK,
	STR_MAG, STR_SPD, SPD_LCK;
}
