/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package group.thelegendofpeter;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.JFrame;

//Klasse um das Fenster des Spiels einzurichten und die Spiellogik/grafik zu implementieren
public class Main extends Canvas implements Runnable{
    private static final long serialVersionUID = 1L;
    public int tickCount;
    private boolean running; //Gibt an ob das Spiel läuft oder nicht
    public static final int Width = 800; //Die Breite des Fensters
    public static final int Height = Width; //Die Höhe des Fensters
    public static final int Scale = 1;//Der Größenskalierungsfaktor mit dem das Fenster vergrößert wird
    public static final String name = "TheLegendOfPeter";//Der Name des Fensters
    private JFrame frame; //Das Fenster
    private BufferedImage image = new BufferedImage(Width,Height,BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
    private Screen screen = new Screen("level",new SpriteSheet("spritesheet.png", 4, 1, 128, 128));
    
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
    	boolean shouldRender = false;
    	while(running)
    	{
    		long now = System.nanoTime();
    		delta += (now - lastTime) / nsPerTick;
    		lastTime = now;
    		while(delta >= 1)
    		{
    			ticks++;
    			tick();
    			delta -= 1;
    			shouldRender = true;
    		}
    		if(shouldRender)
    		{
    		render();
    		frames++;
    		shouldRender = false;
    		}
    		if(System.currentTimeMillis() - lastTimer >= 1000)
    		{
    			lastTimer += 1000;
    			System.out.println(frames + ", " + ticks);
    			frames = 0;
    			ticks = 0;
    		}
    	}
    }
    
    public void tick() //Aktualiesert die Spiellogik
    {
    	screen.loadLevel(1);
    	tickCount++;
    }
    
    public void render() //Aktualiesiert den Spieleanzeigebereich
    {
    	for(int x= 0;x<Main.Width;x++)
    	{
    		for(int y = 0;y<Main.Height;y++)
    		{
    			pixels[x * Main.Height + y] = screen.getPixel()[x][y];
    		}
    	}
    	BufferStrategy bs = getBufferStrategy();
    	if(bs == null)
    	{
    		createBufferStrategy(2);
    		return;
    	}
    	Graphics g = bs.getDrawGraphics();
    	g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
    	g.dispose();
    	bs.show();
    }
    public static void main(String args[]) //Startet das Spiel
    {
    	new Main().start();
    }
}
