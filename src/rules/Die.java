package rules;

import utilities.Logger;
import character.Character;
import encounter.Encounter;
import enums.Condition;
import enums.Relationship;

public class Die extends Rule {
	
	public void subExecute(Character source, Character target, Condition condition) {
		Logger.print(target.getName() + " dies.");
		if(source != target && source.getRelationship(target) == Relationship.ENEMY) {
			source.gainXP(target.getXPValue());
			Logger.print(source.getName() + " gains " + target.getXPValue() + " XP from " + target.getName() + "'s death.");
		}
		Encounter.get().gainXP(target);
		Encounter.get().removeEntity(target);
		
		// When somebody dies, every one of their enemies in the encounter gets XP equal to their general level.
		// Also, if an enemy was the source of the lethal damage, that enemy gets extra XP equal to the dead character's general level.
	}
	
}
