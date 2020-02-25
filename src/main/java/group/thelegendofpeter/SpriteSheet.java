package group.thelegendofpeter;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class SpriteSheet {
	private ArrayList<Sprite> sprites = new ArrayList<Sprite>();
	public SpriteSheet(String path,int rows,int cols,int width,int height)
	{
		try {
			BufferedReader sizereader = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("spritesize")));
			BufferedImage image = ImageIO.read(this.getClass().getClassLoader().getResource(path));
			for(int i = 0;i<cols;i++)
			{
				for(int s = 0;s<rows;s++)
				{
					String info = sizereader.readLine();
					int[] infos = new int[2];
					infos[0] = Integer.parseInt(info.substring(0, 2));
					infos[1] = Integer.parseInt(info.substring(2, 4));
					sprites.add(new Sprite(0,0,infos[0],infos[1],image.getSubimage(width*s, height*i, width, height).getRGB(0,0,width,height,null,0,width)));
				}
				sizereader.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Lesen des SpriteSheets unmöglich");
		}
	}
	
	public ArrayList<Sprite> getSpriteList()
	{
		return sprites;
	}
}
