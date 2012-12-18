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
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLMapReader {
	
	MapLoader ml;
	
	public Map readMap(String mapName) {
	
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		try {
			db = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			db=null;
		}
		
		String fileName = "rsrc/maps/"+mapName+"/map.xml";
        //URL url = XMLMapReader.class.getResource(fileName); commented out to get it to work with the basicRPG
		URL url = com.pixilic.javakat.basicrpg.BasicRPG.class.getResource(fileName);
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
        	ml = new MapLoader(x, y);
        	        	
        	Node item = null;
        	NamedNodeMap attr = null;
        	
        	for ( int i=0; i < xEnts.getLength(); i++ ) {
        		
        		item = xEnts.item(i);
        		attr = item.getAttributes();
        	
        		//System.out.println(xEnts.item(0).getAttributes().getNamedItem("test").getNodeName());
        		ml.addEntity(
        				attr.getNamedItem("type").getNodeValue(), 
        				attr.getNamedItem("name").getNodeValue(),
        				new Integer(attr.getNamedItem("tlX").getNodeValue()), 
        				new Integer(attr.getNamedItem("tlY").getNodeValue()), 
						new Integer(attr.getNamedItem("brX").getNodeValue()), 
						new Integer(attr.getNamedItem("brY").getNodeValue()), 
        				new Integer(attr.getNamedItem("z").getNodeValue()), 
        				new Boolean(attr.getNamedItem("col").getNodeValue()), 
        				new Boolean(attr.getNamedItem("npcCol").getNodeValue()), 
        				new Boolean(attr.getNamedItem("mob").getNodeValue())
        				);
        		//well that was a disaster
        	}
        	
        } else {
        	//TODO throw an exception
        	System.out.println("too lazy to throw ex but stuff went down");
        }

        return ml.getMap();
		
	}
	
	
}
