package com.pixilic.javakat.mapdemo;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLMapReader {
	
	public void readMap(String mapName) {
	
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		try {
			db = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			db=null;
		}
		
		String fileName = "rsrc/map/"+mapName+"/map.xml";
        URL url = XMLMapReader.class.getResource(fileName);
        Document doc = null;
        try {
            doc = db.parse(new File(url.toURI())); //URL -> URI -> File, Java sucks
        } catch (IOException e) {
        	System.out.println("Couldnt load map from "+mapName);
        } catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        doc.normalize(); //make sure no one is shitty at writing xml
        Element xDocEl = doc.getDocumentElement(); 
        
        if ( xDocEl.getNodeName() == "map" ) {
        	NodeList xEnts = doc.getElementsByTagName("entity");
        	
        	//if we haven't died yet, we are ready to start loading things
        	//so init our loader
        	
        	int x = new Integer(xDocEl.getAttribute("width"));
        	int y = new Integer(xDocEl.getAttribute("height"));
        	MapLoader ml = new MapLoader(x, y);
        	        	
        	Node item = null;
        	
        	for ( int i=0; i < xEnts.getLength(); i++ ) {
        		
        		item = xEnts.item(i);
        	
        		//System.out.println(xEnts.item(0).getAttributes().getNamedItem("test").getNodeName());
        		ml.addEntity(
        				item.getAttributes().getNamedItem("type").getNodeValue(), 
        				new Integer(item.getAttributes().getNamedItem("tlX").getNodeValue()), 
        				new Integer(item.getAttributes().getNamedItem("tlY").getNodeValue()), 
						new Integer(item.getAttributes().getNamedItem("brX").getNodeValue()), 
						new Integer(item.getAttributes().getNamedItem("brY").getNodeValue()), 
        				new Integer(item.getAttributes().getNamedItem("z").getNodeValue()), 
        				new Boolean(item.getAttributes().getNamedItem("col").getNodeValue()), 
        				new Boolean(item.getAttributes().getNamedItem("npcCol").getNodeValue()), 
        				new Boolean(item.getAttributes().getNamedItem("mob").getNodeValue())
        				);
        		//well that was a disaster
        	}
        	
        } else {
        	//TODO throw an exception
        	System.out.println("too lazy to throw ex but stuff went down");
        }
		
		
	}
	
	
}