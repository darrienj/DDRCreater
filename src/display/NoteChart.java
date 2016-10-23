package display;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import control.Note;

public class NoteChart {

	List<Note> noteList;
	public NoteChart(List<Note> noteList){
		this.noteList = new ArrayList<Note>(noteList);
	}
	
	/**
	 * Returns a view of the notes in the specified range of times.
	 * @param startTime measured in milliseconds
	 * @param endTime measured in milliseconds
	 * @return
	 */
	public List<Note> getNotesInRange(int startTime,int endTime){

		int startIndex = 0;
		while (startIndex < noteList.size() - 1
				&& noteList.get(startIndex).getTime() < startTime) {
			startIndex++;
		}
		int endIndex = startIndex;
		while (endIndex < noteList.size() - 1
				&& noteList.get(endIndex).getTime() < endTime) {
			endIndex++;
		}
		if (noteList.get(startIndex).getTime() <= endTime) {
			List<Note> result = noteList.subList(startIndex, endIndex);
			return result;
		}
		return new LinkedList<Note>();
	}
}
