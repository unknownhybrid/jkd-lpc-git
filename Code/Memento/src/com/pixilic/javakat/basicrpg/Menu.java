package com.pixilic.javakat.basicrpg;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Menu {
	
	private int width;
	private int height;
	
	int cursor = 0;
	
	private boolean isDisplayed = false;
	
	private ArrayList<MenuOption> options;
	
	public Menu(ArrayList<MenuOption> options, int width, int height){
		this.options = options;
		this.width = width;
		this.height = height;		
	}
	
	public BufferedImage getRender(){
		BufferedImage render = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g = render.createGraphics();
			/*g.setColor(Color.WHITE);
			g.drawRect(0, 0, menuimg.getWidth()-3, menuimg.getHeight()-3);
			g.drawRect(1, 1, menuimg.getWidth()-4, menuimg.getHeight()-4);
			g.drawString("MENU", 5, 25);
			g.drawString("Attack", 50, 40);
			g.drawString(player.abilityset.getAvailableAbilities().get(0).getDisplayableType(), 50, 55);
			g.setColor(Color.GRAY);
			g.drawString("Item", 50, 70);
			g.setColor(Color.WHITE);
			g.drawString("Run", 50, 85);*/ //this is what the original battle menu looks like
			//we need to figure out how many items we can fit in one
			//column based on the height of the visible menu. we'll
			//worry about scrolling/multiple pages later.
			g.setColor(Color.WHITE);
			g.drawRect(0, 0, render.getWidth()-3, render.getHeight()-3);
			g.drawRect(1, 1, render.getWidth()-4, render.getHeight()-4);
			g.drawString(options.get(0).moe.getDisplayableType().toUpperCase(), 5, 25);
			
			int colSize = height/15;
			int y = 0;
			int x = 0;
			
			//strange way of doing this, but I think it's a lot clearer
			//than the perhaps more natural double-for-loop iterated
			//by x and y.
			for(MenuOption option : options){ 
				g.drawString(option.getDisplayableText(), 50*(x+1), 15*(y+1)+25);
				y++;
				if(y >= colSize-1) {
					y = 0;
					x++;
				}
			}
		
		return render;
	}
	public int getWidth(){
		return width;
	}
	public int getHeight(){
		return height;
	}
	public void toggle(){
		isDisplayed = !isDisplayed;
		cursor = 0; //allow the user to change this to remember their
					//most recent selection at some point
	}
	public boolean getDisplayStatus(){
		return isDisplayed;
	}

	public int getCursorPosition() {
		return cursor;
	}

	public int getSize() {
		return options.size();
	}
	public MenuOption getOptionAt(int pos){
		return options.get(pos);
	}
}
