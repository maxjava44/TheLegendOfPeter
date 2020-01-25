package group.thelegendofpeter;

public class Sprite {
	private int x; 
	private int y;
	private int[] pixel;
	
	public Sprite(int pX,int pY,int[] pPixel)
	{
		x = pX;
		y = pY;
		pixel = pPixel;
	}
	
	public int[] getPixel() {
		return pixel;
	}
	
	public void setPixel(int[] pPixel) {
		pixel = pPixel;
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
