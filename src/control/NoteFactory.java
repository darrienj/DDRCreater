package control;

/**
 * Factory for creating a single note
 * @author drichmond
 *
 */
public class NoteFactory {

	private int direction;
	private int time;
	private int hold;
	private int minHoldTime;
	
	/**
	 * Creates a new NoteFactory
	 */
	public NoteFactory(int minHoldTime){
		this.minHoldTime = minHoldTime;
	}
	
	/**
	 * Sets the direction this note is facing.  Will be a constant in Note class.
	 */
	public void setDirection(int direction){
		this.direction = direction;
	}
	
	/**
	 * Sets the time the note will be played at, in milliseconds.
	 */
	public void setTime(int time){
		this.time = time;
	}
	
	/**
	 * Sets the duration of time this note will be held for, in milliseconds
	 */
	public void setHold(int hold){
		if(hold > minHoldTime){
			this.hold = hold;
		} else{
			this.hold = 0;
		}
	}
	
	/**
	 * Builds a Note with the fields set for this NoteFactory
	 * @return Note
	 */
	public Note build(){
		return new Note(direction,time,hold);
	}
	
	/**
	 * Resets all the values in this builder.
	 */
	public void clean(){
		direction = -1;
		time = 0;
		hold = 0;
	}
	
	/**
	 * Builds the Note and resets all the fields in the NoteFactory.
	 * @return
	 */
	public Note buildAndClean(){
		Note response = build();
		clean();
		return response;
	}
	
}
