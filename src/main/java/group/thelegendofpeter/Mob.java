package group.thelegendofpeter;

public class Mob extends Character {
	
	public Mob(Sprite pSprite,int pSpeed)
	{
		super(pSprite,pSpeed);
	}
	
	private int health = 50;

	
}

/*
Was von diesem Code soll man dann überhaupt übernehmen?
Ist es nicht leichter die HP hier an den Mob zu binden?

public class Mob extends Character {
	
	public Mob(Sprite pSprite,int pSpeed)
	{
		super(pSprite,pSpeed);
	}
	}

	xPosition = (int)("Zahl" * Math.random());
        yPosition = (int)("Zahl * Math.random());	
	
	
    private int health;
    public Mob(int health) {
        this.health = health;
    }

    public void hit(int damage) {
        health -= damage;
    }

    public int getHealth() {
        return health;
    }
    
    
    
    */
