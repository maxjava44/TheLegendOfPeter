package group.thelegendofpeter;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

/**
 * Startet das Spiel und richtet die Grundstruktur ein
 * 
 * @author Maximilian Gilsoul
 *
 */
public class Main extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	public int tickCount;
	private boolean running; // Gibt an ob das Spiel läuft oder nicht
	public static final int Width = 768; // Die Breite des Fensters
	public static final int Height = Width; // Die Höhe des Fensters
	public static final int Scale = 1;// Der Größenskalierungsfaktor mit dem das Fenster vergrößert wird
	public static final String name = "TheLegendOfPeter";// Der Name des Fensters
	private BufferedImage image = new BufferedImage(Width, Height, BufferedImage.TYPE_INT_ARGB);
	private BufferedImage healthimg = new BufferedImage(768, 50, BufferedImage.TYPE_INT_ARGB);
	private Game game = new Game(new SpriteSheet("spritesheet.png", 17, 1, 64, 64));
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	private int[] healthpixels = ((DataBufferInt) healthimg.getRaster().getDataBuffer()).getData();
	private Canvas healthbar = new Canvas();

	/**
	 * Erstellt das Fenster
	 */
	public Main() // Konstruktor der Klasse
	{
		setMinimumSize(new Dimension(Width * Scale, Height * Scale));
		setMaximumSize(new Dimension(Width * Scale, Height * Scale));
		setPreferredSize(new Dimension(Width * Scale, Height * Scale));
		JFrame frame = new JFrame(name); // Erstellt das Fenster und gibt dem Fenster den Namen ,,name"
		frame.addKeyListener(game);
		frame.setMinimumSize(new Dimension(Width * Scale, Height * Scale + 50));// Setzen die Größe des Fensters
		frame.setMaximumSize(new Dimension(Width * Scale, Height * Scale + 50));
		frame.setPreferredSize(new Dimension(Width * Scale, Height * Scale + 50));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// Wenn Fenster geschlossen wird schließst sich auch das Programm
		frame.setLayout(new BorderLayout()); // Setzt ein Borderlayout damit für die obere Fensterleiste Platz ist
		frame.add(this, BorderLayout.CENTER);// Setzt das Canvas(der Spielanzeigebereich) in die Mitte
		frame.add(healthbar, BorderLayout.SOUTH);
		healthbar.setBounds(0, 768, 768, 50);
		frame.setResizable(false);// Fenster kann nicht vergrößert/verkleinert werden
		frame.setLocationRelativeTo(null);// Fenster wird mittig platziert
		frame.setVisible(true);// Fenster wird sichtbar
	}

	/**
	 * Startet das Spiel
	 */
	public synchronized void start() // Startet einen Thread in dem das Spiel läuft
	{
		running = true;
		new Thread(this, "Game").start();
	}

	/**
	 * Stoppt das Spiel
	 */
	public synchronized void stop() // Stoppt das Spiel
	{
		running = false;
	}

	/**
	 * Ruft tick() und render() 60 mal die Sekunde auf
	 * 
	 * @see tick()
	 * @see render()
	 */
	public void run() // Ruft die Spielelogik 60 mal in der Sekunde auf
	{
		game.loadLevel();
		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000D / 60D;
		int frames = 0;
		int ticks = 0;
		long lastTimer = System.currentTimeMillis();
		double delta = 0;
		boolean shouldRender = false;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / nsPerTick;
			lastTime = now;
			while (delta >= 1) {
				ticks++;
				tick();
				delta -= 1;
				shouldRender = true;
			}
			if (shouldRender) {
				render();
				frames++;
				shouldRender = false;
			}
			if (System.currentTimeMillis() - lastTimer >= 1000) {
				lastTimer += 1000;
				System.out.println(frames + ", " + ticks);
				frames = 0;
				ticks = 0;
			}
		}
	}

	/**
	 * Führt die Logik des Spiels aus
	 */
	public void tick() // Aktualiesiert die Spiellogik
	{
		game.doLogic();
		tickCount++;
	}

	/**
	 * Rendert den Bildschirm
	 */
	public void render() // Aktualiesiert den Spieleanzeigebereich
	{
		game.getScreen().assemble();
		for (int x = 0; x < Main.Width; x++) {
			for (int y = 0; y < Main.Height; y++) {
				pixels[x * Main.Height + y] = game.getScreen().getPixel()[x][y];
			}
		}
		for (int i = healthpixels.length / (100 / game.getPlayer().getHealth()); i < healthpixels.length; i++) {
			healthpixels[i] = Color.WHITE.getRGB();
		}
		for (int i = 0; i < healthpixels.length / (100 / game.getPlayer().getHealth()); i++) {
			healthpixels[i] = Color.RED.getRGB();
		}
		BufferStrategy bs = getBufferStrategy();
		BufferStrategy bshealth = healthbar.getBufferStrategy();
		if (bshealth == null) {
			healthbar.createBufferStrategy(2);
			return;
		}
		if (bs == null) {
			createBufferStrategy(2);
			return;
		}
		Graphics ghealth = bshealth.getDrawGraphics();
		Graphics g = bs.getDrawGraphics();
		while (!g.drawImage(image, 0, 0, getWidth(), getHeight(), null)) {
		}
		while (!ghealth.drawImage(healthimg, 0, 0, 768, 50, null)) {
		}
		ghealth.dispose();
		g.dispose();
		bs.show();
		bshealth.show();
	}

}
