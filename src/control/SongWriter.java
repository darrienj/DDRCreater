package control;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Stores a created song in a file
 * @author drichmond
 *
 */
public class SongWriter {

	private Song song;
	String fileName;
	
	/**
	 * Constructs a new SongWriter.
	 * @param fileName the name of the file to store the song in
	 * @param song the song object holding all of the notes that are part of this song.
	 */
	public SongWriter(String fileName, Song song){
		this.song = song;
		this.fileName = fileName;
	}
	
	/**
	 * Writes the song to a file.
	 * @throws FileNotFoundException 
	 */
	public void write() throws FileNotFoundException{
		PrintWriter writer = new PrintWriter(getFile(fileName));
		List<Note> originalNoteList = song.getNoteList();
		NoteSynchronizer synchronizer = new NoteSynchronizer(160);
		synchronizer.synchronize(originalNoteList);
		List<Note> noteList = originalNoteList;
		for(Note note : noteList){
			String line = "";
			line+= directionToString(note.getDirection());
			line+= numberToString(note.getTime());
			line+= "|"+numberToString(note.getHold());
			writer.println(line);
		}
		writer.close();
	}
	
	/**
	 * Returns a new file that does not exist yet.
	 * @param name the name to be used as the base for the file name.
	 * @return File
	 */
	private File getFile(String name){
		File file = new File(name+".csong");
		int number = 0;
		while(file.exists()){
			file = new File(name+number+".csong");
			number++;
		}
		return file;
	}
	
	/**
	 * Converts the Note direction constant to a String representation.
	 * @param direction
	 * @return String
	 * @throws IllegalStateException if the entered direction is invalid.
	 */
	private String directionToString(int direction){
		if(direction == Note.LEFT){
			return "L";
		} else if(direction == Note.RIGHT){
			return "R";
		} else if (direction == Note.UP){
			return "U";
		} else if(direction == Note.DOWN){
			return "D";
		}
		throw new IllegalStateException();
	}
	
	/**
	 * Converts a number to a string with a set number of digits.  6 digits.
	 * @param number
	 * @return
	 */
	private String numberToString(int number){
		return String.format("%06d", number);
	}
}
