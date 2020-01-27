package group.thelegendofpeter;

import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class Screen implements java.awt.event.KeyListener {

    private String path;
    private SpriteSheet sheet;
    private int[][] pixel2d = new int[Main.Width][Main.Height];
    public ArrayList<Sprite> sprites = new ArrayList<Sprite>();
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
    
    public int[][] getPixel()
    {
    	return pixel2d;
    }

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(!(sprites.isEmpty()))
		{
			switch(e.getKeyChar())
			{
			case 'w':sprites.get(0).setY(sprites.get(0).getY()-10);
			break;
			case 's':sprites.get(0).setY(sprites.get(0).getY()+10);
			break;
			case 'a':sprites.get(0).setX(sprites.get(0).getX()-10);
			break;
			case 'd':sprites.get(0).setX(sprites.get(0).getX()+10);
			break;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
    
}

