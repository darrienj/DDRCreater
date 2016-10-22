package control;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * NoteChart represents a changing list of notes. It takes times and returns
 * notes that are associated with those times
 * 
 * @author drichmond
 *
 */
public class NoteChart {

	protected ArrayList<Note> noteList;

	public NoteChart() {
		noteList = new ArrayList<Note>();
	}

	/**
	 * Adds a note to the NoteChart.
	 * 
	 * @param time
	 */
	public void addNote(Note note) {
		noteList.add(0, note);
	}

	/**
	 * Gets next note after the specified time.
	 * 
	 * @param time
	 *            the time to get the note from in milliseconds
	 * @return Note next Note after the specified time
	 */
	public Note getNote(int time) {
		int startIndex = 0;
		while (startIndex < noteList.size()
				&& noteList.get(startIndex).getTime() <= time) {
			startIndex++;
		}
		if (startIndex < noteList.size()) {
			return noteList.get(startIndex);
		} else {
			return null;
		}
	}

	/**
	 * Gets an immutable List of Notes within the specified time range.  Will not be null.
	 * 
	 * @param startTime
	 *            the minimum time to include a note from, inclusive
	 * @param endTime
	 *            the maximum time to include a note from, exclusive
	 * @return List<Note> a list of notes in ascending order of time, with the
	 *         lowest time first.  Will never return null.
	 */
	public List<Note> getNoteList(int startTime, int endTime) {
		List<Note> answer = new ArrayList<Note>();
		int startIndex = 0;
		while (startIndex < noteList.size()
				&& noteList.get(startIndex).getTime() < startTime) {
			startIndex++;
		}
		int index = startIndex;
		while (index < noteList.size()
				&& noteList.get(index).getTime() < endTime) {
			answer.add(noteList.get(index));
			index++;
		}
		List<Note> response = new ArrayList<Note>(answer);
		Collections.sort(response, new Comparator<Note>(){
			@Override
			public int compare(Note note1, Note note2) {
				return note1.getTime() - note2.getTime();
			}	
		});
		return response;
	}

	/**
	 * Adds a note to the NoteChart in ascending order of time. It uses the
	 * specified index as a starting point. Nothing before the index should have
	 * a greater time than the Note being added.
	 * 
	 * @param index
	 *            starting index, simply used to make the algorithm faster so it
	 *            doesn't have to start at index 0 if you know everything before
	 *            index 5 is less than the note
	 * @param note
	 *            the note being added.
	 */
	protected void addNoteInOrder(int index, Note note) {
		int addIndex = index;
		while (addIndex < noteList.size()
				&& noteList.get(addIndex).getTime() < note.getTime()) {
			addIndex++;
		}
		if (addIndex < noteList.size()) {
			noteList.add(addIndex, note);
		} else {
			noteList.add(note);
		}
	}

}
