package roles.warp;

import mapLogic.GridLogic;
import queue.Queue;
import rules.Move;
import rules.WeaponAttack;
import utilities.Dice;
import ability.Ability;
import character.Character;
import enums.Condition;
import enums.Direction;

public class Backstab extends Ability {
		
	public Backstab() {
		setName("Backstab");
	}
	
	public void subExecute(Character source, Character target, Condition condition) {
		int attackPower = -2;
		Move teleportBehind = new Move(GridLogic.getDirection(source, target), 2);
		Queue.addAndRun(teleportBehind, source, source, Condition.NOW);
		if(teleportBehind.succeeded())
			attackPower = 3;
		else
			if(teleportSide(source, target))
				attackPower = 0;
		Queue.addAndRun(new WeaponAttack(source, attackPower), source, target, Condition.ATTACK);
	}
	
	public boolean teleportSide(Character source, Character target) {
		int random = getRandomMinusPlus();
		
		Move teleportSide = getTeleport(GridLogic.getDirection(source, target), random);
		
		Queue.addAndRun(teleportSide, source, source, Condition.NOW);
		if(teleportSide.succeeded())
			return true;
		
		Move teleportOtherSide = getTeleport(GridLogic.getDirection(source, target), -random);
		
		Queue.addAndRun(teleportOtherSide, source, source, Condition.NOW);
		if(teleportOtherSide.succeeded())
			return true;
		return false;
	}
	
	public Move getTeleport(Direction direction, int random) {
		switch(direction) {
		case UP:
			return new Move(random, -1);
		case DOWN:
			return new Move(random, 1);
		case LEFT:
			return new Move(-1, random);
		case RIGHT:
			return new Move(1, random);
		}
		return null;
	}
	
	public int getRandomMinusPlus() {
		int roll = Dice.rollDie(2);
		if(roll == 1)
			return 1;
		else
			return -1;
	}
	
	public int getSeconds(Character character) {
		return 5;
	}
	
}
