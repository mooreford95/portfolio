/**
 * 
 */
package items;

import java.util.Random;

/**
 * @author Curtis
 *
 */
public class Weapon extends Enchantable implements Item {
	private int itemNumber;
	private int damage;
	private int armorNegation;
	private boolean enchanted;
	private int accuracy;

	/**
	 * Constructor
	 * 
	 * @param damage
	 *            weapon damage. Proportional to health the recipient will
	 *            receive
	 * @param armorNegation
	 *            weapons with high damage will have to chew through armor
	 *            first. Weapons with high armorNegation mostly ignore armor but
	 *            do little physical damage.
	 * @param enchanted
	 *            if the weapon is pre-enchanted, it cannot be further enchanted
	 * @param accuracy
	 *            counteracts miss chances
	 */
	public Weapon(int damage, int armorNegation, int accuracy, boolean enchanted) {
		this.damage = damage;
		this.armorNegation = armorNegation;
		this.enchanted = enchanted;
		this.accuracy = accuracy;
	}

	/**
	 * @return the damage
	 */
	public int getDamage() {
		return damage;
	}

	/**
	 * @return the armorNegation
	 */
	public int getArmorNegation() {
		return armorNegation;
	}

	/**
	 * @return if enchanted
	 */
	public boolean isEnchanted() {
		return enchanted;
	}

	/**
	 * Enchants a weapon. If it is already enchanted, it cannot be further
	 * enchanted. Otherwise, it will be randomly enchanted according to enchant
	 * random rules in enchantLevel method.
	 * 
	 * @param magic
	 *            the player's magic level
	 */
	public boolean enchant(int magic) {
		if (enchanted) {
			return false;
		}
		if (super.enchant(magic)) {
			enchantLevel(magic);
			return true;
		}
		return false;
	}

	/**
	 * Determines by how much an item will be enchanted.
	 * 
	 * @param magic the player's magic level.
	 */
	private void enchantLevel(int magic) {
		Random randy = new Random();
		int damageIncrease = randy.nextInt(damage / 3 + magic / 2) + 1;
		int accuracyIncrease = randy.nextInt(accuracy / 2 + magic / 2) + 1;
		if (damageIncrease < 1) {
			damageIncrease = 1;
		}
		if (accuracyIncrease < 1) {
			accuracyIncrease = 1;
		}
		if (accuracyIncrease + damageIncrease == 2) {
			accuracyIncrease++;
		}
		this.damage += damageIncrease;
		this.accuracy += accuracyIncrease;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see items.Item#setItemNumber(int)
	 */
	@Override
	public void setItemNumber(int number) {
		this.itemNumber = number;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see items.Item#getItemNumber()
	 */
	@Override
	public int getItemNumber() {
		return itemNumber;
	}

}
