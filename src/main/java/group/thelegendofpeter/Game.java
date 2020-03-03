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
	private CopyOnWriteArrayList<Mob> mobs = new CopyOnWriteArrayList<Mob>();
	private CopyOnWriteArrayList<Sprite> sprites = new CopyOnWriteArrayList<Sprite>();
	SpriteSheet sheet;
	Screen screen = new Screen();
	int levelid = 11;
	
	public Game(SpriteSheet pSheet)
	{
		sheet = pSheet;
		Sound sound = new Sound("Sound",this.getClass().getClassLoader().getResourceAsStream("sound/3386-asian-drums-by-kevin-macleod.wav"));
		sound.start();
		sound.playing = true;
	}
	
	public Screen getScreen()
	{
		screen.setSprites(sprites);
		return screen;
	}
	
	public void doLogic()
	{
            for(Mob mob : mobs)
            {
            	int dx = player.getSprite().getX() - mob.getSprite().getX();
            	int dy = player.getSprite().getY() - mob.getSprite().getY();
            	int prevX = mob.getSprite().getX();
            	int prevY = mob.getSprite().getY();
            	double distance = Math.sqrt((dx*dx)+(dy*dy));
            	mob.setDistance(distance);
            	if(dy < 0) {
            		mob.getSprite().setY(mob.getSprite().getY()-mob.getSpeed());
            		if(collision(mob.getSprite()))
                	{
                		mob.getSprite().setY(prevY);
                	}
            	}
            	else if(dy > 0) {
            		mob.getSprite().setY(mob.getSprite().getY()+mob.getSpeed());
            		if(collision(mob.getSprite()))
                	{
                		mob.getSprite().setY(prevY);
                	}
            	}
            	if(dx < 0) {
            		mob.getSprite().setX(mob.getSprite().getX()-mob.getSpeed());
            		if(collision(mob.getSprite()))
                	{
                		mob.getSprite().setX(prevX);
                	}
            	}
                else if(dx > 0) {
            		mob.getSprite().setX(mob.getSprite().getX()+mob.getSpeed());
            		if(collision(mob.getSprite()))
                	{
                		mob.getSprite().setX(prevX);
                	}
            	}
            	if(distance < 60)
            	{
            		//player.setHealth(player.getHealth() -mob.getAttackDamage());
            	}
            	System.out.println(distance);
            	if (mob.isDead())
            	{
            		mobs.remove(mob);
            		sprites.remove(mob.getSprite());
            	}
            }
            player.isDead();
	}
	
	public boolean figureLevelOut()
	{
		if(levelid == 22)
		{
			return false;
		}
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
        mobs.clear();
    	sprites.clear();
        try{
        	screen.setBackground(new Sprite(0,0,0,0,0,0,ImageIO.read(this.getClass().getClassLoader().getResource(levelid+".png")).getRGB(0,0,768,768,null,0,768)));
            BufferedReader levelfile = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("level" + levelid)));
            BufferedReader mobfile = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("mob" + levelid)));
            int[] infos = new int[3];
            int counter = 0;
            String content;
            while((content = levelfile.readLine())!= null)
            {
            	infos[counter] = Integer.parseInt(content);
            	counter++;
            	if(counter == 3)
            	{
            		Sprite sprite = new Sprite(infos[1],infos[2],sheet.getSpriteList().get(infos[0]).getWidth(),sheet.getSpriteList().get(infos[0]).getHeight(),sheet.getSpriteList().get(infos[0]).getxHitbox(),sheet.getSpriteList().get(infos[0]).getyHitbox(),sheet.getSpriteList().get(infos[0]).getPixel());
            		sprites.add(sprite);
            		counter = 0;
            	}
            }
            int[] mobinfos = new int[7];
            int mobcounter = 0;
            String mobcontent;
            while((mobcontent = mobfile.readLine())!= null)
            {
            	mobinfos[mobcounter] = Integer.parseInt(mobcontent);
            	mobcounter++;
            	if(mobcounter == mobinfos.length-1)
            	{
            		Mob mob = new Mob(new Sprite(mobinfos[1],mobinfos[2],sheet.getSpriteList().get(mobinfos[0]).getWidth(),sheet.getSpriteList().get(mobinfos[0]).getHeight(),sheet.getSpriteList().get(mobinfos[0]).getxHitbox(),sheet.getSpriteList().get(mobinfos[0]).getyHitbox(),sheet.getSpriteList().get(mobinfos[0]).getPixel()),mobinfos[3],mobinfos[4],mobinfos[5],false);
            		mobs.add(mob);
            		mobcounter = 0;
            	}
            }
            for(Mob mob : mobs)
            {
                sprites.add(mob.getSprite());
            }
            mobfile.close();
            levelfile.close();
            player = new Player(sprites.get(0),10,100,10,false);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
	
	 public boolean collision(Sprite sprite)
	    {
	    	Rectangle playerrec = new Rectangle(sprite.getX()+sprite.getxHitbox(),sprite.getY()+sprite.getyHitbox(),sprite.getWidth(),sprite.getHeight());
	    	for(int i = 0;i<sprites.size();i++)
	    	{
	    		Rectangle spriterec = new Rectangle(sprites.get(i).getX()+sprites.get(i).getxHitbox(),sprites.get(i).getY()+sprites.get(i).getyHitbox(),sprites.get(i).getWidth(),sprites.get(i).getHeight());
	    		if(playerrec.intersects(spriterec) && !playerrec.equals(spriterec))
	    		{
	    			return true;
	    		}
	    	}
	    	return false;
	    }

		@Override
		public void keyPressed(KeyEvent e) {
			if(!(sprites.isEmpty()))
			{
				int prevX = sprites.get(0).getX();
				int prevY = sprites.get(0).getY();
				switch(e.getKeyChar())
				{               
				case 'w':player.getSprite().setY(player.getSprite().getY()-player.getSpeed());
				//player.setHealth(player.getHealth() -10);
				break;
				case 's':player.getSprite().setY(player.getSprite().getY()+player.getSpeed());
				break;
				case 'a':player.getSprite().setX(player.getSprite().getX()-player.getSpeed());
				break;
				case 'd':player.getSprite().setX(player.getSprite().getX()+player.getSpeed());      
				break;
				default:break;         
				}
				if(e.getKeyChar() == 'l')
				{
					for(Mob mob : mobs)
					{
						if(mob.getDistance() < 80.0)
						{
							mob.setHealth(mob.getHealth() -player.getAttackDamage());
						}
					}
				}
				if(collision(player.getSprite()))
				{
					player.getSprite().setX(prevX);
					player.getSprite().setY(prevY);
				}
				if(!figureLevelOut() && (player.getSprite().getX() < 0 || player.getSprite().getX() > 704 || player.getSprite().getY() < 0 || player.getSprite().getY() > 704))
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
        
        public Player getPlayer()
        {
            return player;
        }
}

