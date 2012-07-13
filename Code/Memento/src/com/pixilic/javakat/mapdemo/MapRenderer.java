package com.pixilic.javakat.mapdemo;
//render stuff
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.HashMap;
//XML stuff
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class MapRenderer {

	Map currentMap;
	HashMap<String,BufferedImage> imageCache;
	HashMap<String,Area> areaCache;
	DocumentBuilderFactory dbf;
	DocumentBuilder db;
	Document doc;
	Image i;
			
	public MapRenderer(Map m){
		currentMap = m;
		i = new BufferedImage(m.width*Map.TILE_WIDTH, m.height*Map.TILE_HEIGHT, BufferedImage.TYPE_INT_ARGB);
		init(); //initializes tilemap and XML
	}
	
	public void loadXML(){
		//XML initialization
		dbf = DocumentBuilderFactory.newInstance();
		try {
			db = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			db=null;
		}
		String fileName = "rsrc/entities.xml";
        URL url = XMLMapReader.class.getResource(fileName);
        doc = null;
        try {
            doc = db.parse(new File(url.toURI())); //URL -> URI -> File, Java sucks
        } catch (IOException e) {
        	System.out.println("Couldnt load from entities.xml");
        } catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        doc.normalize(); //make sure no one is shitty at writing xml
	}
	
	public void init(){
		loadXML();
		imageCache = new HashMap<String,BufferedImage>();
		areaCache = new HashMap<String,Area>();
	}
	
	
	//XML STUFF
	//checks to see if we've already loaded the given entity into an image
	//if so:
	//-returns that image
	//if not:
	//-reads XML that defines image locations in a large .png
	//-finds the proper subimage
	//-makes an image out of that and stores it in the cache
	public BufferedImage getImage(String entityName) {
		
		if(imageCache.containsKey(entityName)){
			return imageCache.get(entityName);
		}
		
		
        Element xDocEl = doc.getDocumentElement(); 
        
        if ( xDocEl.getNodeName() == "entityDictionary" ) {
        	NodeList xEnts = doc.getElementsByTagName("entity");
        	
        	//if we haven't died yet, we are ready to start loading things
        	//so init our loader
        	
        	/*
        	 * We'll want the following information associated with the visual form of an entity:
        	 * ----------------------------------------------------------------------------------
        	 * the image we'll dissect
        	 * all the relevant subsections (areas) within the image
        	 */    	
        	Node item = null;
        	
        	for ( int i=0; i < xEnts.getLength(); i++ ) {
        		
        		item = xEnts.item(i);
        		if(item.getAttributes().getNamedItem("name").getNodeValue().equals(entityName)){
        			String entityFileName = "rsrc/images/" + item.getAttributes().getNamedItem("name").getNodeValue() + ".png";
        			URL url = XMLMapReader.class.getResource(entityFileName);
        			BufferedImage entityImage = null;
        			try {
        				entityImage = ImageIO.read(new File(url.toURI()));
            			imageCache.put(entityName, entityImage);
        			} catch (IOException e){
        				e.printStackTrace();
        			} catch (URISyntaxException e){
        				e.printStackTrace();
        			}
        			NodeList areas = item.getChildNodes();
        			Node area = null;
        			for(int j = 0; j < areas.getLength(); j++){
        				area = areas.item(j);
        				if(area.getAttributes() == null || area.getAttributes().getNamedItem("id").getNodeValue() == null) continue;
        				String idNodeValue = area.getAttributes().getNamedItem("id").getNodeValue();
        				AreaID id = AreaID.valueOf(idNodeValue.toUpperCase());
        				int originX = new Integer(area.getAttributes().getNamedItem("originX").getNodeValue());
        				int originY = new Integer(area.getAttributes().getNamedItem("originY").getNodeValue());
        				int width = new Integer(area.getAttributes().getNamedItem("width").getNodeValue());
        				int height = new Integer(area.getAttributes().getNamedItem("height").getNodeValue());
        				areaCache.put(entityName+id.toString(), new Area(id, originX, originY, width, height));
        			}
        			return entityImage;
        		}
        	}
        	
        } else {
        	//TODO throw an exception
        	System.out.println("we didn't find an entity with the given name (" + entityName + ") in our XML file or our image cache");
        }
		return null;
		
		
	}
	public BufferedImage entityRender(Entity entity){
		BufferedImage src = getImage(entity.getName());
		BufferedImage render = new BufferedImage(entity.width*Map.TILE_WIDTH, entity.height*Map.TILE_HEIGHT, BufferedImage.TYPE_INT_ARGB);
		Graphics g = render.getGraphics();
		
		Area topleft = areaCache.get(entity.name+AreaID.TOP_LEFT.toString());
		Area topcenter = areaCache.get(entity.name+AreaID.TOP_CENTER.toString());
		Area topright = areaCache.get(entity.name+AreaID.TOP_RIGHT.toString());
		Area left = areaCache.get(entity.name+AreaID.LEFT.toString());
		Area center = areaCache.get(entity.name+AreaID.CENTER.toString());
		Area right = areaCache.get(entity.name+AreaID.RIGHT.toString());
		Area bottomleft = areaCache.get(entity.name+AreaID.BOTTOM_LEFT.toString());
		Area bottomcenter = areaCache.get(entity.name+AreaID.BOTTOM_CENTER.toString());
		Area bottomright = areaCache.get(entity.name+AreaID.BOTTOM_RIGHT.toString());
		
		for(int y = 0; y < entity.height; y++){
			for(int x = 0; x < entity.width; x++){
				if(isTop(y) && isLeft(x)){
					g.drawImage(src.getSubimage(topleft.originX, topleft.originY, topleft.width, topleft.height), x*Map.TILE_WIDTH, y*Map.TILE_HEIGHT, null);
				} else if(isTop(y) && isXCenter(x, entity, topleft, topright)){
					g.drawImage(src.getSubimage(topcenter.originX, topcenter.originY, topcenter.width, topcenter.height), x*Map.TILE_WIDTH, y*Map.TILE_HEIGHT, null);
				} else if (isTop(y) && isRight(x, entity, topleft, topright)){
					g.drawImage(src.getSubimage(topright.originX, topright.originY, topright.width, topright.height), x*Map.TILE_WIDTH, y*Map.TILE_HEIGHT, null);
				} else if (isYCenter(y, entity, topleft, bottomleft) && isLeft(x)){
					g.drawImage(src.getSubimage(left.originX, left.originY, left.width, left.height), x*Map.TILE_WIDTH, y*Map.TILE_HEIGHT, null);
				} else if (isYCenter(y, entity, topcenter, bottomcenter) && isXCenter(x, entity, left, right)){
					g.drawImage(src.getSubimage(center.originX, center.originY, center.width, center.height), x*Map.TILE_WIDTH, y*Map.TILE_HEIGHT, null);
				} else if (isYCenter(y, entity, topright, bottomright) && isRight(x, entity, left, right)){
					g.drawImage(src.getSubimage(right.originX, right.originY, right.width, right.height), x*Map.TILE_WIDTH, y*Map.TILE_HEIGHT, null);
				} else if (isBottom(y, entity, topleft, bottomleft) && isLeft(x)){
					g.drawImage(src.getSubimage(bottomleft.originX, bottomleft.originY, bottomleft.width, bottomleft.height), x*Map.TILE_WIDTH, y*Map.TILE_HEIGHT, null);
				} else if (isBottom(y, entity, topcenter, bottomcenter) && isXCenter(x, entity, bottomleft, bottomright)){
					g.drawImage(src.getSubimage(bottomcenter.originX, bottomcenter.originY, bottomcenter.width, bottomcenter.height), x*Map.TILE_WIDTH, y*Map.TILE_HEIGHT, null);
				} else if (isBottom(y, entity, topright, bottomright) && isRight(x, entity, bottomleft, bottomright)){
					g.drawImage(src.getSubimage(bottomright.originX, bottomright.originY, bottomright.width, bottomright.height), x*Map.TILE_WIDTH, y*Map.TILE_HEIGHT, null);
				}
			}
		}
		return render;
	}
	public boolean isTop(int currentPos){
		return currentPos == 0;
	}
	public boolean isYCenter(int currentPos, Entity e, Area top, Area bottom){
		if(e.name.equals("trunk")) System.out.println("isYCenter");
		return currentPos > 0 && currentPos < e.height - (bottom.height/32);
	}
	public boolean isBottom(int currentPos, Entity e, Area top, Area bottom){
		if(e.name.equals("trunk")) System.out.println("isYCenter");
		return currentPos > 0 && currentPos == e.height - (bottom.height/32);
	}
	public boolean isLeft(int currentPos){
		return currentPos == 0; //YES, I know isTop and isLeft are functionally identical. but this way is way more intuitive to use than having a function named isTopOrLeft
	}
	public boolean isXCenter(int currentPos, Entity e, Area left, Area right){
		return currentPos > 0 && (currentPos < e.width - (right.width/32));
	}
	public boolean isRight(int currentPos, Entity e, Area left, Area right){
		return currentPos > 0 && (currentPos == e.width - (right.width/32));
	}
	
	/*
	 * shitty ass shit ass
	public void renderMap(Graphics2D g){
		for(int y = 0; y < currentMap.height; y++){
			for(int x = 0; x < currentMap.width; x++){
				for(int z = 0; z < currentMap.depth; z++){
					if(currentMap.ents[x][y][z] == null || currentMap.ents[x][y][z].isRendered) continue;
					g.drawImage(entityRender(currentMap.ents[x][y][z]), x*Map.TILE_WIDTH, y*Map.TILE_HEIGHT, null); //change something so I can just refer to the scaled positions
					currentMap.ents[x][y][z].isRendered = true;
				}
			}
		}
	}
	*/
	
	public Image renderMap() {
		Graphics2D g = (Graphics2D) i.getGraphics();
		for(int y = 0; y < currentMap.height; y++){
			for(int x = 0; x < currentMap.width; x++){
				for(int z = 0; z < currentMap.depth; z++){
					if(currentMap.ents[x][y][z] == null || currentMap.ents[x][y][z].isRendered) continue; //we already got a character render thing!
					if(currentMap.ents[x][y][z].getClass() == Character.class){
						Character currentChar = (Character)currentMap.ents[x][y][z];
						g.drawImage(currentChar.getAnimation().getCurrentPath().getCurrentFrame(), x*Map.TILE_WIDTH, y*Map.TILE_HEIGHT, null);
						currentChar.getAnimation().getCurrentPath().next();
					} else if (currentMap.ents[x][y][z].getClass() == Tile.class){
						g.drawImage(entityRender(currentMap.ents[x][y][z]), x*Map.TILE_WIDTH, y*Map.TILE_HEIGHT, null); //change something so I can just refer to the scaled positions
					}
					currentMap.ents[x][y][z].isRendered = true;
				}
			}
		}
		return(i);
	}
	
	//TODO is this actually necessary right now?
	public void forceRender() {
		for(int y = 0; y < currentMap.height; y++){
			for(int x = 0; x < currentMap.width; x++){
				for(int z = 0; z < currentMap.depth; z++){
					if(currentMap.ents[x][y][z] == null ) continue;
					currentMap.ents[x][y][z].isRendered = false;
				}
			}
		}
	}
}
