package com.pixilic.javakat.toolkit;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import static com.pixilic.javakat.toolkit.PathEnum.*;

public class SpriteMap {
	protected BufferedImage image;
	protected int width;
	protected int height;
	
	public SpriteMap (URL url, int width, int height) {
		
        image = null;
        try {
            image = ImageIO.read(url);
        } catch (IOException e) {
        	System.out.println("Couldn't load sprite map from " + url.toString());
        }
        
		this.width	 = width;
		this.height = height;
	}
	public Animation createPaths() {
		Animation anim = new Animation();
		for(int y = 0; y < image.getHeight() / height; y++){
			AnimationPath row = new AnimationPath();
			for(int x = 0; x < image.getWidth() / width; x++){
				if(x == 0) row.setFirstFrame(image.getSubimage(x*width, y*height, width, height));
				row.add(image.getSubimage(x*width, y*height, width, height));
			}
			//FIXME
			switch(y){
			case 0:
				//I say, "fuck the 'hating abstraction'" thing
				//anim.paths.put(Animation.PathName.RUN_UP, row);
				row.pathname = PathName.RUN_UP;
				anim.addPath(PathName.RUN_UP, row);
				break;
			case 3:
				row.pathname = PathName.RUN_RIGHT;
				anim.addPath(PathName.RUN_RIGHT, row);
				break;
			case 2:
				row.pathname = PathName.RUN_DOWN;
				anim.addPath(PathName.RUN_DOWN, row);
				break;
			case 1:
				row.pathname = PathName.RUN_LEFT;
				anim.addPath(PathName.RUN_LEFT, row);
				break;
			}	
		}
		return anim;
	}
	
}