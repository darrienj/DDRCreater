package display.center;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JPanel;

import control.ImageHandler;
import control.Note;
import display.DancePanelInput;
import display.DisplayNoteInput;
import display.InputReceiver;
import display.Scale;

public class MainCenterPanel extends JPanel{
	
	List<Note> noteList;
	int[] selectIndex;
	float[] selectIndexTimer;
	private List<JComponent> componentList;
	private DisplayNoteInput displayNoteInput;
	
	public MainCenterPanel(DisplayNoteInput displayNoteInput){
		this.setFocusable(true);
		componentList = new LinkedList<JComponent>();
		noteList = new ArrayList<Note>();
		selectIndex = new int[4];
		selectIndexTimer = new float[4];
		this.displayNoteInput = displayNoteInput;
	}
	public void addGlobalKeyListener(KeyListener listener){
		for(JComponent component : componentList){
			component.addKeyListener(listener);
		}
	}
	public void update(int currentTime){
		selectIndex[Note.LEFT] = displayNoteInput.getLeft();
		selectIndex[Note.RIGHT] = displayNoteInput.getRight();
		selectIndex[Note.UP] = displayNoteInput.getUp();
		selectIndex[Note.DOWN] = displayNoteInput.getDown();
		displayNoteInput.update(currentTime);
		this.repaint();
	}
	@Override
	public void paintComponent(Graphics g){
		ImageHandler handler = ImageHandler.getImageHandler();
		Scale width = new Scale();
		width.setSize(this.getWidth());
		Scale height = new Scale();
		height.setSize(this.getHeight());
		int ARROW_WIDTH = handler.getLeftArrow(0).getWidth();
		int LEFT_X = width.scale(0.1);
		int DOWN_X = LEFT_X + ARROW_WIDTH;
		int UP_X = DOWN_X + ARROW_WIDTH;
		int RIGHT_X = UP_X + ARROW_WIDTH;
		
		g.drawImage(handler.getLeftArrow(selectIndex[Note.LEFT]), LEFT_X, height.scale(0.05),null);
		g.drawImage(handler.getDownArrow(selectIndex[Note.DOWN]), DOWN_X, height.scale(0.05),null);
		g.drawImage(handler.getUpArrow(selectIndex[Note.UP]), UP_X, height.scale(0.05),null);
		g.drawImage(handler.getRightArrow(selectIndex[Note.RIGHT]), RIGHT_X, height.scale(0.05),null);
	}
	
}
