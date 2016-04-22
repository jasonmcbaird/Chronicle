package enums;

public enum Condition {

	ACTIVATE_ABILITY, TAKE_DAMAGE, HEAL, MOVE, START_TURN, ATTACK, END_ENCOUNTER, END_TURN, CHANGE_ENERGY,
	DODGE, DIE, KILL, NOW, DEAL_DAMAGE, COOLDOWN_FINISH, AFTER
	
	// Attack currently means use an ability on an enemy.
	
	// On Set: Heal, Take_Damage, Gain_Energy, Die, Kill
	// On Action: Activate_Ability, Attack, Move
	// On Phase: Start_Turn, End_Turn, End_Encounter
	// Misc: Now, Dodge
}