package group.thelegendofpeter;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class SpriteSheet {
	private BufferedImage image;
	private ArrayList<Sprite> sprites = new ArrayList<Sprite>();
	public SpriteSheet(String path,int rows,int cols,int width,int height)
	{
            
		try {
			image = ImageIO.read(this.getClass().getClassLoader().getResource(path));
		} catch (IOException e) {
			System.out.println("Lesen des SpriteSheets unmöglich");
		}
		for(int i = 0;i<cols;i++)
		{
			for(int s = 0;s<rows;s++)
			{
				sprites.add(new Sprite(0,0,image.getSubimage(width*s, height*i, width, height).getRGB(0,0,width,height,null,0,width)));
			}
		}
	}
	
	public ArrayList<Sprite> getSpriteList()
	{
		return sprites;
	}
}
