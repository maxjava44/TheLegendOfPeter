package group.thelegendofpeter;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.imageio.ImageIO;

public class Screen {

    private int[][] pixel2d = new int[Main.Width][Main.Height];
    CopyOnWriteArrayList<Sprite> sprites = new CopyOnWriteArrayList<Sprite>();
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
    	int[][] pixel2dbefore = pixel2d;
    	for(Sprite sprite : sprites)
    	{
    		int yOff = sprite.getY();
    		int xOff = sprite.getX();
    		for(int x = xOff;x<64+xOff;x++)
    		{
                for(int y = yOff;y<64+yOff;y++)
                {
                	Color c = new Color(sprite.getPixel()[(y-yOff) * 64 + (x-xOff)],true);
    	            int r = c.getRed();
    	            int g = c.getGreen();
    	            int b = c.getBlue();
    	            int a = c.getAlpha();
    	            if(r > 200 && g > 200 && b > 200)
    	            {
    	            	pixel2d[y][x] = pixel2dbefore[y][x];
    	            }
    	            else if (a == 0) {
    	                pixel2d[y][x] = pixel2dbefore[y][x]; 
    	            }
                	else
                	{
                		pixel2d[y][x] = sprite.getPixel()[(y-yOff) * 64 + (x-xOff)];
                	}
                }
            }
    	}
    }
    
    public void setSprites(CopyOnWriteArrayList<Sprite> pSprites)
    {
    	sprites = pSprites;
    }
    
    public int[][] getPixel()
    {
    	return pixel2d;
    }
}
