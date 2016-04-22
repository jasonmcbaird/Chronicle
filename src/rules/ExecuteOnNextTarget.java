package rules;

import java.util.ArrayList;
import java.util.Collections;

import mapLogic.GridLogic;
import queue.Queue;
import utilities.Logger;
import character.Character;
import enums.Condition;
import enums.Relationship;

public class ExecuteOnNextTarget extends Rule {
	
	private Rule rule;
	private ArrayList<Character> except = new ArrayList<Character>();
	private Relationship relationship;
	
	public ExecuteOnNextTarget(Rule rule) {
		this.rule = rule;
		relationship = Relationship.ENEMY;
		persistent = true;
	}
	
	public ExecuteOnNextTarget(Rule rule, ArrayList<Character> except) {
		this.rule = rule;
		this.except.addAll(except);
		relationship = Relationship.ENEMY;
		persistent = true;
	}
	
	public ExecuteOnNextTarget(Rule rule, Relationship relationship) {
		this.rule = rule;
		this.relationship = relationship;
		persistent = true;
	}
	
	public ExecuteOnNextTarget(Rule rule, ArrayList<Character> except, Relationship relationship) {
		this.rule = rule;
		this.except.addAll(except);
		this.relationship = relationship;
		persistent = true;
	}
	
	public void subExecute(Character source, Character target, Condition condition) {
		ArrayList<Character> targets = GridLogic.getTargetsExcept(source, relationship, except);
		if(targets.size() > 0) {
			Logger.print("Executing on possible targets: ", -1);
			for(Character character: targets) {
				Logger.print(character.getName(), -1);
			}
			Collections.shuffle(targets);
			Character newTarget = targets.get(0);
			Queue.addAndRun(rule, source, newTarget, Condition.NOW);
			except.add(newTarget);
		}
	}
	
}
