package maps;

import items.Item;

public class Action {
	private String actionName;
	private boolean items;
	private boolean spendCurrency;
	private int actCurrency;
	private Item item;

	/**
	 * Sets up the action.
	 * 
	 * @param actionName
	 *            name of the action. Will be displayed on menus.
	 * @param items
	 *            true if player will receive an item.
	 * @param spendCurrency
	 *            true if the player will receive of spend currency
	 */
	public Action(String actionName, boolean items, boolean spendCurrency) {
		this.actionName = actionName;
		this.items = items;
		this.spendCurrency = spendCurrency;
	}

	public String toString() {
		return null;
	}

	public void setActCurrency(int actCurrency) {
		this.actCurrency = actCurrency;
	}

	/**
	 * @return the actionName
	 */
	public String getActionName() {
		return actionName;
	}

	/**
	 * @param actionName
	 *            the actionName to set
	 */
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	/**
	 * @return the items
	 */
	public boolean isItems() {
		return items;
	}

	/**
	 * @param items
	 *            the items to set
	 */
	public void setItems(boolean items) {
		this.items = items;
	}

	/**
	 * @return the spendCurrency
	 */
	public boolean isSpendCurrency() {
		return spendCurrency;
	}

	/**
	 * @return the item
	 */
	public Item getItem() {
		return item;
	}

	/**
	 * @param item
	 *            the item to set
	 */
	public void setItem(Item item) {
		this.item = item;
	}

	/**
	 * @return the actCurrency
	 */
	public int getActCurrency() {
		return actCurrency;
	}
}
