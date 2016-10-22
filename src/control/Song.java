package control;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * This class represents a song, by holding all of the notes in the song. It
 * creates the song while running in parallel with the song being played.
 * 
 * @author drichmond
 *
 */
public class Song {

	private NoteCreator noteCreator;
	private NoteChart noteChart;
	private int time;
	
	public Song() {
		this.noteCreator = new NoteCreator(400);
		this.noteChart = new NoteChart();
	}

	/**
	 * Updates the current time of the song
	 * @param time time in the music currently.  Measured in milliseconds.
	 */
	public void update(int time){
		this.time = time;
	}
	
	/**
	 * Starts a new note.
	 * @param direction the direction of the note to start.  Will be a constant of the Note class.
	 */
	public void startNote(int direction) {
		noteCreator.startNote(time, direction);
	}
	
	/**
	 * Finishes the note in the specified direction.
	 * @param direction the direction of the note to finish.  Will be a constant of the Note class.
	 */
	public void finishNote(int direction) {
		Note note = noteCreator.finishNote(time, direction);
		noteChart.addNote(note);
	}
	
	public List<Note> getNoteList(){
		return noteChart.getNoteList(0,Integer.MAX_VALUE);
	}
}