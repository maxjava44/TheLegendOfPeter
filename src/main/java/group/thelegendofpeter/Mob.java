package group.thelegendofpeter;

public class Mob extends Character {
	
	private double distance;
	
	public Mob(Sprite pSprite,int pSpeed,int pHealth,int pAttackDamage,boolean pDead)
	{
		super(pSprite,pSpeed,pHealth,pAttackDamage,pDead);
	}
	
	public double getDistance()
	{
		return distance;
	}
	
	public void setDistance (double pdistance)
	{
		distance = pdistance;
	}
}
