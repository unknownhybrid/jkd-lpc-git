package com.pixilic.javakat.basicrpg;

import java.awt.event.InputEvent;

import com.pixilic.javakat.framework.GMan;
import com.pixilic.javakat.framework.RenderData;

public class BasicRPGMan extends GMan {

	protected BasicRPGRenderData renderdata;
	private boolean isSwitching;
	private Character player;
	
	public BasicRPGMan(){
		renderdata = new BasicRPGRenderData();
		isSwitching = true;
		player = null;
	}
	public BasicRPGMan(Character player){
		renderdata = new BasicRPGRenderData();
		isSwitching = false;
		this.player = player;
	}
	
	@Override
	public void update(InputEvent evt) {
		
	}

	@Override
	public boolean switching() {
		return isSwitching;
	}

	@Override
	public RenderData getRenderData() {
		return renderdata;
	}

	@Override
	public GMan getSwapClass() {
		// TODO Auto-generated method stub
		if(player == null) return new BasicRPGNewCharMan();
		else return null;
	}


}
