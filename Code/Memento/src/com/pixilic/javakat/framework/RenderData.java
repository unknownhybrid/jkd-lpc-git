package com.pixilic.javakat.framework;

import java.awt.Image;

public abstract class RenderData {
	//should be a mediator between gfxen and gman
	public abstract void forceRender();//flags everything as needing to be re-rendered
	public abstract Image render();//actually submits the render information as an image
}
