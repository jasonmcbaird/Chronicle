package ui.grid;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import mapLogic.Entity;
import ui.CharacterInterface;
import ui.InputResponder;
import ui.UIComponent;
import utilities.Position;
import enums.Direction;

public class Grid extends UIComponent implements InputResponder {
	
	private final int gridSize = 32;
	protected ArrayList<Entity> entities = new ArrayList<Entity>();
	
	public Grid() {
		setName("Grid");
	}
	
	public Grid(int w, int h) {
		setName("Grid");
        setPreferredSize(new Dimension(w, h));
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		//Logger.print("Repainting");
		drawGrid(g);
		//Logger.print("Entities in " + this + ": " + entities);
		
		for(Entity entity: entities) {
			//Logger.print("Printing entity: " + entity);
			drawEntity(entity, entity.getX(), entity.getY(), g);
		}
	}

	public ArrayList<Entity> getEntitiesAt(Position position) {
		ArrayList<Entity> correctEntities = new ArrayList<Entity>();
		for(Entity entity: entities)
			if(position.equalsPosition(entity.getPosition()))
				correctEntities.add(entity);
		return correctEntities;
	}
	
	public void add(Entity entity) {
		entities.add(entity);
		//Logger.print("Entities in " + this + ": " + entities);
	}

	public void remove(Entity entity) {
		/**Logger.print("Removing " + entity);
		Logger.print("From list:");
		for(Entity logEntity: entities) {
			Logger.print(logEntity + "");
		}*/
		entities.remove(entity);
	}
	
	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}
	
	protected void drawGrid(Graphics g) {
		int width = getWidth();
	    int height = getHeight();
	    
	    drawVerticalGrid(g, width, height);
	    drawHorizontalGrid(g, width, height);
	}
	
	private void drawVerticalGrid(Graphics g, int width, int height) {
		int xstart=0;
	    for(int i = 1; i <= width / gridSize; i++) {
	        xstart = i*(gridSize);
	        g.drawLine(xstart, 0, xstart, height);
	    }
	}
	
	private void drawHorizontalGrid(Graphics g, int width, int height) {
		int ystart=0;
	    for(int i = 1; i <= height / gridSize; i++) {
	        ystart = i*(gridSize);
	        g.drawLine(0, ystart, width, ystart);
	    }
	}
	
	protected void drawEntity(Entity entity, int x, int y, Graphics g) {
		g.drawImage(entity.getImage(), getAlignedX(entity, x), getAlignedY(entity, y), null);
	}
	
	private int getAlignedX(Entity entity, int x) {
		int mid = gridXToPixelX(x) + gridSize/2;
		int left = mid - entity.getImage().getWidth() / 2;
		return left;
	}
	
	private int getAlignedY(Entity entity, int y) {
		int bottom = gridYToPixelY(y + 1);
		int top = bottom - entity.getImage().getHeight();
		return top;
	}
	
	private int gridXToPixelX(int grid) {
		return grid * gridSize;
	}
	
	private int gridYToPixelY(int grid) {
		return 	grid * gridSize;
	}
	
	public boolean isFullscreen() {
		return true;
	}
	
	public void display(JPanel jPanel) {
		//Logger.print("Displaying grid");
		jPanel.add(this);
		jPanel.revalidate();
		jPanel.repaint();
	}
	
	public void inputDirection(Direction direction) {
		
	}
	
	public void inputAccept() {

	}
	
	public void inputCancel() {
		
	}
	
	public void inputInfo() {
		
	}
	
	protected ArrayList<CharacterInterface> getCharacters() {
		ArrayList<CharacterInterface> characters = new ArrayList<CharacterInterface>();
		for(Entity entity: entities)
			if(entity instanceof CharacterInterface)
				characters.add((CharacterInterface) entity);
		return characters;
	}

}
