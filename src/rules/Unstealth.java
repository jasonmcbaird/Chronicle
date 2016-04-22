package rules;

import queue.Queue;
import ui.LogDisplay;
import utilities.Logger;
import character.Character;
import encounter.Encounter;
import enums.Condition;

public class Unstealth extends Rule {
	
	private int duration = 4;
	private int timer = 1;
	
	public Unstealth() {
		
	}
	
	public Unstealth(int duration) {
		this.duration = duration;
	}
	
	public void subExecute(Character source, Character target, Condition condition) {
		if(timer < duration) {
			Logger.print("Not reappearing yet. Timer: " + timer + ", duration: " + duration, -1);
			timer++;
			return;
		}
		target.attributes.unstealth();
		Encounter.get().updateGrid();
		Logger.print(source.getName() + " unstealths. Are they stealthed now? " + source.attributes.getStealthed());
		LogDisplay.log(target.getName() + " reappears!");
		Queue.remove(Unstealth.class);
		setPersistent(false);
	}
	
}
