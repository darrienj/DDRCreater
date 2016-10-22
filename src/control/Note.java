package control;

import java.awt.image.BufferedImage;
import com.google.gson.*;

public class Note {
	public final static int LEFT = 0;
	public final static int RIGHT = 1;
	public final static int UP = 2;
	public final static int DOWN = 3;
	private int direction;
	private int time;
	private int hold;

	/**
	 * Creates a new note with the specified values.
	 * 
	 * @param direction
	 *            the direction of the note. Should be a direction constant
	 *            stored in Note class.
	 * @param time
	 *            the time the note should be played. Measured in milliseconds.
	 * @param hold
	 *            the duration of time the note should be held. Measured in
	 *            milliseconds.
	 */
	public Note(int direction, int time, int hold) {

		this.direction = direction;
		this.time = time;
		this.hold = hold;
	}

	public int getTime() {
		return time;
	}

	public int getHold() {
		return hold;
	}

	public int getDirection() {
		return this.direction;
	}

	public String toJSON() {
		Gson gson = new Gson();
		String json = gson.toJson(this);
		return json;
	}
}
