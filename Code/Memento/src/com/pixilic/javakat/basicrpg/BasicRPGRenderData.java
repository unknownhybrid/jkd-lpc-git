package com.pixilic.javakat.basicrpg;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import com.pixilic.javakat.framework.RenderData;

public class BasicRPGRenderData extends RenderData {

	private BufferedImage maprender;
	private BufferedImage sprite;
	private float x, y;
	
	public BasicRPGRenderData(BufferedImage maprender, BufferedImage sprite, float x, float y){
		this.maprender = maprender;
		this.sprite = sprite;
		this.x = x;
		this.y = y;
	}
	@Override
	public void forceRender() {
		
	}

	@Override
	public Image render() {
		BufferedImage render = new BufferedImage(maprender.getWidth(), maprender.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = render.createGraphics();
			g.drawImage(maprender, 0, 0, null);
			g.drawImage(sprite, (int)x*32, (int)y*32, null);
		return render;
	}

	public void update(BufferedImage sprite, float x, float y, boolean isMoving){
		this.sprite = sprite;
		this.x = x;
		this.y = y;
	}
}
