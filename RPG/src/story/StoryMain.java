package story;

import character.PlayerCharacter;

/**
 * Initiates the storyline. This is the easy part.
 * 
 * @author Curtis
 *
 */
public class StoryMain {
	
	/**apparently, this needs to be static. Thank you, Kingdom of nouns...*/
	static PlayerCharacter player;

	/**
	 * Starts the programme. Should initiate the story.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		introDialogue();

	}

	/**
	 * Launches the opening dialog
	 */
	private static void introDialogue() {
		OpenDialog openingDialog = new OpenDialog();
		openingDialog.displayHeader();
		if (openingDialog.loadSave()) {
			loadSave();
		} else {
			player = openingDialog.openPrompt();
			// System.out.println(player);
		}
	}

	/**
	 * At some point, will enable user to load saved data. .csv, maybe?
	 */
	private static void loadSave() {
		// TODO Auto-generated method stub

	}

}
