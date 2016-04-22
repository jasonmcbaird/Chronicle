package ui;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import ui.grid.GridInterface;
import utilities.Position;
import character.Role;
import character.Team;
import controller.Controller;
import enums.Relationship;
import enums.Stat;

public interface CharacterInterface {
	
	public boolean tryToMove(int xChange, int yChange);
	
	public ArrayList<String> getAbilityCategoryNames();
	public ArrayList<Role> getRoles();
	public ArrayList<String> getAbilityNames(String role);
	public void activateAbility(String string, CharacterInterface target);
	public int getRange(String abilityName);
	
	public int getX();
	public int getY();
	public int getMoveSpeed();
	public boolean isReady();
	
	public int getHealth();
	public int getMaxHealth();
	public Controller getController();
	public int getGeneralLevel();

	public Relationship getRelationship(CharacterInterface target);
	public Relationship getRelationship(Team team);
	
	public boolean isValidAbilityTarget(String string, CharacterInterface target);
	public void startTurn(GridInterface grid);
	public boolean tryToEndTurn(GridInterface grid);
	public void endTurn(GridInterface grid);
	
	public String getName();
	public int getStat(Stat stat);
	public Position getPosition();
	
	public void extraTurn();
	
	public boolean isPlayer();
	
	public BufferedImage getImage();

}
