package rules;

import character.Character;
import enums.Condition;

public class NullifyNext extends Rule {
	
	private Rule rule;
	
	public NullifyNext(Rule rule) {
		this.rule = rule;
	}
	
	public NullifyNext(Rule rule, int amount) {
		this.rule = rule;
	}
	
	public void subExecute(Character source, Character target, Condition condition) {
		rule.nullifyNextExecute();
	}
}
