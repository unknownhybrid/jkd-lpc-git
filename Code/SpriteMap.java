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