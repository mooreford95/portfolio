package character;

import java.util.NoSuchElementException;
import java.util.Scanner;

import story.Dialog;

/**
 * Goes through a set of prompts to make a player character.
 * 
 * @author Curtis
 *
 */
public class PlayerCharacterFactory {
	String name;
	String race;
	String firstSpec;
	String secondSpec;
	PlayerCharacter character;
	Dialog dia;

	/**
	 * Constructor for the factory. Does that make this a factory factory?
	 * 
	 * @param name
	 *            name of character.
	 */
	public PlayerCharacterFactory(String name) {
		this.name = name;
		dia = new Dialog(null, new Scanner(System.in));
		takeInRace();

		character = new PlayerCharacter(this.name, race, firstSpec, secondSpec);
	}

	/**
	 * The description is in the return tag, isn't it?
	 * 
	 * @return the character field.
	 */
	public PlayerCharacter getCharacter() {
		if (character == null) {
			throw new NoSuchElementException("Character has not been created.");
		}
		return character;
	}

	/**
	 * Prompts the user, takes in the race, advises the player.
	 */
	private void takeInRace() {

		System.out.println("-------------------------------------------");
		System.out.println("Choose your race now.");
		System.out
				.println("Be aware, dwarves are a hardy people, the masters of smithing and magic.");
		System.out
				.println("Elves are light on their feet and keen of sight. "
						+ "\n \t However, they are a race of slight build and cannot be mages.");
		System.out
				.println("Humans are physically midways between the two, but are a determined and adaptable race, "
						+ "\n \t the only one able to choose a second specialization.");
		System.out.print("Choose wisely. (elf/dwarf/human) ");
		String response = dia.takeInTrimmedString();
		while (!isValidRaceResponse(response)) {
			System.out.println("Invalid.");
			System.out.print("Choose wisely. (elf/dwarf/human) ");
			response = dia.takeInTrimmedString();
		}
		System.out.println("Confirm?");
		Boolean yn = dia.takeInYesNo();
		while (!yn) {
			System.out.print("Choose wisely. (elf/dwarf/human) ");
			response = dia.takeInTrimmedString();
			while (!isValidRaceResponse(response)) {
				System.out.println("Invalid.");
				System.out.print("Choose wisely. (elf/dwarf/human) ");
				response = dia.takeInTrimmedString();
			}
			System.out.println("Confirm?");
			yn = dia.takeInYesNo();
		}
		race = response;
		System.out.println();
		if (race.equalsIgnoreCase("human")) {
			humanSpecChooser();
		}
		if (race.equalsIgnoreCase("elf")) {
			elfSpecChooser();
		} else {
			dwarfSpecChooser();
		}
		return;

	}

	/**
	 * Chooser for dwarven specializations.
	 */
	private void dwarfSpecChooser() {
		System.out
				.println("Hello, then, to you, founder of the clan-house of Alviiu, the oldest and noblest of the "
						+ "\ndwarven houses. You are clanfather "
						+ name
						+ ", and it is time to choose your title.");
		System.out.println("Your specialization options:");
		System.out.println("Warrior: +3 Strength, +3 Constitution");
		System.out.println("Mage: +6 Magic, +3 Wisdom");
		System.out.println("Rouge: +3 Cunning, +1 Strength, +3 TrapDisarm");
		System.out.print("Choose wisely. (warrior/mage/rouge) ");
		String response = dia.takeInTrimmedString();
		while (!isValidClassResponse(response)) {
			System.out.println("Invalid.");
			System.out.print("Choose wisely. (warrior/mage/rouge) ");
			response = dia.takeInTrimmedString();
		}
		System.out.print("Confirm?");
		Boolean yn = dia.takeInYesNo();
		while (!yn) {
			response = dia.takeInTrimmedString();
			while (!isValidClassResponse(response)) {
				System.out.println("Invalid.");
				System.out.print("Choose wisely. (warrior/mage/rouge) ");
				response = dia.takeInTrimmedString();
			}
			System.out.print("Confirm?");
			yn = dia.takeInYesNo();
		}
		firstSpec = response;
	}

