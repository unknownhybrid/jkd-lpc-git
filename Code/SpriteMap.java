<<<<<<< HEAD
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

package javakat.lpc;

public class SpriteMap{
	private BufferedImage map;
	private int width;
	private int height;
	
	public SpriteMap(string path, int width, int height){
		this.width = width;
		this.height = height;
		try {
			map = ImageIO.read(new File(path));
		} catch (IOException e) {
			System.err("Error reading sprite map from file.");
		}
	}
	/*public ArrayList<AnimationPath> generatePaths(){
		ArrayList<AnimationPath> paths = new ArrayList<AnimationPath>();
		
	}*/
}
=======
public class SpriteMap {
	protected BufferedImage map;
	protected int width;
	protected int height;
	
	public SpriteMap(string path, int width, int height) {
		try {
			map = ImageIO.Read(new File(path));
		} catch (IOException e) {
			System.err("Error reading sprite map from file (" + path + ").");
		}
		this.width = width;
		this.height = height;
	}
	
	public ArrayList<Sprite> createSprites(){
		ArrayList<Sprite> sprites = new ArrayList<Sprite>();
		for(int y = 0; y < map.height / height; y++){
			for(int x = 0; x < map.width / width; x++){
				sprites.Add(
								new Sprite(
													map.getSubimage(
																				x * width,
																				y * height,
																				width,
																				height
																				)	
												)
								);
			}
		}
		return sprites;
	}
	public ArrayList<AnimationPath> createPathsByRow(){
		ArrayList<AnimationPath> paths = new ArrayList<AnimationPath>();
		for(int y = 0; y < map.height / height; y++) {
			AnimationPath row = new AnimationPath();
			for(int x = 0; x < map.width / width; x++) {
				row.Add(
								new Sprite(
													map.getSubimage(
																				x * width,
																				y * height,
																				width,
																				height
																				)	
												)
								);
			}
			paths.Add(row);
		}
		return paths;
	}
	public static void main(String args[]) {
		System.out.println("It's working!");
		
	}
}
>>>>>>> 3ee681b4721f0fcd43f18337b9b1f33680707e06
