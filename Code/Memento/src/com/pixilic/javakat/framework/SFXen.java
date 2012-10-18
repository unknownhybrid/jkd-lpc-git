package com.pixilic.javakat.framework;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SFXen {
	AudioInputStream ais;
	Clip clip;
	
	public SFXen(String filename){
		try{
			ais = AudioSystem.getAudioInputStream(new File(filename));
		}
		catch(IOException e){
			System.err.println("Error in: SFXen -- SFXen(). Could not load file (filename: " + filename + ").");
			System.exit(0);
		}
		catch(UnsupportedAudioFileException e){
			System.err.println("Error in SFXen -- SFXen(). File type ");
		}
	}
}
