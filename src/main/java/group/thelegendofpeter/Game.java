package group.thelegendofpeter;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class Game implements KeyListener {
	private Player player;
	private ArrayList<Mob> mobs = new ArrayList<Mob>();
	private ArrayList<Sprite> sprites = new ArrayList<Sprite>();
	SpriteSheet sheet;
	Screen screen = new Screen();
	
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
	
	public void loadLevel(int id)
    {
    	sprites.clear();
        try{
            BufferedReader levelfile = new BufferedReader(new FileReader(new File(this.getClass().getClassLoader().getResource("level" + id).toURI())));
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
	    	Rectangle playerrec = new Rectangle(sprites.get(0).getX(),sprites.get(0).getY(),128,128);
	    	Rectangle screen = new Rectangle(0,0,Main.Width-128,Main.Height-128);
	    	if(!playerrec.intersects(screen) || player.getSprite().getY() < 0 || player.getSprite().getX() < 0)
	    	{
	    		return true;
	    	}
	    	for(int i = 1;i<sprites.size();i++)
	    	{
	    		Rectangle sprite = new Rectangle(sprites.get(i).getX(),sprites.get(i).getY(),128,128);
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
			System.out.println("X:"+player.getSprite().getX()+" Y:"+player.getSprite().getY());
		}
	@Override
	public void keyReleased(KeyEvent e) {
	}
	@Override
	public void keyTyped(KeyEvent e) {
	}
}
