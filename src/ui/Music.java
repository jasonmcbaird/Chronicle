package ui;

import javax.swing.JPanel;

import utilities.AudioPlayer;
import utilities.Logger;


public class Music extends Display {
	
	private AudioPlayer player = new AudioPlayer(this.getClass().getResourceAsStream("../res/audio/semiSuspense.wav"));
	private boolean playing = false;
	private Thread thread;
	
	public void display(JPanel jPanel) {
		//playSound();
		if(!playing)
			playMe();
	}
	
	public void playMe() {
		thread = new Thread(player);
		thread.start();
		playing = true;
	}
	
	public void remove() {
		Logger.print("Stop the music!", -1);
		player.terminate();
		playing = false;
	}
	
}
