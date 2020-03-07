package group.thelegendofpeter;

public class Character {
	private Sprite sprite;
	private int speed;
	private int health;
    private int attackDamage;
    private boolean dead;
	/**
	 * Erstellt ein Character-Objekt mit den gegebenen Daten
	 * @param pSprite Der Sprite des Characters 
	 * @param pSpeed Die Geschwindigkeit des Characters
	 * @param pHealth Die Lebenspunkte des Characters
	 * @param pAttackDamage Der Angriffsschaden des Characters
	 * @param pDead Legt fest ob der Character tod ist 
	 */
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
	/**
	 * Prüft ob der Character tod ist
	 * @return boolean Tod oder Nicht
	 */
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
