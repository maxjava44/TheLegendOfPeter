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

	private Clip clip;
	private boolean playing = false;
        public boolean Microposi_null_erlaubt = false; //Micro darf ebenfalls nicht auf 0 gesetzt werden
        public boolean SoundArtAttack = false; // Standardsound = loopsound

	/**
	 * Erstellt den Sound aus einen InputStream
	 * 
	 * @param name   Der Name des Threads
	 * @param stream Der Inputstream der Sounddatei
	 */
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

	/**
	 * Setzt den Spielzustand
	 * 
	 * @param pPlaying Soll spielen oder nicht
	 */
	public void setPlaying(boolean pPlaying) {
		playing = pPlaying;
	}

	/**
	 * Loopt den Sound wenn er loopen soll
	 */
	@Override
	public void run() {
		while (true) {
			if (playing) {
				 if (SoundArtAttack==true) // Was für ein Sound wird gesucht? Loop oder einzeln
                            {
                                     clip.start();                     
                                    
                                    if (Microposi_null_erlaubt==true)
                                    {
                                    clip.setMicrosecondPosition(0);                   
                                    Microposi_null_erlaubt=false;
                                    }                 
                            }
                            else
                            {                                    
				clip.loop(Clip.LOOP_CONTINUOUSLY);
                            }
                                 
			} else {
				clip.stop();
				clip.setMicrosecondPosition(0);
			}
		}
	}
}
