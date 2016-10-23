package control;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CustomSong {

	private List<Note> noteList;
	private File file;
	private int difficultyLevel;
	private int noteCount;
	private int totalHold;

	/**
	 * Creates a new custom song
	 * @param fileName the name of the file where the song is located at
	 * @param difficulty the difficulty folder where the song is located in
	 * @param songName the name of the song.
	 */
	public CustomSong(String fileName, String difficulty) {
		noteList = new ArrayList<Note>();
		file = new File(fileName);
		if(difficulty.equals("Hard")){
			difficultyLevel = 2;
		} else if(difficulty.equals("Medium")){
			difficultyLevel = 1;
		} else{
			difficultyLevel = 0;
		}
	}

	/**
	 * Builds the CustomSong and generates a list of arrows to represent the
	 * song
	 * 
	 * @throws IOException
	 *             if an error occurs when reading the file
	 */
	public void build() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(file));
		while (reader.ready()) {
			String line = reader.readLine();
			int direction = parseDirection(line);
			int time = parseTime(line);
			int hold = parseHold(line);
			Note note = new Note(direction,time, hold);
			noteList.add(note);
			noteCount++;
			totalHold += hold;
		}
		reader.close();
	}

	/**
	 * Gets the list of arrows which describe the song
	 * 
	 * @return List<Note> a list of Arrow in ascending order of time
	 */
	public List<Note> getNoteList() {
		return noteList;
	}

	/**
	 * Reads the input line of the song file and returns the direction of the
	 * note described in the line
	 * 
	 * @param line
	 * @return Note direction
	 */
	private int parseDirection(String line) {
		if (line.charAt(0) == 'L') {
			return Note.LEFT;
		} else if (line.charAt(0) == 'R') {
			return Note.RIGHT;
		} else if (line.charAt(0) == 'U') {
			return Note.UP;
		} else if (line.charAt(0) == 'D') {
			return Note.DOWN;
		}
		throw new IllegalArgumentException("Direction is an invalid value");
	}

	/**
	 * Reads the input line of the song file and returns the time the note
	 * should be played at in milliseconds
	 * 
	 * @param line
	 * @return time in milliseconds
	 */
	private int parseTime(String line) {
		String timeString = line.substring(1, 7);
		return Integer.parseInt(timeString);
	}

	/**
	 * Reads the input line of the song file and returns the amount of time the
	 * note should be held, measured in milliseconds
	 * 
	 * @param line
	 * @return duration of hold in milliseconds
	 */
	private int parseHold(String line) {
		String timeString = line.substring(8, 14);
		return Integer.parseInt(timeString);
	}

	public double getOffset() {
		return 0;
	}

	public int getDifficulty() {
		return difficultyLevel;
	}

	public int getNoteCount() {
		return noteCount;
	}

	/**
	 * Gets the total amount of time in milliseconds of all the holds in the
	 * song should last
	 * 
	 * @return totalHoldDuration
	 */
	public int getTotalHoldDuration() {
		return totalHold;
	}
}
