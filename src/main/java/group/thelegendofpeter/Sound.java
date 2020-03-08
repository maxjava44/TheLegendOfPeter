package group.thelegendofpeter;

import java.io.InputStream;
import java.io.BufferedInputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * Verwaltet den Sound in einen extra Thread
 * 
 * @author Maximilian Gilsoul,Maximilian Pitzke,Tristan Michels
 *
 */
class Sound extends Thread {

	Clip clip;
	boolean playing = false;

	public Sound(String name, InputStream stream) {
		super(name);
		try {
			AudioInputStream eingabe = AudioSystem.getAudioInputStream(new BufferedInputStream(stream));
			clip = AudioSystem.getClip();
			clip.open(eingabe);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		while (true) {
			if (playing) {
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			} else {
				clip.stop();
				clip.setMicrosecondPosition(0);
			}
		}
	}
}
