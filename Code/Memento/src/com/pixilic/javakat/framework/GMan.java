package com.pixilic.javakat.framework;

import java.awt.event.InputEvent;

public abstract class GMan {
	public abstract void update(InputEvent evt); //respond to input
	public abstract boolean switching(); //returns true if we need to switch to a new game
	public abstract RenderData getRenderData();
	public abstract GMan getSwapClass();
}
