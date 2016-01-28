package story;

import java.util.Scanner;

import character.PlayerCharacter;
import character.PlayerCharacterFactory;

public class OpenDialog extends Dialog {

	PlayerCharacter player;
	String name;
	String race;
	
	/**
	 * Creates an opening dialog.
	 */
	public OpenDialog() {
		super("Welcome to (iReallyNeedATitle)", new Scanner(System.in));
	}

	/**
	 * The opening dialog. Prompts the player for their name. Passes it to player factory.
	 * After the character is created:
	 * 
	 * @return player character
	 */
	public PlayerCharacter openPrompt() {
		System.out
				.println("Good morrow master..."
						+ "\nAh, I see I forgot to take your name last night. May I ask your name?");
		System.out.print("(name)? ");
		name = super.takeInTrimmedString();
		// System.out.println("Good morrow master " + name);
		System.out.print("Confirm name " + name + "?");
		Boolean response = super.takeInYesNo();
		while (!response) {
			System.out.print("(name)? ");
			name = super.takeInTrimmedString();
			System.out.print("Confirm name " + name + "?");
			response = super.takeInYesNo();
		}
		System.out.println("Good morrow master " + name + ".");
		System.out
				.println("I would like to take this opportunity to welcome you to the city of Greenway.");
		System.out
				.println("Forgive me, for I am addressing you through a door in my humble inn, "
						+ "\nbut I do wish to know the custom in which to address you,");
		System.out.println("are you a dwarf, elf or human?");
		PlayerCharacterFactory factory = new PlayerCharacterFactory(name);
		return factory.getCharacter();
	}

	/**
	 * Checks to see if the user desires to load from a save.
	 * 
	 * @return
	 */
	public boolean loadSave() {
		System.out.print("Would you like to load a save? (y/n) ");
		String response = takeInTrimmedString();
		if (response.equalsIgnoreCase("y") || response.equalsIgnoreCase("yes")) {
			return true;
		} else
			return false;
	}

}
