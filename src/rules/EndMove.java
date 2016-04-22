package rules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import queue.Queue;
import character.Character;
import character.Role;
import encounter.Encounter;
import enums.Condition;

public class EndMove extends Rule {
	
	public EndMove() {
		
	}
	
	public void subExecute(Character source, Character target, Condition condition) {
		Encounter.get().endMove(target);
	}
	
	public void gridExecute(Character source, Character target, Condition condition) {
		Map<String, ArrayList<String>> hash = new HashMap<String, ArrayList<String>>();
		for(Role role: target.getRoles()) {
			//ArrayList<String> abilityNames = target.getAbilityNames(role);
			//hash.put(role.getName(), abilityNames);
		}
		//Queue.getGrid().displayCombatMenu(hash);
	}
	
}
