package com.pixilic.javakat.framework;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SFXen {
	AudioInputStream ais;
	Clip clip;
	
	public SFXen(String filename){
		try{
			ais = AudioSystem.getAudioInputStream(new File(filename));
			clip = AudioSystem.getClip();
			clip.open(ais);
		}
		catch(IOException e){
			System.err.println("Error in: SFXen -- SFXen(String). Could not load file (filename: " + filename + ").");
		}
		catch(UnsupportedAudioFileException e){
			System.err.println("Error in SFXen -- SFXen(String). File type of " + filename + " not supported.");
		}
		catch(LineUnavailableException e){
			System.err.println("Error in SFXen -- SFXen(String). Could not open clip.");
		}
	}
	public SFXen(AudioInputStream ais){
		try{
			this.ais = ais;
			clip = AudioSystem.getClip();
			clip.open(this.ais);
		}
		catch(IOException e){
			System.err.println("Error in SFXen -- SFXen(AudioInputStream). Could not load file from stream.");
		}
		catch(LineUnavailableException e){
			System.err.println("Error in SFXen -- SFXen(AudioInputStream). Could not open clip.");
		}
	}
	public void start(){
		if(clip != null) clip.start();
	}
	public void stop(){
		if(clip != null) clip.stop();
	}
}
