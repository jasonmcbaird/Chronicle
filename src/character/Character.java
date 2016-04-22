package character;

import items.SwitchWeapon;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;

import mapLogic.Entity;
import mapLogic.GridLogic;
import queue.Queue;
import rules.EndTurn;
import rules.Move;
import ui.CharacterInterface;
import ui.grid.GridInterface;
import utilities.Dice;
import utilities.Logger;
import ability.Ability;
import ability.MutableAbility;
import controller.Controller;
import enums.Condition;
import enums.DamageType;
import enums.EnergyType;
import enums.Relationship;
import enums.RoleName;
import enums.Stat;

public class Character extends Entity implements CharacterInterface {
	
	private int generalLevel;
	private int currentGenLevelXP, nextGenLevelXP;
	private ArrayList<Role> roles = new ArrayList<Role>();
	private BufferedImage characterImage;
	private Controller controller;
	private Stat mainStat;

	private Team team;
	public Attributes attributes;
	public Inventory inventory;
	
	public Character() {
		attributes = new Attributes();
		attributes.setCurrentHealth(attributes.getMaxHealth());
		attributes.setStat(Stat.STRENGTH, 5);
		attributes.setStat(Stat.TOUGHNESS, 5);
		attributes.setStat(Stat.AGILITY, 5);
		attributes.setStat(Stat.INTUITION, 5);
		attributes.setStat(Stat.INTELLECT, 5);
		attributes.setStat(Stat.WILL, 5);
		attributes.setMoveSpeed(4);
		mainStat = Stat.STRENGTH;
		
		inventory = new Inventory(this);
		
		setRole(RoleName.CHRONICLER, 0);
		
		startingRole().setLevel(1);
		setGeneralLevel(0);
		setCurrentGenLevelExp(0);
		
		setGenExpReq();
		
		name = "Codybitch";
		controller = Controller.getDefaultAIController();
		team = Team.getEnemyTeam();
		try {
			setCharacterImage(ImageIO.read(this.getClass().getResource("/res/feLordSprites/Alm.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setTeam(Team newTeam) {
		team = newTeam;
	}
	
	public Team getTeam() {
		return team;
	}
	
	private Role startingRole() {
		return getRole(0);
	}
	
	public void setController(Controller controller) {
		this.controller = controller;
	}
	
	public Controller getController() {
		return controller;
	}
	
	public void heal(int amount) {
		attributes.setCurrentHealth(attributes.getCurrentHealth() + amount);
		if(attributes.getCurrentHealth() > attributes.getMaxHealth()) {
			attributes.setCurrentHealth(attributes.getMaxHealth());
		}
	}
	
	public void takeDamage(int damage, DamageType damageType) {
		attributes.setCurrentHealth(attributes.getCurrentHealth() - damage);
		if(attributes.getCurrentHealth() < 0) {
			attributes.setCurrentHealth(0);
		}
	}
	
	public int getXPValue() {
		return generalLevel;
	}
	
	public void gainXP(int gain) {
		currentGenLevelXP += gain;
		if(currentGenLevelXP >= nextGenLevelXP)
			generalLevelUp();
	}
	
	// METHOD: setGenExpReq
	//   Called whenever a level-up or new character occurs.
	//   Sets general experience requirement for next level-up.
	public void setGenExpReq()
	{
		// This current formula is COMPLETELY ARBITRARY
		//   and will be changed eventually.
		nextGenLevelXP = (generalLevel * 8);
	}
	
	public void startEncounter() {
		for(Role role: roles) {
			role.activatePassives(this);
		}
		resetEnergy();
		inventory.startEncounter();
	}
	
	public boolean changeEnergy(EnergyType energyType, int amount) {
		boolean added = false;
		for(Role role: roles) {
			if(roleGainEnergy(energyType, amount, role))
				added = true;
		}
		return added;
	}
	
	private boolean roleGainEnergy(EnergyType energyType, int amount, Role role) {
		if(role.gainEnergy(energyType, amount)) {
			return true;
		}
		return false;
	}
	
	public int getEnergy(EnergyType energyType) {
		int amount = 0;
		for(Role role: roles) {
			amount += role.getEnergy(energyType);
		}
		return amount;
	}
	
	public int getMaxEnergy(EnergyType energyType) {
		int amount = 0;
		for(Role role: roles)
			if(amount < role.getMaxEnergy(energyType))
				amount = role.getMaxEnergy(energyType);
		return amount;
	}
	
	public void setMaxEnergy(EnergyType energyType, int i) {
		for(Role role: roles)
			role.setMaxEnergy(energyType, i);
	}
	
	public void resetEnergy() {
		for(Role role: roles)
			role.resetEnergy();
	}
	
	public void resetEnergy(EnergyType energyType) {
		for(Role role: roles)
			role.resetEnergy(energyType);
	}
	
	// METHOD: generalLevelUp
	//   Called on general level-up. Sets the new level,
	//   calls setGenExpReq, and accounts for "overflow"
	//   current experience for the next level.
	//   Should also increase stats.
	public void generalLevelUp() {
		if(currentGenLevelXP >= nextGenLevelXP)
			currentGenLevelXP = currentGenLevelXP - nextGenLevelXP;
		else
			currentGenLevelXP = 0;
		
		if(generalLevel < 40) { // Level cap is 40
			generalLevel++;
			Logger.print(getName() + " levels up to " + generalLevel + ".");
			statUp();
		}
		setGenExpReq();
	}
	
	public int getGeneralLevel(){
		return generalLevel;
	}
	
	public int getCurrentGenLevelExp(){
		return currentGenLevelXP;
	}
	
	public int getNextGenLevelExp(){
		return nextGenLevelXP;
	}
	
	public BufferedImage getImage(){
		return characterImage;
	}
	
	public void setGeneralLevel(int gLevel){
		generalLevel = gLevel;
		setGenExpReq();
	}
	
	public void setCurrentGenLevelExp(int cgLevelExp){
		currentGenLevelXP = cgLevelExp;
	}
	
	public void setNextGenLevelExp(int ngLevelExp){
		nextGenLevelXP = ngLevelExp;
	}
	
	public void setCharacterImage(BufferedImage image){
		characterImage = image;
	}
	
	public void setCharacterImage(String string) {
		try {
			setCharacterImage(ImageIO.read(this.getClass().getResource(string)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setCharacterImage(File image) {
		try {
			characterImage = ImageIO.read(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setRole(RoleName roleName, int index) {
		Role role = RoleBuilder.newRole(roleName);
		if(index < roles.size()) {
			roles.set(index, role);
			getRole(index).setLevel(1);
		} else
			if(roles.size() == index)
				roles.add(role);
		if(index == 0)
			setCharacterImage(role.getDefaultImage());
	}
	
	public void addRole(RoleName roleName) {
		int roleSize = roles.size();
		if(getRole(RoleName.CHRONICLER) != null)
			roleSize--;
		if(roleSize <= 3 && canAddNewRole())
			roles.add(RoleBuilder.newRole(roleName));
	}
	
	public Role getRole(int index) {
		return roles.get(index);
	}
	
	public Role getRole(RoleName name) {
		for(Role role: getRoles())
			if(role.getRoleName() == name)
				return role;
		return null;
	}
	
	public ArrayList<Role> getRoles() {
		return roles;
	}
	
	public ArrayList<Ability> getActivatedAbilities(Role role) {
		return role.getAbilitiesFromLevel(this);
	}
	
	public ArrayList<Ability> getActivatedAbilities() {
		ArrayList<Ability> abilities = new ArrayList<Ability>();
		abilities.addAll(inventory.getAbilities());
		for(Role role: roles)
			abilities.addAll(getActivatedAbilities(role));
		return addAttackAbility(abilities);
	}
	
	public ArrayList<Ability> addAttackAbility(ArrayList<Ability> abilities) {
		if(abilities.size() == 0) {
			abilities.add(new MutableAbility());
			// If you don't have any abilities, get a default attack ability.
			// TODO: Remove this if statement, probably. Right now, only AI can see it.
		}
		return abilities;
	}
	
	public void setRoleLevel(int index, int level) {
		getRole(index).setLevel(level);
	}
	
	public int getClassLevel(int index) {
		return getRole(index).getLevel();
	}
	
	public boolean getCanOverlap() {
		return false;
	}
	
	public ArrayList<String> getAbilityNames(String string) {
		Role role = getRole(string);
		if(role != null)
			return getRoleAbilityNames(role);
		return inventory.getEquipmentAbilities(string);
	}
	
	private ArrayList<String> getRoleAbilityNames(Role role) {
		ArrayList<Ability> abilities = getActivatedAbilities(role);
		ArrayList<String> names = new ArrayList<String>();
		for(Ability ability: abilities)
			names.add(ability.getName());
		return names;
	}
	
	private Role getRole(String string) {
		for(Role role: roles)
			if(role.getName() == string)
				return role;
		return null;
	}
	
	public void activateAbility(String string, CharacterInterface target) {
		Ability ability = getAbility(string);
		if(ability != null)
			Queue.addAndRun(ability, this, (Character) target, Condition.ACTIVATE_ABILITY);
	}

	public int getHealth() {
		return attributes.getCurrentHealth();
	}

	public int getMaxHealth() {
		return attributes.getMaxHealth();
	}
	
	public boolean isPlayer() {
		if(controller == Controller.getPlayerController())
			return true;
		return false;
	}
	
	public void setIsPlayer(boolean bool) {
		if(bool)
			controller = Controller.getPlayerController();
		else
			controller = Controller.getDefaultAIController();
	}
	
	public boolean isReady() {
		Logger.print("Testing readiness on " + name, -1);
		return getTeam().getController().isReady(this);
	}
	
	public boolean hasActed() {
		return getTeam().getController().checkHasActed(this);
	}
	
	public Relationship getRelationship(Character target) {
		if(this == target)
			return Relationship.SELF;
		return team.getRelationship(target.getTeam());
	}
	
	public Relationship getRelationship(CharacterInterface target) {
		return getRelationship((Character) target);
	}
	
	public Relationship getRelationship(Team team) {
		if(getTeam() == team)
			return Relationship.SELF;
		return getTeam().getRelationship(team);
	}
	
	public boolean isValidAbilityTarget(String string, CharacterInterface target) {
		Ability ability = getAbility(string);
		if(ability != null)
			if(getRelationship(target) == ability.getRelationship())
				return withinRange(ability, (Character) target);
		return false;
	}
	
	public boolean withinRange(Ability ability, Character target) {
		return GridLogic.getDistance(this, target) <= ability.getRange(this);
	}
	
	public Ability getAbility(String string) {
		for(Ability ability: getActivatedAbilities())
			if(ability.getName() == string)
				return ability;
		if(string == "Attack")
			return new MutableAbility();
		if(string == "Switch Weapon")
			return new SwitchWeapon();
		return null;
	}
	
	public void startTurn(GridInterface grid) {
		controller.startTurn(this, grid);
	}
	
	public boolean tryToEndTurn(GridInterface grid) {
		EndTurn endTurn = new EndTurn(grid);
		Queue.addAndRun(endTurn, this, this, Condition.ACTIVATE_ABILITY);
		if(endTurn.successful())
			return true;
		return false;
	}
	
	public void endTurn(GridInterface grid) {
		Queue.run(Condition.END_TURN, this);
		getTeam().getController().endTurn(this, grid);
	}
	
	public boolean tryToMove(int xChange, int yChange) {
		Move move = new Move(xChange, yChange);
		Queue.addAndRun(move, this, this, Condition.MOVE);
		Logger.print("Move succeeded? " + move.succeeded());
		return move.succeeded();
	}
	
	public int getStat(Stat stat) {
		return attributes.getStat(stat);
	}
	
	public Stat getMainStat() {
		return mainStat;
	}
	
	public void setMainStat(Stat stat) {
		mainStat = stat;
	}
	
	public int getRange(String abilityName) {
		return getAbility(abilityName).getRange(this);
	}
	
	public void extraTurn() {
		getController().extraTurn(this);
	}
	
	public int getMoveSpeed() {
		return attributes.getMoveSpeed();
	}

	public ArrayList<String> getAbilityCategoryNames() {
		ArrayList<String> abilityCategoryNames = new ArrayList<String>();
		abilityCategoryNames.addAll(inventory.getActiveWeaponNames());
		abilityCategoryNames.addAll(getRoleNames());
		return abilityCategoryNames;
	}
	
	public ArrayList<String> getRoleNames() {
		ArrayList<String> roleNames = new ArrayList<String>();
		for(Role role: roles)
			roleNames.add(role.getName());
		return roleNames;
	}
	
	public boolean canAddNewRole() {
		int roleSize = roles.size();
		if(getRole(RoleName.CHRONICLER) != null)
			roleSize--;
		HashMap<Integer, Integer> roleAtLevel = new HashMap<Integer, Integer>();
		roleAtLevel.put(0, 0);
		roleAtLevel.put(1, 10);
		roleAtLevel.put(2, 20);
		roleAtLevel.put(3, 30);
		Integer necessaryLevel = roleAtLevel.get(roleSize);
		if(necessaryLevel != null && generalLevel >= necessaryLevel)
			return true;
		return false;
	}
	
	private void statUp() {
		switch (getGeneralLevel() % 5) {
		case 0:
			boostStat(getMainStat());
			return;
		case 1:
			boostStat(getRandomAlliedStat());
			return;
		case 2:
			boostStat(getMainStat());
			return;
		case 3:
			boostStat(getRandomAlliedStat());
			return;
		case 4:
			boostStat(getRandomEnemyStat());
			return;
		}
	}
	
	private void boostStat(Stat stat) {
		attributes.changeStat(stat, 1);
		Logger.print("Increasing " + stat.name() + " to " + attributes.getStat(stat) + " at level " + getGeneralLevel(), -1);
	}
	
	private Stat getRandomAlliedStat() {
		return random(Stat.getAlliedStats(getMainStat()));
	}
	
	private Stat random(ArrayList<Stat> stats) {
		return stats.get(Dice.rollDie(stats.size()) - 1);
	}
	
	private Stat getRandomEnemyStat() {
		return random(Stat.getEnemyStats(getMainStat()));
	}
}
