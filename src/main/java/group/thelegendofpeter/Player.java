package group.thelegendofpeter;

/**
 * Stellt ein Player da
 * @author Maximilian Gilsoul
 *
 */
public class Player extends Character {
	/**
	 * Erstellt ein Player-Objekt mit den gegebenen Daten
	 * 
	 * @param pSprite       Der Sprite des Players
	 * @param pSpeed        Die Geschwindigkeit des Players
	 * @param pHealth       Die Lebenspunkte des Players
	 * @param pAttackDamage Der Angriffsschaden des Players
	 * @param pDead         Legt fest ob der Player tod ist
	 */
	public Player(Sprite pSprite, int pSpeed, int pHealth, int pAttackDamage, boolean pDead) {
		super(pSprite, pSpeed, pHealth, pAttackDamage, pDead);
	}
}
