package group.thelegendofpeter;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import javax.imageio.ImageIO;

public class Screen {

    private int[][] pixel2d = new int[Main.Width][Main.Height];
    ArrayList<Sprite> sprites;
    Sprite background;
    public Screen(){
    	try {
			background = new Sprite(0,0,ImageIO.read(this.getClass().getClassLoader().getResource("background.png")).getRGB(0,0,64,64,null,0,64));
		} catch (IOException e) {
			int[] backgroundalt = new int[64*64];
			for(int i = 0;i<backgroundalt.length;i++)
			{
				backgroundalt[i] = java.awt.Color.RED.getRGB();
			}
			background = new Sprite(0,0,backgroundalt);
		}
    }
    
    public void assemble()
    {
    	for(int x = 0;x<Main.Width;x++)
        {
            for(int y = 0;y<Main.Height;y++)
            {
            	pixel2d[x][y] = new Color(0,0,0,255).getRGB();
            }
        }
    	int xHelp = 0;
    	int yHelp = 0;
    	for(int i = 0;i<12*12;i++)
    	{
    		for(int x = xHelp;x<64+xHelp;x++)
    		{
                for(int y = yHelp;y<64+yHelp;y++)
                {
                	pixel2d[y][x] = background.getPixel()[(y-yHelp) * 64 + (x-xHelp)];
                }
            }
    		xHelp = xHelp + 64;
    		if(xHelp > 704)
    		{
    			xHelp = 0;
    			yHelp = yHelp + 64;
    		}
    	}
    	for(Sprite sprite : sprites)
    	{
    		int yOff = sprite.getY();
    		int xOff = sprite.getX();
    		for(int x = xOff;x<64+xOff;x++)
    		{
                for(int y = yOff;y<64+yOff;y++)
                {
                	pixel2d[y][x] = sprite.getPixel()[(y-yOff) * 64 + (x-xOff)];
                }
            }
    	}
    }
    
    public void setSprites(ArrayList<Sprite> pSprites)
    {
    	sprites = pSprites;
    }
    
    public int[][] getPixel()
    {
    	return pixel2d;
    }
}

