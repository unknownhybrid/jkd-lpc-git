import java.awt.image.BufferedImage;
import java.util.LinkedList;


public class AnimationPath {
	LinkedList<BufferedImage> path;
	BufferedImage currentFrame;
	
	public AnimationPath(){
		
	}
	public boolean add(BufferedImage sprite){
		return path.add(sprite);
	}
	public void next(){
		currentFrame = path.pop();
		path.push(currentFrame);
	}
}
