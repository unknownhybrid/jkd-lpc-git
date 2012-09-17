package com.pixilic.javakat.torvaldChildhoodSounds;

import java.awt.event.InputEvent;
import java.util.HashMap;

import com.pixilic.javakat.framework.GMan;
import com.pixilic.javakat.framework.RenderData;

public class ChildhoodSoundsMan extends GMan {

	private ChildhoodSoundsRenderData renderdata = null;
	
	private HashMap<PictureSongName, PictureSong> picsongs;
	private HashMap<WordPartName, WordPart> wordparts;
	private PictureSong ps; 
	private WordPart wp;
	//private Song song; this will be the current total song, if such a thing becomes necessary
	private 
	
	public ChildhoodSoundsMan(){
		//man
		//those childhood sounds, man
		//so childhood
		//so sound
		//man
		picsongs = new HashMap<PictureSongName, PictureSong>();
		wordparts = new HashMap<WordPartName, WordPart>();
	}
	@Override
	public void update(InputEvent evt) {
		
	}

	@Override
	public boolean switching() {
		return false;
	}

	@Override
	public RenderData getRenderData() {
		return renderdata;
	}

}
