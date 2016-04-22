package roles.general;

import items.weapons.ColdIronLongsword;
import queue.Queue;
import rules.ChangeEnergy;
import ui.LogDisplay;
import utilities.Position;
import ability.Ability;
import character.Character;
import encounter.Encounter;
import enums.Condition;
import enums.EnergyType;
import enums.Relationship;
import enums.RoleName;
import enums.Stat;

public class Recruit extends Ability {
	
	private final int cost = 4;
		
	public Recruit() {
		super();
		setName("Recruit");
		setTargetRelationship(Relationship.SELF);
	}
	
	public boolean getCanBeActivated(Character character) {
		if(!super.getCanBeActivated(character))
			return false;
		if(canBeActivated) {
			if(character.getEnergy(EnergyType.CONCENTRATION) < cost) {
				return false;
			}
		}
		return true;
	}
	
	public void subExecute(Character source, Character target, Condition condition) {
		if(!payEnergy(source, EnergyType.CONCENTRATION, cost))
			return;
		Encounter.get().add(makeSoldier(target));
	}
	
	private Character makeSoldier(Character source) {
		Character soldier = new Character();
		soldier.setRole(RoleName.WARRIOR, 0);
		setStatPercentage(source, soldier, Stat.STRENGTH, 70);
		setStatPercentage(source, soldier, Stat.TOUGHNESS, 80);
		setStatPercentage(source, soldier, Stat.AGILITY, 50);
		setStatPercentage(source, soldier, Stat.INTUITION, 50);
		setStatPercentage(source, soldier, Stat.INTELLECT, 40);
		setStatPercentage(source, soldier, Stat.WILL, 50);
		soldier.inventory.equip(new ColdIronLongsword());
		soldier.setName("Soldier");
		soldier.setController(source.getController());
		soldier.setTeam(source.getTeam());
		soldier.getController().skipTurn(soldier);
		soldier.setPosition(new Position(source.getX(), source.getY()));
		if(!soldier.tryToMove(1, 0))
			if(!soldier.tryToMove(0, 1))
				if(!soldier.tryToMove(-1, 0))
					if(!soldier.tryToMove(0, -1))
						return null;
		Queue.add(new ChangeEnergy(EnergyType.CONCENTRATION, cost), soldier, source, Condition.DIE, soldier);
		LogDisplay.log(source.getName() + " recruits a soldier to aid in the fight.");
		return soldier;
	}
	
	private void setStatPercentage(Character source, Character target, Stat stat, int percent) {
		target.attributes.setStat(stat, Math.round(source.attributes.getStat(stat) * percent / 100));
	}
}
