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
        an.setPath(PathName.RUN_LEFT);
	}

	public BufferedImage getRender() {
		return an.getCurrentPath().currentFrame;
	}
}
