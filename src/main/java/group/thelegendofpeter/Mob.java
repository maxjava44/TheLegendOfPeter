package group.thelegendofpeter;

public class Mob extends Character {
	
	public Mob(Sprite pSprite,int pSpeed,int pHealth,int pMaxHealth,boolean pDead)
	{
		super(pSprite,pSpeed, pSpeed, pHealth, pMaxHealth, pDead);
	}
	
	private int health = 50;

	
}
