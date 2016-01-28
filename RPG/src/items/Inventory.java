package items;

import java.util.ArrayList;

public class Inventory {
	private int maxSize;
	private ArrayList<Item> items;

	public Inventory(int defaultInventory) {
		maxSize = defaultInventory;
		items = new ArrayList<Item>();
	}

	public void add(Item item) {
		if (items.size() < maxSize) {
			items.add(item);
		}
		else {
			throw new IllegalArgumentException("Your inventory is full!");
		}
	}

	public void remove(Item item) {
		items.remove(item);
	}
	public void setSize(int size) {
		maxSize = size;
	}

}