	/**
	 * Is it a valid class?
	 * 
	 * @param response
	 *            the player's response. Should come from takeInTrimmedString
	 *            somewhere along the lines.
	 * @return true if player's response is valid.
	 */
	private boolean isValidClassResponse(String response) {
		if (response.equalsIgnoreCase("mage")
				|| response.equalsIgnoreCase("warrior")
				|| response.equalsIgnoreCase("rouge")) {
			return true;
		}
		return false;
	}

	/**
	 * Elves choose their specialization here.
	 */
	private void elfSpecChooser() {
		System.out.println("Greetings, then, " + name
				+ ", of the immortal elves.");
		System.out
				.println("Elves have no grand formal titles, as the dwarves or humans do.");
		System.out
				.println("But you live long, easy lives, resting assured that your Faerie guardians \n"
						+ "will contribute their magic to you in your times of darkest need.");
		System.out.println("What is your chosen path, " + name
				+ " of the immortals?");
		System.out.println("Your specialization options:");
		System.out.println("Warrior: +3 Strength, +3 Constitution");
		System.out.println("Rouge: +3 Cunning, +1 Strength, +3 TrapDisarm");
		System.out.print("Choose wisely. (warrior/rouge) ");
		String response = dia.takeInTrimmedString();
		while (!isValidElfClassResponse(response)) {
			System.out.println("Invalid.");
			System.out.print("Choose wisely. (warrior/rouge) ");
			response = dia.takeInTrimmedString();
		}
		System.out.print("Confirm?");
		Boolean yn = dia.takeInYesNo();
		while (!yn) {
			response = dia.takeInTrimmedString();
			while (!isValidElfClassResponse(response)) {
				System.out.println("Invalid.");
				System.out.print("Choose wisely. (warrior/rouge) ");
				response = dia.takeInTrimmedString();
			}
			System.out.print("Confirm?");
			yn = dia.takeInYesNo();
		}
		firstSpec = response;

	}

	/**
	 * Is the class response valid?
	 * 
	 * @param response
	 *            of the player
	 * @return true if the response is valid.
	 */
	private boolean isValidElfClassResponse(String response) {
		if (response.equalsIgnoreCase("warrior")
				|| response.equalsIgnoreCase("rouge")) {
			return true;
		}
		return false;
	}
/**
 * Humans choose their specialization here.
 */
	private void humanSpecChooser() {
		System.out
				.println("Ah, a human, one of the newcomers to the scenes of this land.");
		System.out
				.println("Humans are looked down upon by immortal elf and mighty dwarf alike.");
		System.out
				.println("But you are adaptable and quickwitted."
						+ "\n Like most of your brethern, you are confident that your kind will one day rule Terra."
						+ "\n Choose now your first and second classes.");
		System.out.println("Your first specialization options:");
		System.out.println("Warrior: +3 Strength, +3 Constitution");
		System.out.println("Mage: +6 Magic, +3 Wisdom");
		System.out.println("Rouge: +3 Cunning, +1 Strength, +3 TrapDisarm");
		System.out.print("Choose wisely. (warrior/mage/rouge) ");
		String response = dia.takeInTrimmedString();
		while (!isValidClassResponse(response)) {
			System.out.println("Invalid.");
			System.out.print("Choose wisely. (warrior/mage/rouge) ");
			response = dia.takeInTrimmedString();
		}
		System.out.print("Confirm?");
		Boolean yn = dia.takeInYesNo();
		while (!yn) {
			response = dia.takeInTrimmedString();
			while (!isValidClassResponse(response)) {
				System.out.println("Invalid.");
				System.out.print("Choose wisely. (warrior/mage/rouge) ");
				response = dia.takeInTrimmedString();
			}
			System.out.print("Confirm?");
			yn = dia.takeInYesNo();
		}
		firstSpec = response;
		System.out.println("Time to choose your second specialization.");
		if (firstSpec.equalsIgnoreCase("warrior")) {
			warriorChoices();
		}
		if (firstSpec.equalsIgnoreCase("mage")) {
			mageChoices();
		}
		if (firstSpec.equalsIgnoreCase("rouge")) {
			rougeChoices();
		}
	}

