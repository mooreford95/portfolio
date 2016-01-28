package maps;

import java.util.ArrayList;

/**
 * Location has actions associated with it. User can gain items, spend money,
 * etc. through these actions. May also be utilized for the map interface.
 * 
 * @author Curtis
 */

public class Location {
	private String name;
	private ArrayList<Action> actions;

	public Location(String name) {
		this.name = name;
		actions = new ArrayList<Action>();
	}

	public String getName() {
		return this.name;
	}

	public void addAction(Action action) {
		actions.add(action);
	}

	public void removeAction(String action) {
		actions.remove(action);
	}

	public void printActions() {
		printRecursive(actions, 1);
	}

	private void printRecursive(ArrayList<Action> actions, int index) {
		if (index == actions.size() - 1) {
			return;
		}
		System.out.println((index + 1) + ": " + actions.get(index));
		printRecursive(actions, index + 1);
	}
}
