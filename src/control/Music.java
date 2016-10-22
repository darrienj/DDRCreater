package control;
import java.applet.AudioClip;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
/**
 * 
 * @author drichmond
 * This class represents the music being played.
 * Can tell the length of the song, 
 */
public class Music implements LineListener{
	
	Clip clip;
	boolean isDone;
	File audioFile;
	private long lastMicrosecondPosition;
	public Music(String fileName){
		this.audioFile = new File(fileName);
		
	}
	
	public void play() throws LineUnavailableException, UnsupportedAudioFileException, IOException{
		if(clip != null){
			clip.setMicrosecondPosition(lastMicrosecondPosition);
			clip.start();
		} else{
			openClip();
			clip.start();
		}
	}
	public void openClip() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
		AudioInputStream audioIn = AudioSystem.getAudioInputStream(audioFile.getAbsoluteFile());
		clip = AudioSystem.getClip();
		clip.addLineListener(this);
		clip.open(audioIn);
		isDone = false;
	}
	public boolean isReady(){
		return clip != null && clip.isOpen();
	}
	public void pause(){
		lastMicrosecondPosition = clip.getMicrosecondPosition();
		clip.stop();
	}
	public void stop(){
		clip.stop();
		clip.close();
	}
	public boolean isFinished(){
		return isDone;
	}
	public void update(LineEvent le) {
	    if(clip.getFramePosition()>=clip.getFrameLength()){
	    	clip.stop();
	    	clip.close();
	    }
	 }
	
	/**
	 * Sets the current time of the song in milliseconds
	 * @param time time in milliseconds
	 */
	public void setMilliseconds(int time){
		time = time/1000;
		clip.setMicrosecondPosition(time);
		lastMicrosecondPosition = time;
	}
	
	/**
	 * Gets the current time in the song in milliseconds
	 * @return time in milliseconds
	 */
	public double getCurrentTime(){
		return clip.getMicrosecondPosition() / 1000.0;
	}
	
	/**
	 * Gets the duration of the music in milliseconds
	 * @return maximum amount of time the music will play for
	 */
	public double getLength(){
		return clip.getMicrosecondLength() /1000.0;
	}
}
