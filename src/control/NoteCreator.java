package control;

/**
 * Knows how to create a note for each direction
 * 
 * @author drichmond
 *
 */
public class NoteCreator {

	private final static int UNSET_TIME = -1;
	private NoteFactory[] noteFactory;
	private int[] startTime = new int[4];

	/**
	 * Constructs a new NoteCreator
	 */
	public NoteCreator(int minHoldTime) {
		this.noteFactory = new NoteFactory[4];
		this.noteFactory[directionToIndex(Note.LEFT)] = new NoteFactory(minHoldTime);
		this.noteFactory[directionToIndex(Note.RIGHT)] = new NoteFactory(minHoldTime);
		this.noteFactory[directionToIndex(Note.UP)] = new NoteFactory(minHoldTime);
		this.noteFactory[directionToIndex(Note.DOWN)] = new NoteFactory(minHoldTime);
		this.noteFactory[directionToIndex(Note.LEFT)].setDirection(Note.LEFT);
		this.noteFactory[directionToIndex(Note.RIGHT)].setDirection(Note.RIGHT);
		this.noteFactory[directionToIndex(Note.UP)].setDirection(Note.UP);
		this.noteFactory[directionToIndex(Note.DOWN)].setDirection(Note.DOWN);
		this.startTime[0] = UNSET_TIME;
		this.startTime[1] = UNSET_TIME;
		this.startTime[2] = UNSET_TIME;
		this.startTime[3] = UNSET_TIME;
	}

	/**
	 * Starts a new note for the specified direction. If a note was already
	 * started in that direction, this method will override the start time of
	 * that note.
	 * 
	 * @param time
	 *            the time in milliseconds of when is note is starting from
	 * @param direction
	 *            the direction the note is facing. A constant value from the
	 *            Note class.
	 */
	public void startNote(int time, int direction) {
		this.noteFactory[directionToIndex(direction)].setTime(time);
		this.startTime[directionToIndex(direction)] = time;
	}

	/**
	 * Finishes the note being created in the specified direction and returns
	 * the result. After this the note in that direction has been created and
	 * startNote will need to be called on this direction before this method can
	 * be called another time.
	 * 
	 * @param time the time the note is finishing at, in milliseconds
	 * @param direction the direction of the note, a constant value from the Note class.
	 * @return
	 */
	public Note finishNote(int time, int direction) {
		int startTime = getStartTime(direction);
		noteFactory[directionToIndex(direction)].setHold(time - startTime);
		Note response = noteFactory[directionToIndex(direction)]
				.buildAndClean();
		noteFactory[directionToIndex(direction)].setDirection(direction);
		return response;
	}

	/**
	 * Gets the start time of the note facing the specified direction
	 * 
	 * @param direction
	 *            the untransformed direction of the Note
	 * @return the start time of the note in milliseconds
	 */
	private int getStartTime(int direction) {
		int result = this.startTime[directionToIndex(direction)];
		if (result == UNSET_TIME) {
			throw new IllegalStateException(
					"Cannot get start time.  Arrow for that direction has already been build");
		}
		this.startTime[directionToIndex(direction)] = UNSET_TIME;
		return result;
	}

	/**
	 * Converts the direction of the note to an index for the noteFactory array
	 * 
	 * @param direction
	 * @return
	 */
	private int directionToIndex(int direction) {
		return direction;
	}
}