	private void rougeChoices() {
		System.out.println("Secondary specialization options: ");
		System.out.println("Warrior: Strength & Constitution +2");
		System.out.println("Smith: Cunning +1, 2 Smithing");
		System.out.println("Mage: Magic +3, Wisdom +4");
		System.out.println("Trapper (Become Hunter): Trap Disarm Skill +1");

		System.out.print("Choose wisely. (warrior/mage/smith/trapper) ");
		String response = dia.takeInTrimmedString();
		while (!isValidSecondClassResponse(response)) {
			System.out.println("Invalid.");
			System.out.print("Choose wisely. (warrior/mage/smith/trapper) ");
			response = dia.takeInTrimmedString();
		}
		System.out.print("Confirm?");
		Boolean yn = dia.takeInYesNo();
		while (!yn) {
			response = dia.takeInTrimmedString();
			while (!isValidSecondClassResponse(response)) {
				System.out.println("Invalid.");
				System.out
						.print("Choose wisely. (warrior/mage/smith/trapper) ");
				response = dia.takeInTrimmedString();
			}
			System.out.print("Confirm?");
			yn = dia.takeInYesNo();
		}
		secondSpec = response;
	}

	private void mageChoices() {
		System.out.println("Secondary specialization options: ");
		System.out.println("Warrior: Strength & Constitution +2");
		System.out.println("Smith: Cunning +1, 2 Smithing");
		System.out.println("Mage (Become High Mage): Magic +3, Wisdom +4");
		System.out.println("Trapper: Trap Disarm Skill +1");

		System.out.print("Choose wisely. (warrior/mage/smith/trapper) ");
		String response = dia.takeInTrimmedString();
		while (!isValidSecondClassResponse(response)) {
			System.out.println("Invalid.");
			System.out.print("Choose wisely. (warrior/mage/smith/trapper) ");
			response = dia.takeInTrimmedString();
		}
		System.out.print("Confirm?");
		Boolean yn = dia.takeInYesNo();
		while (!yn) {
			response = dia.takeInTrimmedString();
			while (!isValidSecondClassResponse(response)) {
				System.out.println("Invalid.");
				System.out
						.print("Choose wisely. (warrior/mage/smith/trapper) ");
				response = dia.takeInTrimmedString();
			}
			System.out.print("Confirm?");
			yn = dia.takeInYesNo();
		}
		secondSpec = response;

	}

	private void warriorChoices() {

		System.out.println("Secondary specialization options: ");
		System.out
				.println("Warrior (become Master Warrior): Strength & Constitution +2");
		System.out.println("Smith: Cunning +1, 2 Smithing");
		System.out.println("Mage: Magic +3, Wisdom +4");
		System.out.println("Trapper: Trap Disarm Skill +1");

		System.out.print("Choose wisely. (warrior/mage/smith/trapper) ");
		String response = dia.takeInTrimmedString();
		while (!isValidSecondClassResponse(response)) {
			System.out.println("Invalid.");
			System.out.print("Choose wisely. (warrior/mage/smith/trapper) ");
			response = dia.takeInTrimmedString();
		}
		System.out.print("Confirm?");
		Boolean yn = dia.takeInYesNo();
		while (!yn) {
			response = dia.takeInTrimmedString();
			while (!isValidSecondClassResponse(response)) {
				System.out.println("Invalid.");
				System.out
						.print("Choose wisely. (warrior/mage/smith/trapper) ");
				response = dia.takeInTrimmedString();
			}
			System.out.print("Confirm?");
			yn = dia.takeInYesNo();
		}
		secondSpec = response;

	}

	private boolean isValidSecondClassResponse(String response) {
		if (response.equalsIgnoreCase("warrior")
				|| response.equalsIgnoreCase("mage")
				|| response.equalsIgnoreCase("smith")
				|| response.equalsIgnoreCase("trapper")) {
			return true;
		}
		return false;
	}

	private boolean isValidRaceResponse(String response) {
		if (response.equalsIgnoreCase("elf")
				|| response.equalsIgnoreCase("dwarf")
				|| response.equalsIgnoreCase("human")) {
			return true;
		}
		return false;
	}

}
