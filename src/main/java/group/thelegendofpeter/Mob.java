package group.thelegendofpeter;

public class Mob extends Character {
	
	public Mob(Sprite pSprite,int pSpeed,int pHealth,int pMaxHealth,int pAttackDamage,boolean pDead)
	{
		super(pSprite,pSpeed, pHealth, pMaxHealth,pAttackDamage, pDead);
	}
	
	private int health = 50;

	
}
