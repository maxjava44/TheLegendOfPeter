package group.thelegendofpeter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
            BufferedReader levelfile = new BufferedReader(new FileReader(new File(this.getClass().getClassLoader().getResource(path + id).toURI())));
            int xOff = 0;
            int yOff = 0;
            int[] infos = new int[3];
            int counter = 0;
            String content;
            while((content = levelfile.readLine())!= null)
            {
            	infos[counter] = Integer.parseInt(content);
            	counter++;
            	if(counter == 3)
            	{
            		xOff = infos[1];
            		yOff = infos[2];
            		for(int x = xOff;x<128+xOff;x++)
                    {
                        for(int y = yOff;y<128+yOff;y++)
                        {
                        	pixel2d[x][y] = sheet.getSpriteList().get(infos[0]).pixel[(x-xOff) * 128 + (y-yOff)];
                        }
                    }
            		counter = 0;
            	}
            }
            levelfile.close();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public int[][] getPixel()
    {
        return pixel2d;
    }
}

