package dungeon;

import unit.Unit;

public class Floor {

	private int dificulty;
	private boolean isSpecial;
	private int size;
	private int encounterRate; // 0-100%
	private Unit[] monsters;

	public Floor(int dificulty, int size, int encounterRate) {
		this.dificulty = dificulty;
		this.isSpecial = false;
		this.size = size;
		this.encounterRate = encounterRate;
		populateMonsters();
	}

	private void populateMonsters() {
		monsters = new Unit[5];
		monsters[0] = new Unit("Slime", 2, 2, 2, 2, 2, 2);
		monsters[1] = new Unit("Rat", 3, 2, 2, 2, 2, 2);
		monsters[2] = new Unit("Bug", 2, 3, 3, 2, 2, 2);
		monsters[3] = new Unit("Dog", 2, 2, 3, 2, 2, 2);
		monsters[4] = new Unit("Cat", 3, 2, 2, 3, 2, 2);
	}

	private Unit getRandomMonster() {
		return monsters[(int) (Math.random() * monsters.length)];
	}

	public String monsterEncounter(Unit hero) {
		String allEncounterLog = "";
		for (int i = 0; i < size; i++) {
			int encounterRoll = (int)(Math.random() * 100);
			if (encounterRoll < encounterRate) {
				String singleEncounter = hero.fight(getRandomMonster());
				allEncounterLog = allEncounterLog.concat(hero.fight(getRandomMonster()));
			}
		}
		return allEncounterLog;
	}

	public int getDificulty() {
		return dificulty;
	}

}
