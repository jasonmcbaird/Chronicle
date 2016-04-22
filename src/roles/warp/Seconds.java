package roles.warp;

import queue.Queue;
import rules.ChangeEnergy;
import rules.ClearEvent;
import rules.EndTurn;
import rules.ExecuteIfEmptyEnergy;
import rules.ExecuteOnEventType;
import rules.Move;
import rules.NullifyNext;
import rules.SpendSeconds;
import ability.Ability;
import character.Character;
import enums.Condition;
import enums.EnergyType;

//Ability:  Seconds
//Level:    0
//Passive:  True
//
//Rules:    1. At the beginning of your turn, you reset to 6 seconds.
//			2. Whenever you move 1 or 2 squares, you lose 1 second.
//			3. Whenever you use a normal ability, you lose 4 seconds.
//			4. Your turn does not end when you use an ability.
//			5. Whenever you use a Warp ability, you lose a custom number of seconds from 0 to 6.
//			6. When you have 0 seconds remaining, your turn ends.

public class Seconds extends Ability {
	
	public Seconds() {
		setName("Seconds");
		setPassive(true);
	}
	
	public void subExecute(Character source, Character target, Condition condition) {
		ChangeEnergy reset = new ChangeEnergy(EnergyType.SECONDS, true);
		reset.setPersistent(true);
		Queue.add(reset, source, target, Condition.START_TURN);
		
		ExecuteOnEventType checkAbilitySeconds = new ExecuteOnEventType(Ability.class, new SpendSeconds());
		checkAbilitySeconds.setAfter();
		checkAbilitySeconds.setPersistent(true);
		Queue.add(checkAbilitySeconds, source, target, Condition.ACTIVATE_ABILITY);
		
		ExecuteOnEventType clearEndTurn = new ExecuteOnEventType(EndTurn.class, new ClearEvent());
		clearEndTurn.setPersistent(true);
		Queue.add(clearEndTurn, source, target, Condition.ACTIVATE_ABILITY);
		
		ExecuteOnEventType checkMoveSeconds = new ExecuteOnEventType(Move.class, new SpendSeconds());
		checkMoveSeconds.setPersistent(true);
		checkMoveSeconds.selfRestriction(true);
		Queue.add(checkMoveSeconds, source, target, Condition.MOVE);
		
		NullifyNext nullifyNextClear = new NullifyNext(clearEndTurn);
		ExecuteIfEmptyEnergy execute = new ExecuteIfEmptyEnergy(nullifyNextClear, EnergyType.SECONDS);
		execute.setPersistent(true);
		Queue.add(execute, source, target, Condition.CHANGE_ENERGY);
	}
}
