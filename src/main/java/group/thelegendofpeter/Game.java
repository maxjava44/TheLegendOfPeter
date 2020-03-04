package group.thelegendofpeter;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import java.util.concurrent.CopyOnWriteArrayList;

public class Game implements KeyListener {
	private Player player = new Player(null,10,100,10,false);
	private CopyOnWriteArrayList<Mob> mobs = new CopyOnWriteArrayList<Mob>();
	private CopyOnWriteArrayList<Sprite> sprites = new CopyOnWriteArrayList<Sprite>();
	SpriteSheet sheet;
	Screen screen = new Screen();
	int levelid = 11;
	long timediff = 1500;
	long attackedtime;
	long playertimediff = 1500;
	long playerattackedtime;
	Sound attacksound = new Sound("AttackSound",this.getClass().getClassLoader().getResourceAsStream("sound/Windows Ding.wav"));
	
	 /**
         * Initialisiert ein Onjekt der klasse game und startet den Sound 
         * @param pSheet 
         */
	public Game(SpriteSheet pSheet)
	{
		attacksound.start();
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
	
	 /**
         * Die For-Schleife sorgt dafür, dass die folgenden Methoden für jedes einzelne MobObjekt in der ArrayList ausgeführt werden
         * Die Methode berechnet mithilfe von Pythagoras die Distanz zwischen einem Mob und dem Player 
         * Wenn ein Mob links, rechts, unten oder oben vom Player aus steht, bewegt sich der Mob auf den Player zu
         * Wenn sich die Hitboxen vom Player und einem Mob berühren wird der Mob auf seine vorherige Position zurückgesetzt  
         * Solange der Abstand zwischen dem Player und einem Mob einen bestimmten Wert unterschreitet, verliert der Player Leben gleich des Schadens den ein Mob verursacht
         * Wenn ein Mob stirbt, wird das Objekt des Mobs aus der MobArrayList entfernt, sowie sein Sprite aus der SpriteArrayList
         * 
         * Die Methode überprüft ob der Player gestorben ist
         */
	public void doLogic()
	{
		    long milliseconds = System.currentTimeMillis(); 
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
            	if(distance < 60 && timediff >= 1500)
            	{
            		player.setHealth(player.getHealth() -mob.getAttackDamage());
            		attackedtime = System.currentTimeMillis();
            		timediff = 0;
            	}
            	System.out.println(distance);
            	if (mob.isDead())
            	{
            		mobs.remove(mob);
            		sprites.remove(mob.getSprite());
            	}
            }
            player.isDead();
            timediff = milliseconds - attackedtime;
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
            player.setSprite(sprites.get(0));
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
			long milliseconds = System.currentTimeMillis();
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
				if(e.getKeyChar() != 'l') //stoppt den attacksound wenn der spieler weiter läuft
				{
                                     attacksound.playing = false;
                                }        
				
				if(e.getKeyChar() == 'l') //startet den attacksound wenn der spieler "l" drückt
				{
					 attacksound.playing = true;
					for(Mob mob : mobs)
					{
						if(mob.getDistance() < 80.0 && playertimediff >= 1500)
						{
							mob.setHealth(mob.getHealth() -player.getAttackDamage());
							playerattackedtime = System.currentTimeMillis();
		            		playertimediff = 0;
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
			playertimediff = milliseconds - playerattackedtime;
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

