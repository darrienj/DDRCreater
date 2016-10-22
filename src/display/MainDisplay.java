package display;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JFrame;

import control.Main;
import control.Song;
import display.center.MainCenterPanel;
import display.side.MainSidePanel;

public class MainDisplay{

	public static final int SELECT_HIGHLIGHT_DELAY = 32;
	private MainCenterPanel mainCenterPanel;
	private MainSidePanel mainSidePanel;
	private List<JComponent> componentList;
	private DisplayNoteInput displayNoteInput;
	
	public MainDisplay(InputReceiverImpl inputReceiver){
		displayNoteInput = new DisplayNoteInput(SELECT_HIGHLIGHT_DELAY,5);
		inputReceiver.setDisplayNoteInput(displayNoteInput);
		mainCenterPanel = new MainCenterPanel(displayNoteInput);
		mainSidePanel = new MainSidePanel(this);
		componentList = new LinkedList<JComponent>();
		componentList.add(mainCenterPanel);
		componentList.add(mainSidePanel);
		JFrame frame = new JFrame();
		frame.add(mainCenterPanel,BorderLayout.CENTER);
		frame.add(mainSidePanel,BorderLayout.EAST);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.pack();
		addGlobalKeyListener(new DancePanelInput(inputReceiver));
	}
	
	private void addGlobalKeyListener(KeyListener listener){
		for(JComponent component : componentList){
			component.addKeyListener(listener);
		}
		mainCenterPanel.addGlobalKeyListener(listener);
		mainSidePanel.addGlobalKeyListener(listener);
	}
	public void setSongTime(int microseconds){
		Main.setSongTime(microseconds);
	}
	
	/**
	 * Updates the MainDisplay and all its sub components
	 * @param time the current time in milliseconds
	 */
	public void updateTime(int time){
		mainSidePanel.updateTime(time);
		mainCenterPanel.update(time);
		displayNoteInput.update(time);
	}
}
