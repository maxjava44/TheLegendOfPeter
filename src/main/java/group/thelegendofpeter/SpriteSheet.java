package group.thelegendofpeter;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class SpriteSheet {
	private BufferedImage image;
	private ArrayList<Sprite> sprites = new ArrayList<Sprite>();
	private String path;
	private int rows;
	private int cols;
	private int width;
	private int height;
	public SpriteSheet(String pPath,int pRows,int pCols,int pWidth,int pHeight)
	{
		try {
			image = ImageIO.read(new File(pPath));
		} catch (IOException e) {
			System.out.println("Lesen des SpriteSheets unmöglich");
		}
		rows = pRows;
		cols = pCols;
		width = pWidth;
		height = pHeight;
		int[][] positions = new int[rows*cols][4]; 
		for(int i = 0;i<cols;i++)
		{
			for(int s = 0;s<rows;s++)
			{
				sprites.add(new Sprite(positions[i*s][0],positions[i*s][1],positions[i*s][2],positions[i*s][3],image.getSubimage(width*cols, height*rows, width, height).getRGB(0,0,width,height,null,0,width)));
			}
		}
	}
	
	public ArrayList<Sprite> getSpriteList()
	{
		return sprites;
	}
}
