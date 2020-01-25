package group.thelegendofpeter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class Screen {

    String path;
    SpriteSheet sheet;
    int[][] pixel2d = new int[Main.Width][Main.Height];
    ArrayList<Sprite> sprites = new ArrayList<Sprite>();
    public Screen(String pPath,SpriteSheet pSheet)
    {
        path = pPath;
        sheet = pSheet;
    }
    
    public void loadLevel(int id)
    {
    	sprites.clear();
        try{
            BufferedReader levelfile = new BufferedReader(new FileReader(new File(this.getClass().getClassLoader().getResource(path + id).toURI())));
            int[] infos = new int[3];
            int counter = 0;
            String content;
            while((content = levelfile.readLine())!= null)
            {
            	infos[counter] = Integer.parseInt(content);
            	counter++;
            	if(counter == 3)
            	{
            		Sprite sprite = new Sprite(infos[1],infos[2],sheet.getSpriteList().get(infos[0]).getPixel());
            		sprites.add(sprite);
            		counter = 0;
            	}
            }
            levelfile.close();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public void assemble()
    {
    	for(Sprite sprite : sprites)
    	{
    		int xOff = sprite.getX();
    		int yOff = sprite.getY();
    		for(int x = xOff;x<128+xOff;x++)
            {
                for(int y = yOff;y<128+yOff;y++)
                {
                	pixel2d[x][y] = sprite.getPixel()[(x-xOff) * 128 + (y-yOff)];
                }
            }
    	}
    }
    
    public int[][] getPixel()
    {
    	return pixel2d;
    }
    
}

