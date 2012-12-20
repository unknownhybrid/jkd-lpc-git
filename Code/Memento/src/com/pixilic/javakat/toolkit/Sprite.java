package com.pixilic.javakat.toolkit;

import java.awt.image.BufferedImage;
import java.net.URL;

import com.pixilic.javakat.toolkit.PathEnum.PathName;

public class Sprite {
	SpriteMap sm;
	Animation an;
	
	public Sprite(URL url){
		//FIXME: this is a shitty place to load images
        sm = new SpriteMap(url, 64, 64);
        an = sm.createPaths();
        //FIXME: change the path here
        an.setPath(PathName.RUN_RIGHT);
	}

	public BufferedImage getRender() {
		return an.getCurrentPath().getCurrentFrame();
	}
	public PathName getCurrentPathName(){
		return an.getCurrentPathName();
	}
	public void setCurrentPath(PathName pn){
		an.setPath(pn);
	}
	public void nextFrame(){
		an.next();
	}
	public BufferedImage getPlayerBattleRender(){
		return an.getPlayerBattlePath().getFirstFrame();
	}
	public BufferedImage getEnemyBattleRender(){
		return an.getEnemyBattlePath().getFirstFrame();
	}
}
