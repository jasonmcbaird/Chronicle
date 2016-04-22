package ui;

import javax.swing.ActionMap;
import javax.swing.InputMap;

import enums.Direction;

public interface InputResponder {
	
	public void inputDirection(Direction direction);
	public void inputAccept();
	public void inputCancel();
	public void inputInfo();
	public InputMap getInputMap();
	public ActionMap getActionMap();

}