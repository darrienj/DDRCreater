package control;

import display.MainDisplay;

public class InputThread implements Runnable{

	private MainDisplay frame;
	private Music music;
	
	public InputThread(MainDisplay frame){
		this.frame = frame;
	}
	public void setMusic(Music music){
		this.music = music;
	}
	@Override
	public void run() {
		while(true){
			if(music != null && music.isReady()){
				frame.updateTime((int)(music.getCurrentTime()));
				Main.update((int)(music.getCurrentTime()));
			}
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}		
	}

	
}
