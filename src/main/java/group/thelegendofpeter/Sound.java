/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group.thelegendofpeter;

/**
 *
 * @author Maximilian Pitzke
 */
import java.io.File;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
/**
 *
 * @author Tristan Michels
 */
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
        //JOptionPane.showMessageDialog(,"ss");        
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
       public void Sound_Start2()
    {
       
              
                  //Sound test = new Sound("C:/A_Netbeans/TheLegendOfPeter-master/Lauf.wav"); 
                   // test.start();
                    
              
           }
           
  public void Sound_Stoppen()
    {
       
       stop();
    }

    }
