package group.thelegendofpeter;

import java.io.File;
import java.util.Scanner;

public class Screen {

    String path;
    SpriteSheet sheet;
    int[][] pixel2d = new int[Main.Width][Main.Height];
    public Screen(String pPath,SpriteSheet pSheet)
    {
        path = pPath;
        sheet = pSheet;
    }
    
    public void loadLevel(int id)
    {
        try{
            int[] contents = new int[3];
            Scanner sc = new Scanner(this.getClass().getClassLoader().getResourceAsStream(path + id));  
            sc.useDelimiter(",");
            int counter = 0;
            while (sc.hasNext())  //returns a boolean value  
            {  
                contents[counter] = Integer.parseInt(sc.next());
                int xOff = contents[1];
                int yOff = contents[2];
                if(counter == 2)
                {
                    for(int x = xOff;x<128+xOff;x++)
                    {
                        for(int y = yOff;y<128+yOff;y++)
                	{
                		pixel2d[x][y] = sheet.getSpriteList().get(contents[0]).pixel[(x-xOff) * 128 + (y-yOff)];
                	}
                    }
                    counter = 0;
                }
                counter++;
            }   
            sc.close();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public int[] getPixel()
    {
        int[] pixels = new int[Main.Width*Main.Height];
        for(int x= 0;x<Main.Width;x++)
    	{
    		for(int y = 0;y<Main.Height;y++)
    		{
    			pixels[x * Main.Height + y] = pixel2d[x][y];
    		}
    	}
        return pixels;
    }
}
