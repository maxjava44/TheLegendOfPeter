package group.thelegendofpeter;

public class Sprite {
	public int x1; 
	public int y1;
	public int x2;
	public int y2;
	public int[] pixel;
	
	public Sprite(int px1,int py1,int px2,int py2,int[] pPixel)
	{
		x1 = px1;
		y1 = py1;
		x2 = px2;
		y2 = py2;
		pixel = pPixel;
	}
}
