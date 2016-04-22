package items.accessories;

import items.Accessory;

import java.util.ArrayList;

import queue.Event;
import queue.Queue;
import rules.Break;
import rules.ExecuteIfHealthBelowThreshold;
import rules.Heal;
import character.Character;
import enums.Condition;

public class CrisisGem extends Accessory {
	
	private final int healAmount = 10;
	
	public CrisisGem() {
		name = "Crisis Gem";
	}
	
	public void startEncounter(Character character) {
		Break breakThis = new Break(this);
		Heal heal = new Heal(healAmount);
		ArrayList<Event> events = new ArrayList<Event>();
		events.add(breakThis);
		events.add(heal);
		Queue.add(new ExecuteIfHealthBelowThreshold(events, 30), character, character, Condition.TAKE_DAMAGE);
	}

}
