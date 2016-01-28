package items;

import java.util.Random;


public class Armor extends Enchantable implements Item {
	private int defense;
	boolean enchanted;
	
	public Armor(int defense, boolean enchanted) {
		this.defense = defense;
		this.enchanted = enchanted;
	}
	
	public boolean enchant(int magic) {
		if(enchanted) {
			return false;
		}
		if(super.enchant(magic)) {
			enchantLevel(magic);
			return true;
		}
		return false;
	}
	
	private void enchantLevel(int magic) {
		Random randy = new Random();
		int defenseIncrease = randy.nextInt(defense/2+magic/2)+1;
		if(defenseIncrease <= 1) {
			defenseIncrease = 2;
		}
		this.defense +=defenseIncrease;
		
	}
	
	public int getDefense() {
		return defense;
	}
	
	@Override
	public void setItemNumber(int number) {
		throw new IllegalArgumentException("Armors do not stack");
	}

	@Override
	public int getItemNumber() {
		return 1;
	}

}
