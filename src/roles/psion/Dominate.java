package roles.psion;

import ability.Ability;
import queue.Queue;
import rules.Control;
import rules.Convert;
import rules.Cooldown;
import ui.LogDisplay;
import character.Character;
import enums.Condition;
import enums.Stat;

//Ability:  Dominate
//Level:    10
//Passive:  False
//
//Rules:    1. Gain control of a target. It's now on your team and you control it.
//			2. This effect ends after a few of the character's turns;
//				two turns at the minimum, plus more if you have high Intellect and/or the target has low Will.
//			3. When you cast Dominate, it becomes disabled.
//			5. (X) turns after you cast Dominate, it becomes enabled again.

public class Dominate extends Ability {
	
	private final int minimumDuration = 2;
	private final int cooldown = 8;

	public Dominate() {
		setRange(2);
		setName("Dominate");
	}
	
	public void subExecute(Character source, Character target, Condition condition) {
		int duration = getDuration(source, target);
		Control returnControl = new Control(target.isPlayer());
		returnControl.delay(duration);
		Queue.add(returnControl, source, target, Condition.END_TURN);
		Queue.addAndRun(new Control(source.isPlayer()), source, target, Condition.NOW);
		Convert unConvert = new Convert(target.getTeam());
		unConvert.delay(duration - 1);
		Queue.add(unConvert, source, target, Condition.END_TURN);
		Queue.addAndRun(new Convert(source.getTeam()), source, target, Condition.NOW);
		LogDisplay.log(source.getName() + " converts " + target.getName() + " to their team for " + duration + " turns!");
		
		Queue.add(new Cooldown(cooldown, this), source, source, Condition.END_TURN);
	}
	
	private int getDuration(Character source, Character target) {
		int duration = source.attributes.getStat(Stat.INTELLECT) - target.attributes.getStat(Stat.WILL)*2;
		duration = duration / 4;
		if(duration < minimumDuration - 1) {
			duration = minimumDuration - 1;
		}
		duration++;
		return duration;
	}
}
