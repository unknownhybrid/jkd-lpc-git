package com.pixilic.javakat.torvaldChildhoodSounds;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.pixilic.javakat.framework.GMan;
import com.pixilic.javakat.framework.RenderData;

//author: m.javakat
//purpose: manage the Childhood Sounds minigame (Torvald)
//remaining tasks:
//implementation questions: should the game's view change based solely on the cursor_target? (I think it should)

public class ChildhoodSoundsMan extends GMan {

	private ChildhoodSoundsRenderData renderdata;
	
	private ArrayList<PictureSong> picsongs;
	private int pos_picsongs;
	private ArrayList<WordPart> wordparts;
	private int pos_wordparts;
	private PictureSong ps; //currently chosen picture-song
	private ArrayList<WordPart> wps; //currently chosen word-parts
	//private Song song; this will be the current total song, if such a thing becomes necessary
	private SoundChunk cursor_target;
	
	//health stuff
	private int health;
	private long starttime; //starting time of the game
	private long endtime; //ending time of the game; //endtime - starttime = amount of time we've been in game!
	static private int looptime = 60; //number of times it takes for health to be decremented
	private int looptimer; //amount of loops spent so far 
	private int damage; //amount of health to decrement
	private int gametimer; //amount of decrements we've done so far
	static private int START_LOOP_DAMAGE = 1; //amount of health we start decrementing at the beginning of a loop
	static private int DAMAGE_WEIGHT = 2; //amount of damage we increase the current damage by per tick. based on gametimer.

	public ChildhoodSoundsMan(){
		//man
		//those childhood sounds, man
		//so childhood
		//so sound
		//man
		picsongs = new ArrayList<PictureSong>();
			picsongs.add(new PictureSong(PictureSongName.TEST_BLUE));
			picsongs.add(new PictureSong(PictureSongName.TEST_BLACK));
			picsongs.add(new PictureSong(PictureSongName.TEST_WHITE));
			pos_picsongs = 0;
			
		wordparts = new ArrayList<WordPart>();
			wordparts.add(new WordPart(WordPartName.TEST_DEATH));
			wordparts.add(new WordPart(WordPartName.TEST_LIFE));
			wordparts.add(new WordPart(WordPartName.TEST_NATURE));
			wordparts.add(new WordPart(WordPartName.TEST_WATER));
			pos_wordparts = 0;
			
		wps = new ArrayList<WordPart>();
		health = 100;
		damage = START_LOOP_DAMAGE;
		starttime = System.nanoTime();
		renderdata = new ChildhoodSoundsRenderData();
		ps = picsongs.get(pos_picsongs); //in this example, should be TEST_BLUE
		cursor_target = ps;
		looptimer = 0;
	}
	@Override
	public void update(InputEvent evt) {
		looptimer++;
		if(evt instanceof KeyEvent){
			updateWithKey((KeyEvent)evt);
		} else if (evt instanceof MouseEvent){
			updateWithMouse((MouseEvent)evt);
		}
		if(looptimer >= looptime){
			health -= damage;
			gametimer++;
			damage += gametimer*DAMAGE_WEIGHT; //*2 is completely arbitrary. may like to have this be a scaling factor, maybe exponential or some shit I don't know
		}
		if(health <= 0){
			endtime = System.nanoTime();
			//endSomeShit(endtime - starttime);
		}
		updateRenderData();
	}

	private void updateRenderData() {
		renderdata.update(ps, cursor_target, health);
	}
	private void updateWithKey(KeyEvent evt) {
		//functions:
		//-select
		//-go back
		//-move cursor (left or right)
		switch(evt.getKeyCode()){
		case (KeyEvent.VK_E): //abstract this to be changeable based on user config
			if(cursor_target instanceof WordPart){
				//the current solution says:
				//you ONLY can succeed by adding more parts
				//I want to reward removing parts as well,
				//but not reward adding/removing the same parts
				//over and over again.
				//I can fix this later by adding some collection of events of adding and removing
				//and limiting point gain by a percentage (even entirely) based on repitition
				if(!wps.contains((WordPart)cursor_target)){
					/*
					 * if(wellTimed){
					 * 	health += damage;
					 * }
					 */
					
					wps.add((WordPart)cursor_target);
					looptimer = 0;
					damage = START_LOOP_DAMAGE;
				}
			} else if (cursor_target instanceof PictureSong){
				ps = (PictureSong)cursor_target;
				cursor_target = !wordparts.isEmpty() ? wordparts.get(pos_wordparts = 0) : new WordPart(null); //ho ho ho look at what I did that is so cool
			} else {
				System.err.println("shit went down in: updateWithKey.\nproblem: cursor_target was an invalid data type.");
			}
			break;
		case (KeyEvent.VK_Q):
			if(cursor_target instanceof WordPart){
				cursor_target = ps != null ? ps : new PictureSong(null);
			} else if (cursor_target instanceof PictureSong){
				break;
			} else {
				System.err.println("shit went down in: updateWithKey.\nproblem: cursor_target was an invalid data type.");
			}
			break;
		case (KeyEvent.VK_RIGHT):
			if(cursor_target instanceof PictureSong){
				if(pos_picsongs <= picsongs.size() - 2){ //as long as it won't break anything,
					pos_picsongs++; //go ahead and move right one picsong
					cursor_target = picsongs.get(pos_picsongs);
				}
			} else if (cursor_target instanceof WordPart){
				if(pos_wordparts <= wordparts.size() - 2){
					pos_wordparts++;
					cursor_target = wordparts.get(pos_wordparts);
				}
			}
			break;
		case (KeyEvent.VK_LEFT):
			if(cursor_target instanceof PictureSong){
				if(pos_picsongs >= 1){ //as long as subtracting one won't go beyond the lower bounds of the array (indexed from 0)
					pos_picsongs--; //go ahead and move left one picsong
					cursor_target = picsongs.get(pos_picsongs);
				}
			} else if (cursor_target instanceof WordPart){
				if(pos_wordparts >= 1){
					pos_wordparts--;
					cursor_target = wordparts.get(pos_wordparts);
				}
			}
			break;
		}
	}
	
	private void updateWithMouse(MouseEvent evt){
		//functions:
		//-move cursor and select simultaneously
		//-go back (right-click)
		//-move cursor(click on side arrows)
		
		//fill this shit in later. it should just be an allowed alternate control scheme with no different functionality
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
