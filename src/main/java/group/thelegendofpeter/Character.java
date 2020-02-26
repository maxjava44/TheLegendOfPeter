package group.thelegendofpeter;

public class Character {
	private Sprite sprite;
	private int speed;
	private int health;
        private int maxHealth = 100;
        private int attackDamage;
        private boolean dead;
	
	public Character(Sprite pSprite,int pSpeed,int pHealth,int pMaxHealth,int pAttackDamage,boolean pDead)
	{
		sprite = pSprite;
		speed = pSpeed;
		health = pHealth;
                maxHealth = pMaxHealth;
                attackDamage = pAttackDamage;
                dead = pDead;
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
	
	public int getMaxHealth()
        {
            return maxHealth;
        }	
        
        public int getAttackDamage()
        {
            return attackDamage;
        }
        
        public boolean getDead()
        {
            return dead;
        }
	
	public void sterben()
        {
            if (health == 0){
                dead = true;
            }
            if (dead == true){
                System.out.println("tot");
            }
        }
		
}
