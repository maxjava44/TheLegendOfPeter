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
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.swing.JOptionPane;
/**
 *
 * @author maximilian.pitzke
 */
class Sound {

    int f;
 /* public void berechneWas() {

        Object lock = new Object();

        //String soundFile = "H:/Dokumente/Laser_Shoot.wav";

        String soundFile = "D:/0_Downloadsfuerandere/E6C2/C2_FUll_mitLE/MB_C2_E5/Sound-10er/fahrt2.wav";


        
        for(int i=0; i<1; i++) {
		
        
        System.out.println("Now the output is redirected!"+i);
         
         Sound test = new Sound("C:\\musik.wav");
        test.start();
        
        }
  }
   public void start()
    {
        clip.start();
    }
}

*/
     Clip clip;
   
    public Sound(String path)
    {
        try
        {
            AudioInputStream eingabe = AudioSystem.getAudioInputStream(new File(path));
           
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
       Sound test = new Sound("C:/A_Netbeans/TheLegendOfPeter-master/Purple Planet Music - Space Journey (1_20).wav"); 
        test.start_LOOP();
        
    }
       public void Sound_Start2()
    {
       
              
                  Sound test = new Sound("C:/A_Netbeans/TheLegendOfPeter-master/Lauf.wav"); 
                    test.start();
                    
              
           }
           
  public void Sound_Stoppen()
    {
       
       stop();
    }

    }
