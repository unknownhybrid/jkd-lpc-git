package com.pixilic.javakat.mapdemo;
//render stuff
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
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
	DocumentBuilderFactory dbf;
	DocumentBuilder db;
	Document doc;
	
	BufferedImage tilemap;
	
	static final String TILE_MAP_PATH = "rsrc/tilemap.png";
			
	public MapRenderer(Map m){
		currentMap = m;
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
	
	public void loadTileMap(){
		try{
			tilemap = ImageIO.read(new File(TILE_MAP_PATH));
		} catch (IOException e) {
			e.printStackTrace();
			tilemap = null;
		}
	}
	
	public void init(){
		loadTileMap();
		loadXML();
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
        	 * a width
        	 * a height
        	 * the start location of the subimage in the .png (topleft corner)
        	 * 
        	 */    	
        	Node item = null;
        	
        	for ( int i=0; i < xEnts.getLength(); i++ ) {
        		
        		item = xEnts.item(i);
        		if(item.getAttributes().getNamedItem("name").getNodeValue().equals(entityName)){
        			String entityFileName = item.getAttributes().getNamedItem("filename").getNodeValue();
        			int originX = new Integer(item.getAttributes().getNamedItem("originX").getNodeValue());
        			int originY = new Integer(item.getAttributes().getNamedItem("originY").getNodeValue());
        			int width = new Integer(item.getAttributes().getNamedItem("width").getNodeValue());
        			int height = new Integer(item.getAttributes().getNamedItem("height").getNodeValue());
        			
        			BufferedImage entityImage = tilemap.getSubimage(originX, originY, width, height);
        			
        			imageCache.put(entityFileName, entityImage);
        			return entityImage;
        		}
        	}
        	
        } else {
        	//TODO throw an exception
        	System.out.println("we didn't find an entity with the given name (" + entityName + ") in our XML file");
        }
		return null;
		
		
	}
	
	public void renderMap(Graphics2D g){
		for(int y = 0; y < currentMap.height; y++){
			for(int x = 0; x < currentMap.width; x++){
				for(int z = 0; z < currentMap.depth; z++){
					if(currentMap.ents[x][y][z].isRendered) continue;
					g.drawImage(getImage(currentMap.ents[x][y][z].getName()), x*Map.TILE_WIDTH, y*Map.TILE_HEIGHT, this); //change something so I can just refer to the scaled positions
					currentMap.ents[x][y][z].isRendered = true;
				}
			}
		}
		
	}
}
