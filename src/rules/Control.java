package rules;

import character.Character;
import enums.Condition;

public class Control extends Rule {
	
	private boolean bePlayer;
	
	public Control(boolean bePlayer) {
		this.bePlayer = bePlayer;
	}
	
	public void subExecute(Character source, Character target, Condition condition) {
		target.setIsPlayer(bePlayer);
	}
}
