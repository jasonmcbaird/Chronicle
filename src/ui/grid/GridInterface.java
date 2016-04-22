package ui.grid;

import java.util.ArrayList;

import mapLogic.Entity;
import ui.CharacterInterface;

public interface GridInterface {
	
	public void add(Entity entity);
	public void remove(Entity entity);
	public void setEntities(ArrayList<Entity> entities);
	public void removeAll();
	public void repaint();
	public void playerTurn();
	public void startTurn(CharacterInterface character);
	public void aiTurn();
	public boolean isPlayerTurn();
	public void waitMilliseconds(int milliseconds);

}
