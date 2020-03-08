package group.thelegendofpeter;

/**
 * Ein Sprite beinhaltet die Informationen zur Darstellung und für die Hitboxen
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

	public int[] getPixel() {
		return pixel;
	}

	public void setPixel(int[] pPixel) {
		pixel = pPixel;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public int getxHitbox() {
		return xHitboxOffset;
	}

	public int getyHitbox() {
		return yHitboxOffset;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int pX) {
		x = pX;
	}

	public void setY(int pY) {
		y = pY;
	}
}
