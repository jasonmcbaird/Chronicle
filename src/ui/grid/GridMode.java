package ui.grid;

import mapLogic.Entity;
import enums.Direction;

public interface GridMode {
	
	public void inputDirection(Direction direction);
	public void inputAccept();
	public void inputCancel();
	public Entity getModeEntity();
	
}
