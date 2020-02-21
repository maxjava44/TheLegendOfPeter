package group.thelegendofpeter;

public class Character {
	private Sprite sprite;
	private int speed;
	private int health = 100;
	
	public Character(Sprite pSprite,int pSpeed)
	{
		sprite = pSprite;
		speed = pSpeed;
	}
	
	public int getSpeed()
	{
		return speed;
	}
	
	public Sprite getSprite()
	{
		return sprite;
	}
	
        public int getHealth()
        {
            return health;
        }
	
	// helathpoints
	// damage 
	// armor
	
}
