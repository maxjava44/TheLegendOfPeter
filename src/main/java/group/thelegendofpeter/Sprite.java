package group.thelegendofpeter;

/**
 * Ein Sprite beinhaltet die Informationen zur Darstellung und für die Hitboxen
 * 
 * @author Maximilian Gilsoul
 *
 */
public class Sprite {
	private int x;
	private int y;
	private int width;
	private int height;
	private int xHitboxOffset;
	private int yHitboxOffset;
	private int[] pixel;

	/**
	 * Erstellt ein Sprite(die grafische Darstellung von etwas mit X/Y Informationen
	 * sowie Hitbox Informationen)
	 * 
	 * @param pX             X-Koordinate des Sprites
	 * @param pY             Y-Koordinate des Sprites
	 * @param pWidth         Breite der Hitbox
	 * @param pHeight        Höhe der Hitbox
	 * @param pxHitboxOffset X-Offset der Hitbox(X-Koordinate wo die Hitbox beginnt)
	 * @param pyHitboxOffset Y-Offset der Hitbox
	 * @param pPixel         Pixel der grafischen Darstellung
	 */
	public Sprite(int pX, int pY, int pWidth, int pHeight, int pxHitboxOffset, int pyHitboxOffset, int[] pPixel) {
		x = pX;
		y = pY;
		width = pWidth;
		height = pHeight;
		xHitboxOffset = pxHitboxOffset;
		yHitboxOffset = pyHitboxOffset;
		pixel = pPixel;
	}

	/**
	 * Gibt die Pixel des Sprites zurück
	 * 
	 * @return die Pixel
	 */
	public int[] getPixel() {
		return pixel;
	}

	/**
	 * Setzt die Pixel
	 * 
	 * @param pPixel die Pixel die zu setzen sind
	 */
	public void setPixel(int[] pPixel) {
		pixel = pPixel;
	}

	/**
	 * Gibt die Höhe der Hitbox zurück
	 * 
	 * @return Die Höhe der Hitbox
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Gibt die Breite der Hitbox zurück
	 * 
	 * @return Die Breite der Hitbox
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Gibt den X-Offset der Hitbox zurück
	 * 
	 * @return Den X-Offset der Hitbox
	 */
	public int getxHitbox() {
		return xHitboxOffset;
	}

	/**
	 * Gibt den Y-Offset der Hitbox zurück
	 * 
	 * @return Den Y-Offset der Hitbox
	 */
	public int getyHitbox() {
		return yHitboxOffset;
	}

	/**
	 * Gibt die X-Koordinate zurück
	 * 
	 * @return Die X-Koordinate
	 */

	public int getX() {
		return x;
	}

	/**
	 * Gibt die Y-Koordinate zurück
	 * 
	 * @return Die Y-Koordinate
	 */
	public int getY() {
		return y;
	}

	/**
	 * Setzt die X-Koordinate
	 */
	public void setX(int pX) {
		x = pX;
	}

	/**
	 * Setzt die Y-Koordinate
	 */
	public void setY(int pY) {
		y = pY;
	}
}
