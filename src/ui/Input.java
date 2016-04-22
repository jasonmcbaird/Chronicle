package ui;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import utilities.Logger;
import enums.Direction;

public class Input {
	
	public Input() {
	}
	
	protected void addBindings() {
		InputResponder responder = UIStack.getResponder();
		if(responder == null) {
			Logger.print("Null responder");
			return;
		}
		Logger.print("Setting up input on " + responder, -2);
        responder.getInputMap().put(KeyStroke.getKeyStroke("W"), "Move up");
        responder.getInputMap().put(KeyStroke.getKeyStroke("UP"), "Move up");
        responder.getActionMap().put("Move up", new MoveAction(Direction.UP));
        
        responder.getInputMap().put(KeyStroke.getKeyStroke("A"), "Move left");
        responder.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "Move left");
        responder.getActionMap().put("Move left", new MoveAction(Direction.LEFT));
        
        responder.getInputMap().put(KeyStroke.getKeyStroke("S"), "Move down");
        responder.getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "Move down");
        responder.getActionMap().put("Move down", new MoveAction(Direction.DOWN));
        
        responder.getInputMap().put(KeyStroke.getKeyStroke("D"), "Move right");
        responder.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "Move right");
        responder.getActionMap().put("Move right", new MoveAction(Direction.RIGHT));
        
        responder.getInputMap().put(KeyStroke.getKeyStroke("Z"), "Action");
        responder.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "Action");
        responder.getActionMap().put("Action", new AcceptAction());
        
        responder.getInputMap().put(KeyStroke.getKeyStroke("X"), "Cancel");
        responder.getInputMap().put(KeyStroke.getKeyStroke("ESCAPE"), "Cancel");
        responder.getActionMap().put("Cancel", new CancelAction());
        
        responder.getInputMap().put(KeyStroke.getKeyStroke("Q"), "Info");
        responder.getInputMap().put(KeyStroke.getKeyStroke("/"), "Info");
        responder.getActionMap().put("Info", new InfoAction());
    }
    
    private class MoveAction extends AbstractAction {

    	Direction direction;
    	
        MoveAction(Direction newDirection) {
        	direction = newDirection;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
        	Logger.print("Input: " + direction, -1);
        	UIStack.getResponder().inputDirection(direction);
        }
        
    }
    private class CancelAction extends AbstractAction {
    	
    	@Override
    	public void actionPerformed(ActionEvent e) {
    		Logger.print("Input: Cancel", -1);
        	UIStack.getResponder().inputCancel();
    	}
    }
    
    private class AcceptAction extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
    		Logger.print("Input: Accept", -1);
        	UIStack.getResponder().inputAccept();
        }
    }
    
    private class InfoAction extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
    		Logger.print("Input: Info", -1);
        	UIStack.getResponder().inputInfo();
        }
    }
}
