package roles.sylvan;

import java.util.HashMap;

import queue.Queue;
import rules.ClearEvent;
import rules.EndTurn;
import rules.ExecuteOnEventType;
import rules.StatBuff;
import ability.Ability;
import character.Character;
import enums.Condition;
import enums.EnergyType;
import enums.Relationship;
import enums.Stat;

public class Morph extends Ability {
	
	private final int herbCost = 4;
	private final int duration = 2;

	public Morph() {
		setName("Morph");
		setTargetRelationship(Relationship.SELF);
	}
	
	public boolean getCanBeActivated(Character character) {
		if(!super.getCanBeActivated(character))
			return false;
		// TODO: This code gets repeated.
		if(character.getEnergy(EnergyType.HERBS) < herbCost)
			return false;
		return true;
	}
	
	public void subExecute(Character source, Character target, Condition condition) {
		if(!payEnergy(source, EnergyType.HERBS, herbCost))
			return;
		ExecuteOnEventType clearEndTurn = new ExecuteOnEventType(EndTurn.class, new ClearEvent());
		Queue.add(clearEndTurn, source, target, Condition.ACTIVATE_ABILITY);
		HashMap<Stat, Integer> buffs = getStatBuffs();
		for(Stat stat: buffs.keySet())
			Queue.addAndRun(new StatBuff(stat, buffs.get(stat), Condition.START_TURN, duration), source, target, Condition.NOW);
		//TODO: Prevent you from using class abilities while morphed. Probably.
	}
	
	public HashMap<Stat, Integer> getStatBuffs() {
		HashMap<Stat, Integer> buffs = new HashMap<Stat, Integer>();
		buffs.put(Stat.STRENGTH, 4);
		buffs.put(Stat.TOUGHNESS, 5);
		buffs.put(Stat.WILL, -2);
		buffs.put(Stat.INTELLECT, -4);
		buffs.put(Stat.INTUITION, +1);
		buffs.put(Stat.AGILITY, -2);
		return buffs;
	}
}
