package dungeon;

import unit.Unit;

public class Dungeon {

	private Floor[] floors;
	private String name;

	public Dungeon(int floorNumber) {
		floors = new Floor[floorNumber];
		for (int i = 0; i < floorNumber; i++) {
			floors[i] = new Floor(i, (3 + i) * (3 + i), 20);
		}
	}

	public String processDungeon(Unit hero) {
		String dungeonLog = "";
		for (int i = 0; i < floors.length; i++) {
			dungeonLog = dungeonLog.concat(floors[i].monsterEncounter(hero));
		}
		return dungeonLog;
	}
}
