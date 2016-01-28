package items;

import java.util.Random;

public abstract class Enchantable {
	
	public static final int FAIL_CHANCE = 5;

	public boolean enchant(int magic) {
		Random randy = new Random();
		
		int failChance = randy.nextInt(FAIL_CHANCE);
		return(failChance-(magic/2) < 0); //the higher the magic, the lower the fail chance
			 //need to call a method to actually perform the enchantment. Maybe in classes?
	}

}
