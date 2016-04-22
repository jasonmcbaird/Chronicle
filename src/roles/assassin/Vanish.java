package roles.assassin;

import queue.Queue;
import rules.Unstealth;
import ui.LogDisplay;
import ability.Ability;
import character.Character;
import encounter.Encounter;
import enums.Condition;
import enums.EnergyType;
import enums.Relationship;

//Ability:  Rush
//Level:    2
//Passive:  False
//Involves: TAKE_DAMAGE, ATTACK, MOVE
//
//Rules:    1. Move one square towards the target.
//			2. Make a physical attack based on Power.

public class Vanish extends Ability {
	
	private final int cost = 1;
	private final int duration = 4;
	
	public Vanish() {
		setName("Vanish");
		setTargetRelationship(Relationship.SELF);
	}
	
	public boolean getCanBeActivated(Character character) {
		if(!super.getCanBeActivated(character))
			return false;
		// TODO: This code gets repeated.
		if(canBeActivated)
			if(character.getEnergy(EnergyType.PATIENCE) < cost)
				return false;
		return true;
	}
	
	public void subExecute(Character source, Character target, Condition condition) {
		if(!payEnergy(source, EnergyType.PATIENCE, cost))
			return;
		source.attributes.stealth();
		Unstealth unstealth = new Unstealth();
		Queue.add(unstealth, source, target, Condition.ATTACK);
		Queue.add(unstealth, source, target, Condition.TAKE_DAMAGE);
		Queue.add(unstealth, source, target, Condition.END_ENCOUNTER);
		Unstealth timedUnstealth = new Unstealth(duration);
		timedUnstealth.setPersistent(true);
		Queue.add(timedUnstealth, source, target, Condition.START_TURN);
		Encounter.get().updateGrid();
		LogDisplay.log(target.getName() + " vanishes!");
	}
}
