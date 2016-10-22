package display;

import control.Note;
import control.Song;

public class InputReceiverImpl implements InputReceiver{
	
	private DisplayNoteInput displayNoteInput;
	private Song song;

	public InputReceiverImpl(Song song){
		this.song = song;
	}
	
	public void setDisplayNoteInput(DisplayNoteInput displayNoteInput){
		this.displayNoteInput = displayNoteInput;
	}
	
	@Override
	public void pressRight(){
		displayNoteInput.pressRight();
		song.startNote(Note.RIGHT);
		
	}
	@Override
	public void pressLeft(){
		displayNoteInput.pressLeft();
		song.startNote(Note.LEFT);

	}
	@Override
	public void pressUp(){
		displayNoteInput.pressUp();
		song.startNote(Note.UP);

	}
	@Override
	public void pressDown(){
		displayNoteInput.pressDown();
		song.startNote(Note.DOWN);

	}
	@Override
	public void releaseLeft() {
		displayNoteInput.releaseLeft();
		song.finishNote(Note.LEFT);

	}
	@Override
	public void releaseRight() {
		displayNoteInput.releaseRight();
		song.finishNote(Note.RIGHT);

	}
	@Override
	public void releaseUp() {
		displayNoteInput.releaseUp();
		song.finishNote(Note.UP);
	}
	@Override
	public void releaseDown() {
		displayNoteInput.releaseDown();
		song.finishNote(Note.DOWN);
	}
	@Override
	public void pressEnter() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void releaseEnter() {
		// TODO Auto-generated method stub
		
	}
}
