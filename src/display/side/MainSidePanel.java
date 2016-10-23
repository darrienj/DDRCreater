package display.side;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import control.Main;
import display.MainDisplay;

public class MainSidePanel extends JPanel{

	private JSlider beatSlider;
	MainDisplay mainDisplay;
	JButton playSongButton;
	private List<JComponent> componentList;
	public MainSidePanel(MainDisplay mainDisplay){
		this.componentList = new LinkedList<JComponent>();
		this.mainDisplay = mainDisplay;
		this.setLayout(new GridLayout(16,1,0,0));
		this.setBackground(Color.RED);
		JLabel songLocationLabel = new JLabel("Song Location");
		final JTextField songLocationInput = new JTextField();
		songLocationInput.setColumns(15);
		JButton songLocationButton = new JButton("Set Location");
		JLabel baseDifficultyLabel = new JLabel("Playback Difficulty");
		final JTextField baseDifficultyInput = new JTextField("Easy");
		JLabel goalDifficultyLabel = new JLabel("Output Difficulty");
		final JTextField goalDifficultyInput = new JTextField("Medium");
		final JButton difficultyButton = new JButton("Set Difficulty");
		playSongButton = new JButton("Play");
		JButton pauseSongButton = new JButton("Pause");
		JButton stopSongButton = new JButton("Stop");
		JButton saveButton = new JButton("Save");
		beatSlider = new JSlider();
		
		componentList.add(songLocationLabel);
		componentList.add(songLocationInput);
		componentList.add(songLocationButton);
		componentList.add(baseDifficultyLabel);
		componentList.add(baseDifficultyInput);
		componentList.add(goalDifficultyLabel);
		componentList.add(goalDifficultyInput);
		componentList.add(difficultyButton);
		componentList.add(playSongButton);
		componentList.add(pauseSongButton);
		componentList.add(stopSongButton);
		componentList.add(beatSlider);
		songLocationButton.addActionListener(new ActionListener() {     
            public void actionPerformed(ActionEvent e) {
            	setSong(songLocationInput.getText());
            }
        });
		difficultyButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				setDifficulty(baseDifficultyInput.getText(),goalDifficultyInput.getText());
			}
		});
		playSongButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				playSong();
			}
		});
		pauseSongButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				pauseSong();
			}
		});
		saveButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				saveSong();
			}
		});
		beatSlider.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e) {
				setSongPosition(beatSlider.getValue());
			}
			
		});
		beatSlider.addMouseListener(new MouseListener(){
			public void mouseReleased(MouseEvent e) {
				playSongButton.requestFocus();
			}
			public void mouseClicked(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			
		});
		this.add(songLocationLabel);
		this.add(songLocationInput);
		this.add(songLocationButton);
		this.add(baseDifficultyLabel);
		this.add(baseDifficultyInput);
		this.add(goalDifficultyLabel);
		this.add(goalDifficultyInput);
		this.add(difficultyButton);
		this.add(beatSlider);
		MultiPanel tripleControlsPanel = new MultiPanel(3);
		tripleControlsPanel.add(playSongButton);
		tripleControlsPanel.add(pauseSongButton);
		tripleControlsPanel.add(stopSongButton);
		this.add(tripleControlsPanel);
		this.add(saveButton);
	}
	public void addGlobalKeyListener(KeyListener listener){
		for(JComponent component : componentList){
			component.addKeyListener(listener);
		}
	}
	public void updateTime(int currentTime){
		if(beatSlider.hasFocus() == false){
			beatSlider.setValue(currentTime);
		}
	}
	/**
	 * this method sets the song in main, and then sets the slider min/max values
	 * based on what the song is.
	 * @param song
	 */
	private void setSong(String song){
		Main.setSong(song);
		beatSlider.setMaximum((int)(Main.getSongLength()));
		System.out.println((int)(Main.getSongLength()));
		beatSlider.setMinimum(0);
		System.out.println("DONE");
	}
	
	/**
	 * Sets the difficulty of the song to playback in main.
	 * @param baseDifficulty the difficulty level of the song to be played in the background.
	 * @param targetDifficulty the difficulty level the output of this program will be
	 */
	private void setDifficulty(String baseDifficulty,String targetDifficulty){
		Main.setDifficulty(baseDifficulty,targetDifficulty);
	}
	private void playSong(){
		Main.playSong();
	}
	private void stopSong(){
		Main.stopSong();
	}
	private void pauseSong(){
		Main.pauseSong();
	}
	private void saveSong(){
		Main.saveSong();
	}
	private void setSongPosition(int position){
		if(beatSlider.hasFocus())
			mainDisplay.setSongTime(position);
	}
	private class MultiPanel extends JPanel{
		
		public MultiPanel(int size){
			this.setLayout(new GridLayout(1,size,0,0));
		}
	}
}
