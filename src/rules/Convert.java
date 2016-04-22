package rules;

import character.Character;
import character.Team;
import enums.Condition;

public class Convert extends Rule {
	
	private Team team;
	
	public Convert(Team team) {
		this.team = team;
	}
	
	public void subExecute(Character source, Character target, Condition condition) {
		target.setTeam(team);
	}
}
