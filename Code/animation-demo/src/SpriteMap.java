
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteMap {
	protected BufferedImage map;
	protected int width;
	protected int height;
	
	public SpriteMap (String path, int width, int height) {
		this.width = width;
		this.height = height;
		try{
			map = ImageIO.read(new File(path));
		} catch (IOException e) {
			System.err.println("Error reading sprite map from image.");
		}
	}
	public Animation createPaths() {
		Animation anim = new Animation();
		for(int y = 0; y < map.getHeight() / height; y++){
			AnimationPath row = new AnimationPath();
			for(int x = 0; x < map.getWidth() / width; x++){
				row.add(map.getSubimage(x*width, y*height, width, height));
			}
			switch(y){
			case 0:
				anim.paths.put(Animation.PathName.RUN_UP, row);
				break;
			case 1:
				anim.paths.put(Animation.PathName.RUN_RIGHT, row);
				break;
			case 2:
				anim.paths.put(Animation.PathName.RUN_DOWN, row);
				break;
			case 3:
				anim.paths.put(Animation.PathName.RUN_LEFT, row);
				break;
			}	
		}
		return anim;
	}
	
}