package group.thelegendofpeter;

import java.awt.Color;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.imageio.ImageIO;

public class Screen {

    private int[][] pixel2d = new int[Main.Width][Main.Height];
    CopyOnWriteArrayList<Sprite> sprites = new CopyOnWriteArrayList<Sprite>();
    CopyOnWriteArrayList<Sprite> mobsprites = new CopyOnWriteArrayList<Sprite>();
    Sprite background;

    public void setBackground(Sprite pBackground)
    {
    	background = pBackground;
    }
    
    public void assemble()
    {
		for(int x = 0;x<768;x++)
		{
            for(int y = 0;y<768;y++)
            {
            	pixel2d[y][x] = background.getPixel()[y * 768 + x];
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
    	            int a = c.getAlpha();
    	            if (a == 0) {
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
