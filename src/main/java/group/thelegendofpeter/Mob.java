package group.thelegendofpeter;

/**
 * Stellt ein Mob da
 * @author Maximilian Gilsoul
 *
 */
public class Mob extends Character {

	private double distance;

	/**
	 * Erstellt ein Mob-Objekt mit den gegebenen Daten
	 * 
	 * @param pSprite       Der Sprite des Mobs
	 * @param pSpeed        Die Geschwindigkeit des Mobs
	 * @param pHealth       Die Lebenspunkte des Mobs
	 * @param pAttackDamage Der Angriffsschaden des Mobs
	 * @param pDead         Legt fest ob der Mob tod ist
	 */
	public Mob(Sprite pSprite, int pSpeed, int pHealth, int pAttackDamage, boolean pDead) {
		super(pSprite, pSpeed, pHealth, pAttackDamage, pDead);
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double pdistance) {
		distance = pdistance;
	}
}
