package utilities;

import java.util.Random;

public class Dice {
	
	private static Dice instance = null;
	
	protected Dice() {
		// You can't instantiate Dice directly.
	}
	
	public static Dice get() {
		if(instance == null) {
			instance = new Dice();
		}
		return instance;
	}
	
	public static boolean rollAgainstDC(int die, int bonus, int DC) {
		int roll = rollDie(die) + bonus;
		//Logger.get().print("Die: " + die + ", bonus: " + bonus + ", DC: " + DC);
		if(roll >= DC) {
			return true;
		}
		return false;
	}
	
	public static int rollDie(int die) {
		Random rng = new Random();
		return rng.nextInt(die) + 1;
	}

}