package unit;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Unit {

	private static int HERO_INITIAL_STRENGTH = 10;
	private static int HERO_INITIAL_VITALITY = 10;
	private static int HERO_INITIAL_DEXTERITY = 10;
	private static int HERO_INITIAL_SPIRIT = 10;
	private static int HERO_INITIAL_MAGIC = 10;
	private static int HERO_INITIAL_LV = 1;
	private static int HERO_LEVEL_UP_STRENGHT = 2;
	private static int HERO_LEVEL_UP_DEXTERITY = 2;
	private static int HERO_LEVEL_UP_VITALITY = 2;
	private static int HERO_LEVEL_UP_SPIRIT = 2;
	private static int HERO_LEVEL_UP_MAGIC = 2;
	// private static String SAVE_FILE =
	// "C://Users//Hideki//Desktop//MainCharacter.properties";
	private static String SAVE_FILE = "C://Documents and Settings//henrique.oka//Desktop//MainCharacter.properties";

	/**
	 * Dynamic stats
	 */
	private int actualHitPoints;
	private int actualMagicPoints;

	/**
	 * Calculated stats
	 */
	private int minAttack;
	private int maxAttack;
	private int maxHitPoints;
	private int maxMagicPoints;
	private int defense;
	private int magicAttack;
	private int magicDefense;

	/**
	 * Raw stats
	 */
	private int strength;
	private int vitality;
	private int dexterity;
	private int magic;
	private int spirit;
	private int level;
	private String name;

	public Unit(String name) {
		this.name = name;
		setRawStats(name, HERO_INITIAL_STRENGTH, HERO_INITIAL_VITALITY,
				HERO_INITIAL_DEXTERITY, HERO_INITIAL_SPIRIT,
				HERO_INITIAL_MAGIC, HERO_INITIAL_LV);
		calculateAttributes();
		fullHeal();
	}

	public Unit(String name, int strength, int vitality, int dexterity,
			int spirit, int magic, int level) {
		setRawStats(name, strength, vitality, dexterity, spirit, magic, level);
		calculateAttributes();
		fullHeal();
	}

	private void setRawStats(String name, int strength, int vitality,
			int dexterity, int spirit, int magic, int level) {
		this.name = name;
		this.level = level;
		this.strength = strength;
		this.vitality = vitality;
		this.spirit = spirit;
		this.magic = magic;
	}

	public void fullHeal() {
		this.actualHitPoints = maxHitPoints;
		this.actualMagicPoints = maxMagicPoints;
	}

	private void calculateAttributes() {
		setPhysicalStats();
		setMagicalStats();
	}

	public void levelUp() {
		this.level += 1;
		this.strength += HERO_LEVEL_UP_STRENGHT;
		this.vitality += HERO_LEVEL_UP_VITALITY;
		this.dexterity += HERO_LEVEL_UP_DEXTERITY;
		this.spirit += HERO_LEVEL_UP_SPIRIT;
		this.magic += HERO_LEVEL_UP_MAGIC;
	}

	private void setPhysicalStats() {
		this.minAttack = (int) Math.round(strength * 0.5);
		this.maxAttack = strength * 1;
		this.maxHitPoints = vitality * 10;
		this.defense = dexterity;
	}

	private void setMagicalStats() {
		this.magicAttack = magic * 3;
		this.magicDefense = spirit;
		this.maxMagicPoints = magic * 10;

	}

	private void takeDamage(int damage) {
		this.actualHitPoints -= damage;
	}

	private int doDamage() {
		int attackDifference;
		int damage;
		attackDifference = (int) (Math.random() * (this.maxAttack
				- this.minAttack + 1));
		damage = this.minAttack + attackDifference - defense;
		return damage;
	}

	public int getActualHitPoints() {
		return this.actualHitPoints;
	}

	public int getActualMagicPoints() {
		return this.actualMagicPoints;
	}

	public String fight(Unit character) {
		String fightLog = "";
		System.out.println("bleh");
		fightLog = fightLog.concat("Fight against: " + character.name + "\n");
		while (character.getActualHitPoints() > 0
				&& this.getActualHitPoints() > 0) {
			this.takeDamage(character.doDamage());
			character.takeDamage(this.doDamage());
			fightLog = fightLog.concat(this.name + " gets "
					+ character.doDamage() + " damage." + "\n");
			fightLog = fightLog.concat(character.name + " gets "
					+ this.doDamage() + " damage." + "\n");
		}
		if (this.getActualHitPoints() <= 0) {
			fightLog = fightLog.concat(this.name + " has died." + "\n");
		}
		if (character.getActualHitPoints() <= 0) {
			fightLog = fightLog.concat(character.name + " has died." + "\n");
		}
		return fightLog;
	}

	public String getName() {
		return this.name;
	}

	public int getLevel() {
		return this.level;
	}

	public int getStrength() {
		return this.strength;
	}

	public int getVitality() {
		return this.vitality;
	}

	public int getDexterity() {
		return this.dexterity;
	}

	public int getSpirit() {
		return this.spirit;
	}

	public int getMagic() {
		return this.magic;
	}

	public int getMaxHitPoints() {
		return this.maxHitPoints;
	}

	public int getMaxMagicPoints() {
		return this.maxMagicPoints;
	}

	public void saveCharacter() {
		try {
			Properties properties = new Properties();
			properties.setProperty("hero.name", getName());
			properties.setProperty("hero.level",
					((Integer) getLevel()).toString());
			properties.setProperty("hero.strength",
					((Integer) getStrength()).toString());
			properties.setProperty("hero.vitality",
					((Integer) getVitality()).toString());
			properties.setProperty("hero.dexterity",
					((Integer) getDexterity()).toString());
			properties.setProperty("hero.spirit",
					((Integer) getSpirit()).toString());
			properties.setProperty("hero.magic",
					((Integer) getMagic()).toString());
			properties.store(new FileOutputStream(SAVE_FILE), "");
		} catch (IOException e) {
		}
	}

	public void loadCharacter() {
		try {
			int level = 1;
			int strength = 10;
			int vitality = 10;
			int dexterity = 10;
			int spirit = 10;
			int magic = 10;
			String name = "";
			Properties properties = new Properties();
			FileInputStream fileInputStream = new FileInputStream(SAVE_FILE);
			try {
				properties.load(fileInputStream);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (properties != null) {
				if (properties.containsKey("hero.level")) {
					level = Integer.parseInt(properties
							.getProperty("hero.level"));
				}
				if (properties.containsKey("hero.name")) {
					name = properties.getProperty("hero.name");
				}
				if (properties.containsKey("hero.strength")) {
					strength = Integer.parseInt(properties
							.getProperty("hero.strength"));
				}
				if (properties.containsKey("hero.vitality")) {
					vitality = Integer.parseInt(properties
							.getProperty("hero.vitality"));
				}
				if (properties.containsKey("hero.dexterity")) {
					dexterity = Integer.parseInt(properties
							.getProperty("hero.dexterity"));
				}
				if (properties.containsKey("hero.spirit")) {
					spirit = Integer.parseInt(properties
							.getProperty("hero.spirit"));
				}
				if (properties.containsKey("hero.magic")) {
					magic = Integer.parseInt(properties
							.getProperty("hero.magic"));
				}
			}
			setRawStats(name, strength, vitality, dexterity, spirit, magic,
					level);
			calculateAttributes();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
