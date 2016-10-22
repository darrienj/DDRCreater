package control;

import java.util.List;

public class NoteSynchronizer {

	private int range;
	private double FAVOR_LEFT = .7;
	private double FAVOR_RIGHT = .3;

	/**
	 * Creates a new NoteSynchronizer.
	 * 
	 * @param range
	 *            the amount of time in milliseconds notes can be off from each
	 *            other before the synchronizer considers them to be at the same
	 *            time
	 */
	public NoteSynchronizer(int range) {
		this.range = range;
	}

	/**
	 * Synchronizes the notes in the specified lists. This means that it changes
	 * the notes in the list that is passed in
	 * 
	 * @param originalNoteList
	 */
	public void synchronize(List<Note> originalNoteList) {
		int size = originalNoteList.size();
		for (int i = 0; i < size; i++) {
			synchronize(i,originalNoteList);
		}
	}

	/**
	 * Synchronizes a note with the notes around it. If there is a note within
	 * the specified range, it will set this note and that note to have the same
	 * time as each other
	 * 
	 * @param index
	 * @param noteList
	 *            a list of notes ordered in ascending order by note time.
	 */
	private void synchronize(int index, List<Note> noteList) {
		if (index <= 0 || index >= noteList.size()) {
			return;
		}
		Note note = noteList.get(index);
		Note leftNote = noteList.get(index - 1);
		if (note.getTime() - leftNote.getTime() < range) {
			int newTime = (int) (leftNote.getTime() * FAVOR_LEFT + note
					.getTime() * FAVOR_RIGHT);
			Note newLeftNote = new Note(leftNote.getDirection(), newTime,
					leftNote.getHold());
			Note newNote = new Note(note.getDirection(), newTime,
					note.getHold());
			noteList.remove(index);
			noteList.add(index, newNote);
			noteList.remove(index - 1);
			noteList.add(index - 1, newLeftNote);
		}
	}
}
