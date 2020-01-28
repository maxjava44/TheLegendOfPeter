package group.thelegendofpeter;

public class Character {
	private Sprite sprite;
	private int speed;
	
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
}
