package com.pixilic.javakat.animationdemo;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import static com.pixilic.javakat.animationdemo.PathEnum.*;

public class SpriteMap {
	protected BufferedImage image;
	protected int width;
	protected int height;
	
	public SpriteMap (String name, int width, int height) {
		
		String imgFileName = "rsrc/"+name+".png";
        URL url = Origin.class.getResource(imgFileName);
        image = null;
        try {
            image = ImageIO.read(url);
        } catch (IOException e) {
        	System.out.println("Couldnt load sprite map from "+name);
        }
        
		this.width	 = width;
		this.height = height;
	}
	public Animation createPaths() {
		Animation anim = new Animation();
		for(int y = 0; y < image.getHeight() / height; y++){
			AnimationPath row = new AnimationPath();
			for(int x = 0; x < image.getWidth() / width; x++){
				row.add(image.getSubimage(x*width, y*height, width, height));
			}
			//FIXME
			switch(y){
			case 0:
				//I say, "fuck the 'hating abstraction'" thing
				//anim.paths.put(Animation.PathName.RUN_UP, row);
				anim.addPath(PathName.RUN_UP, row);
				break;
			case 1:
				anim.addPath(PathName.RUN_RIGHT, row);
				break;
			case 2:
				anim.addPath(PathName.RUN_DOWN, row);
				break;
			case 3:
				anim.addPath(PathName.RUN_LEFT, row);
				break;
			}	
		}
		return anim;
	}
	
}