package group.thelegendofpeter;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class SpriteSheet {
	private ArrayList<Sprite> sprites = new ArrayList<Sprite>();

	/**
	 * Lädt das Spritesheet(ein Bild welches alle Sprite-Darstellungen enthält) und
	 * verseht die Sprites mit den richtigen Hitbox Parameter
	 * 
	 * @param path   Der Pfad zum Spritesheet
	 * @param rows   Anzahl der Zeilen
	 * @param cols   Anzahl der Spalten
	 * @param width  Breite eines Einzelbildes
	 * @param height Höhe eines Einzelbildes
	 */
	public SpriteSheet(String path, int rows, int cols, int width, int height) {
		try {
			BufferedReader sizereader = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("spritesize")));
			BufferedImage image = ImageIO.read(this.getClass().getClassLoader().getResource(path));
			for (int i = 0; i < cols; i++) {
				for (int s = 0; s < rows; s++) {
					String info = sizereader.readLine();
					int[] infos = new int[4];
					infos[0] = Integer.parseInt(info.substring(0, 2));
					infos[1] = Integer.parseInt(info.substring(2, 4));
					infos[2] = Integer.parseInt(info.substring(4, 6));
					infos[3] = Integer.parseInt(info.substring(6, 8));
					sprites.add(new Sprite(0, 0, infos[0], infos[1], infos[2], infos[3], image.getSubimage(width * s, height * i, width, height).getRGB(0, 0, width, height, null, 0, width)));
				}
				sizereader.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Lesen des SpriteSheets unmöglich");
		}
	}

	public ArrayList<Sprite> getSpriteList() {
		return sprites;
	}
}
