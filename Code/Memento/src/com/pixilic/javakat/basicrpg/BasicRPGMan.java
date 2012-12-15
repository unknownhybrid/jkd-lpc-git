package com.pixilic.javakat.basicrpg;

import java.awt.event.InputEvent;

import com.pixilic.javakat.framework.GMan;
import com.pixilic.javakat.framework.RenderData;

public class BasicRPGMan extends GMan {

	protected BasicRPGRenderData renderdata;
	@Override
	public void update(InputEvent evt) {
		
	}

	@Override
	public boolean switching() {
		
		return false;
	}

	@Override
	public RenderData getRenderData() {
		
		return null;
	}

	@Override
	public GMan getSwapClass() {
		// TODO Auto-generated method stub
		return null;
	}

}
