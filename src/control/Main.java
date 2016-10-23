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
	private static CustomSong displaySong;
	private static String baseDifficulty;
	private static String targetDifficulty;
	private static MainDisplay mainDisplay;

	public static void main(String[] args) throws IOException {
		song = new Song();
		musicPlaying = false;
		playSongLock = new ReentrantLock(true);
		ImageHandler.createImageHandler();
		InputReceiverImpl inputReceiver = new InputReceiverImpl(song);
		mainDisplay = new MainDisplay(inputReceiver);
		thread = new InputThread(mainDisplay);
		(new Thread(thread)).start();
	}

	public static void setSong(String song) {
		songName = song;
		musicFile = Constants.FILE_BASE + "DDR_Files/Music/" + songName
				+ ".wav";
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

	public static void setDifficulty(String baseDifficultyIn,
			String targetDifficultyIn) {
		baseDifficulty = baseDifficultyIn;
		targetDifficulty = targetDifficultyIn;
	}

	public static void setSongTime(int milliseconds) {
		music.setMilliseconds(milliseconds);
	}

	/**
	 * gets how long the currently selected song is
	 * 
	 * @return the currently selected song's length in microseconds
	 */
	public static double getSongLength() {
		setMusic();// store in ms where in the song each note is. No concept of
					// BPM here.
		return music.getLength();
	}

	public static void pauseSong() {
		if (playSongLock.tryLock() && musicPlaying == false) {
			playSongLock.unlock(); // do nothing, song wasn't playing
		} else {
			music.pause();
			musicPlaying = false;
			playSongLock.unlock();
		}
	}

	public static void playSong() {
		if (playSongLock.tryLock()) {
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
			if (targetDifficulty != "" && targetDifficulty != null) {
				songFile = Constants.FILE_BASE + "DDR_Files/Song/"
						+ targetDifficulty + "/" + songName;
			} else {
				songFile = Constants.FILE_BASE + "DDR_Files/Song/" + songName;
			}
			try {
				if (baseDifficulty != "" && baseDifficulty != null) {
					displaySong = new CustomSong(Constants.FILE_BASE
							+ "DDR_Files/Song/" + baseDifficulty + "/"
							+ songName + ".csong", baseDifficulty);
					displaySong.build();
				} else {
					displaySong = null;
				}
				System.out.println("Got song");
			} catch (IOException e) {
				System.out.println(e);
				displaySong = null;
			}
			mainDisplay.setCustomSong(displaySong);
		}

	}

	public static CustomSong getCustomSong() {
		return displaySong;
	}

	public static void stopSong() {
		if (playSongLock.tryLock() && musicPlaying == false) {
			playSongLock.unlock(); // do nothing, song wasn't playing
		} else {
			music.stop();
			musicPlaying = false;
			playSongLock.unlock();
		}
	}

	public static void createSong() {
		playSong();
	}

	private static String selectSong() {
		return "standOut";
	}

	public static void saveSong() {
		SongWriter writer = new SongWriter(songFile, song);
		try {
			writer.write();
		} catch (FileNotFoundException e) {
			throw new RuntimeException("Failed to save file");
		}
		stopSong();
	}

	private static void setMusic() {
		if (music == null) { // TODO remove this if. Seems unnecessary
			music = new Music(musicFile);
		}
	}

	/**
	 * Updates the note creation process.
	 * 
	 * @param time
	 *            the current time in the song, in milliseconds
	 */
	public static void update(int time) {
		song.update(time);
	}
}
