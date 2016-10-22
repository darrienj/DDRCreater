package control;
import java.awt.FlowLayout;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

import display.*;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.*;

public class Main {

	private static String songName;
	private static String musicFile;
	private static String songFile;
	private static Lock playSongLock;
	private static boolean musicPlaying;
	private static Music music;
	private static InputThread thread;
	private static NoteChart noteChart;
	private static Song song;
	
	public static void main(String[] args) throws IOException{
		song = new Song();
		musicPlaying = false;
		playSongLock = new ReentrantLock(true);
		ImageHandler.createImageHandler();
		InputReceiverImpl inputReceiver = new InputReceiverImpl(song);
		MainDisplay frame = new MainDisplay(inputReceiver);
		thread = new InputThread(frame);
		(new Thread(thread)).start();
	}
	public static void setSong(String song){
		songName = song;
		musicFile = Constants.FILE_BASE + "DDR_Files/Music/"+songName + ".wav";
		songFile = Constants.FILE_BASE + "DDR_Files/Song/tmp/"+songName+".song";
		setMusic();
		thread.setMusic(music);
		try {
			music.openClip();
			noteChart = new NoteChart();
		} catch (LineUnavailableException | UnsupportedAudioFileException
				| IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void setSongTime(int milliseconds){
		music.setMilliseconds(milliseconds);
	}
	/**
	 * gets how long the currently selected song is
	 * @return the currently selected song's length in microseconds
	 */
	public static double getSongLength(){
		setMusic();//store in ms where in the song each note is.  No concept of BPM here.
		return music.getLength();
	}
	public static void pauseSong(){
		if(playSongLock.tryLock() && musicPlaying == false){
			playSongLock.unlock(); //do nothing, song wasn't playing
		} else{
			music.pause();
			musicPlaying = false;
			playSongLock.unlock();
		}
	}
	public static void playSong(){
		if(playSongLock.tryLock()){
			musicPlaying = true;
			setMusic();
			try {
				music.play();
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedAudioFileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	public static void stopSong(){
		if(playSongLock.tryLock() && musicPlaying == false){
			playSongLock.unlock(); //do nothing, song wasn't playing
		} else{
			music.stop();
			musicPlaying = false;
			playSongLock.unlock();
		}
	}
	public static void createSong(){
		playSong();
	}
	private static String selectSong(){
		return "standOut";
	}
	public static void saveSong(){
		SongWriter writer = new SongWriter(songName,song);
		try{
			writer.write();
		} catch(FileNotFoundException e){
			throw new RuntimeException("Failed to save file");
		}
		stopSong();
	}
	private static void setMusic(){
		if(music == null){
			music = new Music(musicFile);
		}
	}
	
	/**
	 * Updates the note creation process.  
	 * @param time the current time in the song, in milliseconds
	 */
	public static void update(int time){
		song.update(time);
	}
}
