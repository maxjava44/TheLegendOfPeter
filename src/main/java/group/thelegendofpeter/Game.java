
package group.thelegendofpeter;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class Game implements KeyListener {
	private Player player;
	private ArrayList<Mob> mobs = new ArrayList<Mob>();
	private CopyOnWriteArrayList<Sprite> sprites = new CopyOnWriteArrayList<Sprite>();
	SpriteSheet sheet;
	Screen screen = new Screen();
	int levelid = 11;
	
	public Game(SpriteSheet pSheet)
	{
		sheet = pSheet;
	}
	
	public Screen getScreen()
	{
		screen.setSprites(sprites);
		return screen;
	}
	
	public void doLogic()
	{
	}
	
	public boolean figureLevelOut()
	{
		if(player.getSprite().getX()>=704 && levelid != 13 && levelid != 23 && levelid != 33)
		{
			levelid++;
			loadLevel();
			return true;
		}else if(player.getSprite().getX()<=0 && levelid != 11 && levelid != 21 && levelid != 31)
		{
			levelid--;
			loadLevel();
			return true;
		}else if(player.getSprite().getY()>=704 && levelid != 31 && levelid != 32 && levelid != 33)
		{
			levelid = levelid + 10;
			loadLevel();
			return true;
		}else if(player.getSprite().getY()<=0 && levelid != 11 && levelid != 12 && levelid != 13)
		{
			levelid = levelid - 10;
			loadLevel();
			return true;
		}
		return false;
	}
	
	public void loadLevel()
    {
    	sprites.clear();
        try{
            BufferedReader levelfile = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("level" + levelid)));
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
            player = new Player(sprites.get(0),10);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
	
	 public boolean collision()
	    {
	    	Rectangle playerrec = new Rectangle(sprites.get(0).getX(),sprites.get(0).getY(),64,64);
	    	for(int i = 1;i<sprites.size();i++)
	    	{
	    		Rectangle sprite = new Rectangle(sprites.get(i).getX(),sprites.get(i).getY(),64,64);
	    		if(playerrec.intersects(sprite))
	    		{
	    			return true;
	    		}
	    	}
	    	return false;
	    }

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			int prevX = sprites.get(0).getX();
			int prevY = sprites.get(0).getY();
			if(!(sprites.isEmpty()))
			{
				switch(e.getKeyChar())
				{
				case 'w':player.getSprite().setY(player.getSprite().getY()-player.getSpeed());
				break;
				case 's':player.getSprite().setY(player.getSprite().getY()+player.getSpeed());
				break;
				case 'a':player.getSprite().setX(player.getSprite().getX()-player.getSpeed());
				break;
				case 'd':player.getSprite().setX(player.getSprite().getX()+player.getSpeed());
				break;
				}
			}
			if(collision())
			{
				player.getSprite().setX(prevX);
				player.getSprite().setY(prevY);
			}
			if(player.getSprite().getX() < 0 || player.getSprite().getX() > 704 || player.getSprite().getY() < 0 || player.getSprite().getY() > 704)
	    	{
				if(!figureLevelOut())
				{
					player.getSprite().setX(prevX);
					player.getSprite().setY(prevY);
				}
	    	}
		}
	@Override
	public void keyReleased(KeyEvent e) {
	}
	@Override
	public void keyTyped(KeyEvent e) {
	}
}
