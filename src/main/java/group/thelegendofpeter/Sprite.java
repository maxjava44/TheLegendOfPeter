ackage group.thelegendofpeter;

public class Sprite {
	private int x; 
	private int y;
	private int width;
	private int height;
	private int xHitboxOffset;
	private int yHitboxOffset;
	private int[] pixel;
	
	public Sprite(int pX,int pY,int pWidth,int pHeight,int pxHitboxOffset,int pyHitboxOffset,int[] pPixel)
	{
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
