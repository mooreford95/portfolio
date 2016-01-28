/**
 * 
 */
package character;

/**
 * Stats of a character - NPC, Player or Enemy
 * 
 * @author Curtis
 *
 */
public class CharacterStats {

	/**
	 * Common traits. Most are pretty standard RPG traits, some are custom
	 */
	private int dexterity;
	private int cunning;
	private int constitution;
	private int wisdom;
	private int magic;
	private int strength;
	private int charisma;
	private int trapDisarm;
	private int smithing;

	/**
	 * Constructs class.
	 * 
	 */
	public CharacterStats(int dexterity, int cunning, int constitution,
			int wisdom, int magic, int strength, int charisma, int trapDisarm,
			int smithing) {
		this.dexterity = dexterity;
		this.cunning = cunning;
		this.constitution = constitution;
		this.wisdom = wisdom;
		this.magic = magic;
		this.strength = strength;
		this.charisma = charisma;
		this.trapDisarm = trapDisarm;
		this.smithing = smithing;
	}

	public void setDexterity(int dexterity) {
		this.dexterity = dexterity;
	}

	/**
	 * @return the cunning
	 */
	public int getCunning() {
		return cunning;
	}

	/**
	 * @param cunning
	 *            the cunning to set
	 */
	public void setCunning(int cunning) {
		this.cunning = cunning;
	}

	/**
	 * @return the constitution
	 */
	public int getConstitution() {
		return constitution;
	}

	/**
	 * @param constitution
	 *            the constitution to set
	 */
	public void setConstitution(int constitution) {
		this.constitution = constitution;
	}

	/**
	 * @return the wisdom
	 */
	public int getWisdom() {
		return wisdom;
	}

	/**
	 * @param wisdom
	 *            the wisdom to set
	 */
	public void setWisdom(int wisdom) {
		this.wisdom = wisdom;
	}

	/**
	 * @return the magic
	 */
	public int getMagic() {
		return magic;
	}

	/**
	 * @param magic
	 *            the magic to set
	 */
	public void setMagic(int magic) {
		this.magic = magic;
	}

	/**
	 * @return the strength
	 */
	public int getStrength() {
		return strength;
	}

	/**
	 * @param strength
	 *            the strength to set
	 */
	public void setStrength(int strength) {
		this.strength = strength;
	}

	/**
	 * @return the charisma
	 */
	public int getCharisma() {
		return charisma;
	}

	/**
	 * @param charisma
	 *            the charisma to set
	 */
	public void setCharisma(int charisma) {
		this.charisma = charisma;
	}

	/**
	 * @return the trapDisarm
	 */
	public int getTrapDisarm() {
		return trapDisarm;
	}

	/**
	 * @param trapDisarm
	 *            the trapDisarm to set
	 */
	public void setTrapDisarm(int trapDisarm) {
		this.trapDisarm = trapDisarm;
	}

	/**
	 * @return the smithing
	 */
	public int getSmithing() {
		return smithing;
	}

	/**
	 * @param smithing
	 *            the smithing to set
	 */
	public void setSmithing(int smithing) {
		this.smithing = smithing;
	}

	/**
	 * @return the dexterity
	 */
	public int getDexterity() {
		return dexterity;
	}

	public void printTable() {
		System.out.printf("%-15s %5d %-15s %5d \n", "Dexterity:", dexterity, "Charisma:",
				charisma);
		System.out.printf("%-15s %5d %-15s %5d \n", "Cunning:", cunning, "Constitution:",
				constitution);
		System.out.printf("%-15s %5d %-15s %5d \n", "Wisdom:", wisdom, "Strength:", strength);
		if (magic > 0) {
			System.out.println("Magic:              " + magic);
		}
		if (trapDisarm > 0) {
			System.out.println("Trap Disarming:     " + trapDisarm);
		}
		if (smithing > 0) {
			System.out.println("Smithing:           " + smithing);
		}
	}
}
