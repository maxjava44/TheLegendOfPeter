package group.thelegendofpeter;

public class Character {
	private Sprite sprite;
	private int speed;
	private int health;
        private int attackDamage;
        private boolean dead;
	
	public Character(Sprite pSprite,int pSpeed,int pHealth,int pAttackDamage,boolean pDead)
	{
		sprite = pSprite;
		speed = pSpeed;
		health = pHealth;
                attackDamage = pAttackDamage;
                dead = pDead;
	}
	
	public int getSpeed()
	{
		return speed;
	}
	
	public void setSprite(Sprite pSprite)
	{
		sprite = pSprite;
	}
	
	public Sprite getSprite()
	{
		return sprite;
	}
	
	public void setHealth(int pHealth)
    {
        health = pHealth;
    }
	
        public int getHealth()
        {
            return health;
        }
        
        public int getAttackDamage()
        {
            return attackDamage;
        }
        
        public boolean getDead()
        {
            return dead;
        }
	
	public boolean isDead()
        {
            if (health == 0){
                dead = true;
            }
            if (dead == true){
                System.out.println("tot");
            }
            return dead;
        }
		
}
