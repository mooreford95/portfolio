package character;

import items.Inventory;
import items.Item;

public class PlayerCharacter extends Character {
	private int currency;
	private Inventory inventory;
	private static final int DEFAULT_INVENTORY = 30;

	/**
	 * Sets up the player's character based on their choices.
	 * 
	 * @param name
	 *            of the character, used around the game.
	 * @param race
	 *            of the character, elf, dwarf or human.
	 * @param specialization
	 *            class of character
	 * @param secondSpec
	 *            human-only. Will probably need some balancing later.
	 */
	public PlayerCharacter(String name, String race, String specialization, String secondSpec) {
		super(name, race, specialization);

		inventory = new Inventory(DEFAULT_INVENTORY);

		currency = 200;

		if (!race.toLowerCase().equals("human") && secondSpec != null) {
			throw new IllegalArgumentException("Only humans may dual-specialize");
		}

		if (race.toLowerCase().equals("dwarf")) {
			super.stats = new CharacterStats(10, 10, 15, 10, 5, 13, 10, 0, 1);
		}
		if (race.toLowerCase().equals("elf")) {
			super.stats = new CharacterStats(15, 15, 8, 10, 1, 8, 10, 0, 0);
		}
		if (race.toLowerCase().equals("human")) {
			super.stats = new CharacterStats(10, 10, 10, 10, 0, 10, 10, 0, 0);
		}
		if (specialization.toLowerCase().equals("warrior")) {
			super.stats.setStrength(super.stats.getStrength() + 3);
			super.stats.setConstitution(super.stats.getConstitution() + 3);
		}
		if (specialization.toLowerCase().equals("mage")) {
			if (race.toLowerCase().equals("elf")) {
				throw new IllegalArgumentException("Elves cannot be mages");
			} else {
				super.stats.setMagic(super.stats.getMagic() + 6);
				super.stats.setWisdom(super.stats.getWisdom() + 3);
			}
		}
		if (specialization.toLowerCase().equals("Rouge")) {
			super.stats.setCunning(super.stats.getCunning() + 3);
			super.stats.setStrength(super.stats.getStrength() + 1);
			super.stats.setTrapDisarm(3);
		}
		// human-only secondary classes
		if (race.toLowerCase().equals("human")) {
			// smith second special
			if (specialization.toLowerCase().equals("warrior") && secondSpec.toLowerCase().equals("smith")) {
				super.stats.setSmithing(2);
				super.stats.setCunning(super.stats.getCunning() + 1);
			} else if (specialization.toLowerCase().equals("rouge") && secondSpec.toLowerCase().equals("smith")) {
				super.stats.setSmithing(1);
				super.stats.setStrength(super.stats.getStrength() + 1);
			} else if (specialization.toLowerCase().equals("mage") && secondSpec.toLowerCase().equals("smith")) {
				super.stats.setSmithing(1);
				super.stats.setStrength(super.stats.getStrength() + 1);
			}

			// warrior second special
			if (secondSpec.toLowerCase().equals("warrior")) {
				super.stats.setStrength(super.stats.getStrength() + 2);
				super.stats.setConstitution(super.stats.getConstitution() + 2);
				if (specialization.toLowerCase().equals("warrior")) {
					setSpecialization("Master Warrior");
				}
			}

			// mage second special
			if (secondSpec.toLowerCase().equals("mage")) {
				super.stats.setMagic(super.stats.getMagic() + 4);
				super.stats.setWisdom(super.stats.getWisdom() + 3);
				if (specialization.toLowerCase().equals("mage")) {
					setSpecialization("High Mage");
				}
			}

			// trapper second special
			if (secondSpec.toLowerCase().equals("trapper")) {
				super.stats.setTrapDisarm(super.stats.getTrapDisarm() + 1);
				if (specialization.toLowerCase().equals("rouge")) {
					setSpecialization("Hunter");
					super.stats.setDexterity(super.stats.getDexterity() + 1);
				}
			}
		}
	}

	public void spendCurrency(int amountSpent) {
		if (amountSpent > currency) {
			throw new IllegalArgumentException("Insufficent currency");
		}
		currency -= amountSpent;
	}

	public void addItem(Item item) {
		inventory.add(item);
	}

	public String toString() {
		return (super.getName() + " " + super.getRace() + " " + super.getSpecialization());
	}

}
