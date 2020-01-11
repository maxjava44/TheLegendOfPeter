/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package group.thelegendofpeter;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

//Klasse um das Fenster des Spiels einzurichten und die Spiellogik/grafik zu implementieren
public class Main extends Canvas implements Runnable{
    private static final long serialVersionUID = 1L;
    boolean running; //Gibt an ob das Spiel läuft oder nicht
    public static final int Width = 160; //Die Breite des Fensters
    public static final int Height = Width / 12 *9; //Die Höhe des Fensters
    public static final int Scale = 3;//Der Größenskalierungsfaktor mit dem das Fenster vergrößert wird
    public static final String name = "TheLegendOfPeter";//Der Name des Fensters
    private JFrame frame; //Das Fenster
    
    public Main() //Konstruktor der Klasse
    {
    	setMinimumSize(new Dimension(Width*Scale, Height*Scale));
    	setMaximumSize(new Dimension(Width*Scale, Height*Scale));
    	setPreferredSize(new Dimension(Width*Scale, Height*Scale));
        frame = new JFrame(name); //Gibt dem Fenster den Namen name
        frame.setMinimumSize(new Dimension(Width*Scale, Height*Scale));//Setzen die Größe des Fensters
    	frame.setMaximumSize(new Dimension(Width*Scale, Height*Scale));
    	frame.setPreferredSize(new Dimension(Width*Scale, Height*Scale));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Wenn Fenster geschlossen wird schließst sich auch das Programm
        frame.setLayout(new BorderLayout()); //Setzt ein Borderlayout damit für die obere Fensterleiste Platz ist
        frame.add(this,BorderLayout.CENTER);//Setzt das Canvas(der Spielanzeigebereich) in die Mitte 
        frame.setResizable(false);//Fenster kann nicht vergrößert/verkleinert werden
        frame.setLocationRelativeTo(null);//Fenster wird mittig platziert
        frame.setVisible(true);//Fenster wird sichtbar
    }
    public synchronized void start() //Startet einen Thread in dem das Spiel läuft
    {
    	running = true;
    	new Thread(this).start();
    }
    
    public synchronized void stop() //Stoppt das Spiel
    {
    	running = false;
    }
    
    public void run() //ruft die Spielelogik 60 mal in der Sekunde auf
    {
    	long lastTime = System.nanoTime();
    	double nsPerTick = 1000000000D/60D;
    	int frames = 0;
    	int ticks = 0;
    	long lastTimer = System.currentTimeMillis();
    	double delta = 0;
    	while(running)
    	{
    		long now = System.nanoTime();
    		delta += (now - lastTime);
    		tick();
    		render();
    	}
    }
    
    public void tick() //Aktualiesert die Spiellogik
    {
    	
    }
    
    public void render() //Aktualiesiert den Spieleanzeigebereich
    {
    	
    }
    public static void main(String args[]) //Startet das Spiel
    {
    	new Main().start();
    }
}
