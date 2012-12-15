package com.pixilic.javakat.basicrpg;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import com.pixilic.javakat.framework.GMan;
import com.pixilic.javakat.framework.RenderData;

public class BasicRPGStartMan extends GMan {

	protected BasicRPGStartRenderData renderdata;
	
	private int cursor;
	private final int MAX_OPTION = 2;
	private final static int OPT_NEWGAME = 0;
	private final static int OPT_CONTINUE = 1;
	private final static int OPT_SETTINGS = 2;
	
	protected boolean isStarted = false;
	
	public BasicRPGStartMan(){
		cursor = 0;
		renderdata = new BasicRPGStartRenderData();
	}
	
	@Override
	public void update(InputEvent evt) {
		//if we've already begun the game, don't let them change anything
		if(isStarted) return;
		
		//if they hit a key
		//=========================
		//KEYPRESS
		//=========================
		if(evt instanceof KeyEvent){
			//and that key is up, we want to move up or cycle around depending on where the cursor is
			if( ((KeyEvent)evt).getKeyCode() == KeyEvent.VK_UP ){
				cursor = (cursor == 0) ? MAX_OPTION : cursor - 1;
				//TODO: REMOVE THIS TESTING LINE
				System.out.println("Cursor: " + cursor);
			}
			//and that key is down, we want to move down or cycle around depending on where the cursor is
			else if ( ((KeyEvent)evt).getKeyCode() == KeyEvent.VK_DOWN ){
				cursor = (cursor == MAX_OPTION) ? 0 : cursor + 1;
				//TODO: REMOVE THIS TESTING LINE
				System.out.println("Cursor: " + cursor);
			}
			//and that key is the GO key (here, enter), go into the selected menu
			else if ( ((KeyEvent)evt).getKeyCode() == KeyEvent.VK_ENTER ){
				switch(cursor){
				case OPT_NEWGAME:
					newGame();
					break;
				case OPT_CONTINUE:
					continueGame();
					break;
				case OPT_SETTINGS:
					settings();
					break;
				}
			}
		}
		//=========================
		//END KEYPRESS
		//=========================
		updateRenderData();
	}

	@Override
	public boolean switching(){
		return isStarted;
	}

	@Override
	public RenderData getRenderData(){
		return renderdata;
	}

	public void updateRenderData(){
		renderdata.update(cursor);
	}
	
	private void newGame(){
		isStarted = true;
	}
	private void continueGame(){
		//TODO: allow read-from-file
	}
	private void settings(){
		//TODO: allow settings manipulation (i.e. resolution)
	}

	@Override
	public GMan getSwapClass() {
		return new BasicRPGMan();
	}
}
