package character;

public abstract class Character {
	protected CharacterStats stats;
	/**
	 * @return the race
	 */
	public String getRace() {
		return race;
	}

	/**
	 * @param race the race to set
	 */
	public void setRace(String race) {
		this.race = race;
	}

	/**
	 * @return the specialization
	 */
	public String getSpecialization() {
		return specialization;
	}

	/**
	 * @param specialization the specialization to set
	 */
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	private String race;
	private String specialization;
	private String name;
	
	
	public Character(String name, String race, String specialization) {
		this.name = name;
		this.race = race;
		this.specialization = specialization;
	}
	
	public void printStats() {
		System.out.println(name+ "'s Statistics are as follows:");
		stats.printTable();
	}
}
