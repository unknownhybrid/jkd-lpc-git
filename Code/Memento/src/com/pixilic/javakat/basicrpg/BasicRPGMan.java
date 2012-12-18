package com.pixilic.javakat.basicrpg;

import java.awt.event.InputEvent;
import java.net.URL;

import com.pixilic.javakat.framework.GMan;
import com.pixilic.javakat.framework.RenderData;
import com.pixilic.javakat.toolkit.Map;

public class BasicRPGMan extends GMan {

	protected BasicRPGRenderData renderdata;
	private boolean isSwitching;
	private Character player;
    private Map map;
	
	public BasicRPGMan(){
		renderdata = new BasicRPGRenderData(null, null, 0, 0);
		isSwitching = true;
		player = null;
		map = null;
	}
	public BasicRPGMan(Character player){
		isSwitching = false;
		this.player = player;
		map = new Map(BasicRPG.class.getResource("rsrc/maps/map1/map.png"));
		renderdata = new BasicRPGRenderData(map.getRender(), player.sprite.getRender(), player.x, player.y);
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
