/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group.thelegendofpeter;

import java.io.InputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

class Sound {
	
     Clip clip;
   
    public Sound(InputStream stream)
    {
        try
        {
            AudioInputStream eingabe = AudioSystem.getAudioInputStream(stream);
            clip = AudioSystem.getClip();
            clip.open(eingabe);
        }
        catch (Exception e) { e.printStackTrace(); }
    }
   
    public void start()
    {
        clip.start();
    }
    
    public void start_LOOP()
    {
        clip.start();
        clip.loop(Clip.LOOP_CONTINUOUSLY);        
    }
    
     public void stop()
    {
        clip.stop();
    }
   
    public boolean isRunning()
    {
        return clip.isRunning();
    }
   
    public void Sound_Start()
    {
    	 Sound test; 
    	 test = new Sound(this.getClass().getClassLoader().getResourceAsStream("sound/Purple Planet Music - Space Journey (1_20).wav"));
    	 test.start_LOOP();
    }
}
