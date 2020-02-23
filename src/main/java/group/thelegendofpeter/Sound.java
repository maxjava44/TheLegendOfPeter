package group.thelegendofpeter;

import java.io.InputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

class Sound extends Thread {
	
     Clip clip;
     boolean playing = false;
   
    public Sound(String name,InputStream stream)
    {
        super(name);
        try
        {
            AudioInputStream eingabe = AudioSystem.getAudioInputStream(new BufferedInputStream(stream));
            clip = AudioSystem.getClip();
            clip.open(eingabe);
        }
        catch (Exception e) { e.printStackTrace(); }
    }
    
    public void start_LOOP()
    {
        clip.start();
        clip.loop(Clip.LOOP_CONTINUOUSLY);        
    }
   
    public boolean isRunning()
    {
        return clip.isRunning();
    }
   
    public void Sound_Start()
    {
    	 Sound test; 
    	 //test = new Sound(this.getClass().getClassLoader().getResourceAsStream("sound/Purple Planet Music - Space Journey (1_20).wav"));
    	 //test.start_LOOP();
    }

	@Override
	public void run() {
		while(true)
		{
			if(playing)
			{
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			}
			else
			{
				clip.stop();
				clip.setMicrosecondPosition(0);
			}
		}
	}
}
