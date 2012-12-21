package com.pixilic.javakat.basicrpg;

public class MenuOption {
	//this can be a cost or a count; cost for abilities, count for items
	MenuOptionEnum moe; //moe moe desuuu~ kawaii sugoi~~
	
	public MenuOption(MenuOptionEnum moe){
		this.moe = /*best*/ moe;
	}
	
	public String getDisplayableText(){
		return moe.getDisplayableText();
	}
}
