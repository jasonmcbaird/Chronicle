package roles.artificer;

import items.weapons.InfernoLongbow;
import ui.LogDisplay;
import utilities.Position;
import ability.Ability;
import character.Character;
import encounter.Encounter;
import enums.Condition;
import enums.Relationship;
import enums.RoleName;
import enums.Stat;

public class InfernoTurret extends Ability {
	
	private final int maxDurability = 1;
	private int durability;
		
	public InfernoTurret() {
		super();
		setName("Inferno Turret");
		setTargetRelationship(Relationship.SELF);
		durability = maxDurability;
	}
	
	public boolean getCanBeActivated(Character character) {
		if(!super.getCanBeActivated(character))
			return false;
		if(canBeActivated)
			if(durability <= 0)
				return false;
		return true;
	}
	
	public void subExecute(Character source, Character target, Condition condition) {
		durability --;
		Encounter.get().add(makeTurret(target));
	}
	
	private Character makeTurret(Character source) {
		Character turret = new Character();
		turret.setRole(RoleName.RANGER, 0);
		setStatPercentage(source, turret, Stat.STRENGTH, 30);
		setStatPercentage(source, turret, Stat.TOUGHNESS, 120);
		setStatPercentage(source, turret, Stat.AGILITY, 170);
		setStatPercentage(source, turret, Stat.INTUITION, 10);
		setStatPercentage(source, turret, Stat.INTELLECT, 10);
		setStatPercentage(source, turret, Stat.WILL, 10);
		turret.inventory.equip(new InfernoLongbow());
		turret.setName("Inferno Turret");
		turret.setController(source.getController());
		turret.setTeam(source.getTeam());
		turret.getController().skipTurn(turret);
		turret.setPosition(new Position(source.getX(), source.getY()));
		turret.attributes.setMoveSpeed(0);
		if(!turret.tryToMove(1, 0))
			if(!turret.tryToMove(0, 1))
				if(!turret.tryToMove(-1, 0))
					if(!turret.tryToMove(0, -1))
						return null;
		LogDisplay.log(source.getName() + " builds an inferno turret.");
		return turret;
	}
	
	private void setStatPercentage(Character source, Character target, Stat stat, int percent) {
		target.attributes.setStat(stat, Math.round(source.attributes.getStat(stat) * percent / 100));
	}
}
