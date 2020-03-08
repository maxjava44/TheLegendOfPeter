package group.thelegendofpeter;

/**
 * Stellt ein Charakter da
 * @author Maximilian Gilsoul
 * @author Tristan Michels
 */
public class Character {
	private Sprite sprite;
	private int speed;
	private int health;
	private int attackDamage;
	private boolean dead;

	/**
	 * Erstellt ein Character-Objekt mit den gegebenen Daten
	 * 
	 * @param pSprite       Der Sprite des Characters
	 * @param pSpeed        Die Geschwindigkeit des Characters
	 * @param pHealth       Die Lebenspunkte des Characters
	 * @param pAttackDamage Der Angriffsschaden des Characters
	 * @param pDead         Legt fest ob der Character tod ist
	 */
	public Character(Sprite pSprite, int pSpeed, int pHealth, int pAttackDamage, boolean pDead) {
		sprite = pSprite;
		speed = pSpeed;
		health = pHealth;
		attackDamage = pAttackDamage;
		dead = pDead;
	}

	/**
	 * Gibt den Speed zurück
	 * 
	 * @return Den Speed
	 */
	public int getSpeed() {
		return speed;
	}

	/**
	 * Setzt den Sprite
	 * 
	 * @param pSprite Den Sprite
	 */
	public void setSprite(Sprite pSprite) {
		sprite = pSprite;
	}

	/**
	 * Gibt den Sprite zurück
	 * 
	 * @return Den Sprite
	 */
	public Sprite getSprite() {
		return sprite;
	}

	/**
	 * Setzt die Gesundheit des Characters
	 * 
	 * @param pHealth Die Gesundheit
	 */
	public void setHealth(int pHealth) {
		health = pHealth;
	}

	/**
	 * Gibt die Gesundheit des Characters zurück
	 * 
	 * @return Die Gesundheit
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * Gibt den Angriffsschaden zurück
	 * 
	 * @return Den Angriffsschaden
	 */
	public int getAttackDamage() {
		return attackDamage;
	}

	/**
	 * Gibt zurück ob der Charakter tod ist
	 * 
	 * @return Ist tod?
	 */
	public boolean getDead() {
		return dead;
	}

	/**
	 * Prüft ob der Character tod ist
	 * 
	 * @return boolean Tod oder Nicht
	 */
	public boolean isDead() {
		if (health == 0) {
			dead = true;
		}
		if (dead) {
			System.out.println("tot");
		}
		return dead;
	}

}
