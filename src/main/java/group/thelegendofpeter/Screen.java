package group.thelegendofpeter;

import java.util.ArrayList;

public class Screen {

    private int[][] pixel2d = new int[Main.Width][Main.Height];
    ArrayList<Sprite> sprites;
    public void assemble()
    {
    	for(int x = 0;x<Main.Width;x++)
        {
            for(int y = 0;y<Main.Height;y++)
            {
            	pixel2d[x][y] = 0;
            }
        }
    	for(Sprite sprite : sprites)
    	{
    		int yOff = sprite.getY();
    		int xOff = sprite.getX();
    		for(int x = xOff;x<128+xOff;x++)
    		{
                for(int y = yOff;y<128+yOff;y++)
                {
                	pixel2d[y][x] = sprite.getPixel()[(y-yOff) * 128 + (x-xOff)];
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

