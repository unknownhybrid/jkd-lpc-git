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
