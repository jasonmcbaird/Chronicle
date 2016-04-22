package rules;

import queue.Event;
import queue.Queue;
import character.Character;
import enums.Condition;
import enums.EnergyType;
import enums.Sign;
import enums.Stat;

public class Berserk extends Rule {
	
	private StatBuff statBuff;
	private Boolean wasPlayer;
	private Rule loseRage;
	private ChangeEnergy loseRageIfNoAttack;
	private Event preventGainEnergy;
	private boolean berserk = false;
	
	public Berserk() {
		persistent = true;
	}
	
	public void subExecute(Character source, Character target, Condition condition) {
		if(target.getEnergy(EnergyType.RAGE) >= target.getMaxEnergy(EnergyType.RAGE)) {
			berserk(target);
		} else {
			if(target.getEnergy(EnergyType.RAGE) <= 0) {
				subside(target);
			}
		}
	}
	
	private void berserk(Character target) {
		if(!berserk) {
			//Logger.get().print("Berserking");
			berserk = true;
			wasPlayer = target.isPlayer();
			Queue.addAndRun(new Control(false), target, target, Condition.NOW);
			statBuff = new StatBuff(Stat.STRENGTH, 4);
			Queue.add(statBuff, target, target, Condition.NOW);
			Queue.run(Condition.NOW, target);
			
			loseRage = new ChangeEnergy(EnergyType.RAGE, -2);
			loseRage.setPersistent(true);
			Queue.add(loseRage, target, target, Condition.END_TURN);
			
			loseRageIfNoAttack = new ChangeEnergy(EnergyType.RAGE, -2);
			loseRageIfNoAttack.setPersistent(true);
			Queue.add(loseRageIfNoAttack, target, target, Condition.END_TURN);
			NullifyNext nullify = new NullifyNext(loseRageIfNoAttack);
			nullify.setPersistent(true);
			Queue.add(nullify, target, target, Condition.ATTACK);
			
			PreventModifyEnergy prevent = new PreventModifyEnergy(EnergyType.RAGE, Sign.POSITIVE);
			ExecuteOnEventType preventAll = new ExecuteOnEventType(ChangeEnergy.class, prevent);
			preventAll.setPersistent(true);
			preventGainEnergy = preventAll;
			Queue.add(preventGainEnergy, target, target, Condition.CHANGE_ENERGY);
		}
	}
	
	private void subside(Character target) {
		if(berserk) {
			//Logger.get().print("Berserk subsiding");
			berserk = false;
			statBuff.clearBuff(target);
			Queue.addAndRun(new Control(wasPlayer), target, target, Condition.NOW);
			Queue.remove(loseRage);
			Queue.remove(loseRageIfNoAttack);
			Queue.remove(preventGainEnergy);
			statBuff = null;
		}
	}
	
	public int getPriority() {
		return -10;
	}
	
}
