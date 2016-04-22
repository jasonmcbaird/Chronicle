package character;

import java.util.HashMap;

import roles.artificer.Artificer;
import roles.assassin.Assassin;
import roles.berserker.Berserker;
import roles.chronicler.Chronicler;
import roles.fate.Fate;
import roles.general.General;
import roles.mage.Mage;
import roles.necromancer.Necromancer;
import roles.npc.Boss;
import roles.npc.Ire;
import roles.npc.StoneVeins;
import roles.psion.Psion;
import roles.purger.Purger;
import roles.ranger.Ranger;
import roles.rogue.Rogue;
import roles.sylvan.Sylvan;
import roles.warlock.Warlock;
import roles.warp.Warp;
import roles.warrior.Warrior;
import ability.Ability;
import enums.RoleName;

public class RoleBuilder {
	
	public static Role newRole(RoleName roleName) {
		switch (roleName) {
		case CHRONICLER:
			return new Chronicler();
		case WARRIOR:
			return new Warrior();
		case BERSERKER:
			return new Berserker();
		case GENERAL:
			return new General();
		case PURGER:
			return new Purger();
		case FATE:
			return new Fate();
		case NECROMANCER:
			return new Necromancer();
		case WARLOCK:
			return new Warlock();
		case PSION:
			return new Psion();
		case MAGE:
			return new Mage();
		case ARTIFICER:
			return new Artificer();
		case WARP:
			return new Warp();
		case ASSASSIN:
			return new Assassin();
		case RANGER:
			return new Ranger();
		case ROGUE:
			return new Rogue();
		case SYLVAN:
			return new Sylvan();
		case DEMON:
			HashMap<Ability, Integer> hash = new HashMap<Ability, Integer>();
			hash.put(new Ire(), 3);
			hash.put(new StoneVeins(), 0);
			return new Boss("Demon", hash);
		default:
			return null;
		}
	}

}
