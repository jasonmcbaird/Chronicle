package ui;

import javax.swing.JComponent;
import javax.swing.JPanel;

import utilities.Logger;

public abstract class UIComponent extends JPanel {
	
	protected String name;
	
	public void setName(String string) {
		name = string;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean isFullscreen() {
		return false;
	}

	public void display(JPanel jPanel) {
		throw new UnsupportedOperationException("You tried to display a UIComponent that has no display method.");
	}
	
	public void remove() {
		JComponent parent = (JComponent) getParent();
		Logger.print("Removing from " + parent, -1);
		parent.remove(this);
		parent.repaint();
	}
	
}