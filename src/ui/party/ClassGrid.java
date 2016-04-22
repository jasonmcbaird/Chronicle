package ui.party;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import ui.InputResponder;
import ui.UIComponent;
import ui.UIStack;
import utilities.TextExtension;
import character.Character;
import character.Role;
import enums.Direction;
import enums.RoleName;

public class ClassGrid extends UIComponent implements InputResponder, PartyMenuBuilder {
	
	private final int hexSize = 60;
	private final int hexLineThickness = 4;
	
	private final int fontSize = 13;
	private final String fontName = "Verdana";
	
	private ArrayList<RoleHex> hexes = new ArrayList<RoleHex>();
	private RoleHex selectedHex;
	
	private Character character;
	
	public ClassGrid() {
		
	}
	
	public ClassGrid(Character character) {
		this.character = character;
	}
	
	public void setCharacter(Character character) {
		this.character = character;
	}
	
	public boolean isFullscreen() {
		return true;
	}
	
	public void inputDirection(Direction direction) {
		switch (direction) {
		case DOWN:
			moveDown();
			repaint();
			return;
		case UP:
			moveUp();
			repaint();
			return;
		case LEFT:
			moveLeft();
			repaint();
			return;
		case RIGHT:
			moveRight();
			repaint();
			return;
		default:
			return;
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		removeAll();
		if(hexes.size() < 1) {
			hexes.add(createRoleHex(RoleName.GENERAL, this.getWidth() / 2 - hexSize / 2, 150));
			hexes.add(createRoleHex(RoleName.WARRIOR, getRoleHex(RoleName.GENERAL), HexDirection.BOTTOM));
			hexes.add(createRoleHex(RoleName.PURGER, getRoleHex(RoleName.GENERAL), HexDirection.BOTTOM_RIGHT));
			hexes.add(createRoleHex(RoleName.FATE, getRoleHex(RoleName.PURGER), HexDirection.BOTTOM_RIGHT));
			hexes.add(createRoleHex(RoleName.NECROMANCER, getRoleHex(RoleName.FATE), HexDirection.BOTTOM));
			hexes.add(createRoleHex(RoleName.WARLOCK, getRoleHex(RoleName.NECROMANCER), HexDirection.BOTTOM));
			hexes.add(createRoleHex(RoleName.MAGE, getRoleHex(RoleName.NECROMANCER), HexDirection.BOTTOM_LEFT));
			hexes.add(createRoleHex(RoleName.PSION, getRoleHex(RoleName.MAGE), HexDirection.BOTTOM));
			hexes.add(createRoleHex(RoleName.ARTIFICER, getRoleHex(RoleName.PSION), HexDirection.BOTTOM_LEFT));
			hexes.add(createRoleHex(RoleName.WARP, getRoleHex(RoleName.ARTIFICER), HexDirection.TOP_LEFT));
			hexes.add(createRoleHex(RoleName.ROGUE, getRoleHex(RoleName.WARP), HexDirection.TOP));
			hexes.add(createRoleHex(RoleName.ASSASSIN, getRoleHex(RoleName.WARP), HexDirection.TOP_LEFT));
			hexes.add(createRoleHex(RoleName.RANGER, getRoleHex(RoleName.ASSASSIN), HexDirection.TOP));
			hexes.add(createRoleHex(RoleName.SYLVAN, getRoleHex(RoleName.RANGER), HexDirection.TOP));
			hexes.add(createRoleHex(RoleName.BERSERKER, getRoleHex(RoleName.SYLVAN), HexDirection.TOP_RIGHT));
			setRelationships();
			if(character == null)
				selectedHex = getRoleHex(RoleName.WARRIOR);
			else {
				if(getRoleNames(character).contains(RoleName.CHRONICLER))
					hexes.add(createRoleHex(RoleName.CHRONICLER, getRoleHex(RoleName.WARRIOR), HexDirection.BOTTOM));
				selectedHex = getRoleHex(getRoleNames(character).get(0));
			}
		}
		for(RoleHex hex: hexes)
			paintHex(hex, g);
	}
	
	private void paintHex(RoleHex hex, Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(hexLineThickness));
		g2.setColor(Color.BLACK);
		g2.drawPolygon(hex.getHexagon());
		g2.setColor(RoleName.getColor(hex.getName()));
		grayIfTakenRole(hex, g2);
		darkenIfSelected(hex, g2);
		darkenIfAvailable(hex, g2);
		g2.fillPolygon(hex.getHexagon());
		
		JLabel jlabel = new JLabel(TextExtension.toFirstCase(hex.getName().toString()), SwingConstants.CENTER);
		Font font = new Font(fontName, 1, fontSize);
		FontMetrics metrics = g.getFontMetrics(font);
		int textHeight = metrics.getHeight();
		int labelX = hex.getHexagon().xpoints[0] - getHexagonDiagonalX(getHexagonSideLength(hex.getHexagon()));
		int labelY = hex.getHexagon().ypoints[0] + getHexagonDiagonalY(getHexagonSideLength(hex.getHexagon())) - textHeight / 2;
		int labelWidth = getHexagonSideLength(hex.getHexagon()) + getHexagonDiagonalX(getHexagonSideLength(hex.getHexagon())) * 2;
        jlabel.setBounds(labelX, labelY, labelWidth, textHeight);
        jlabel.setFont(font);
        add(jlabel);
	}
	
	private void darkenIfSelected(RoleHex hex, Graphics g2) {
		if(hex == selectedHex)
			g2.setColor(g2.getColor().darker());
	}
	
	private void grayIfTakenRole(RoleHex hex, Graphics g2) {
		if(character != null && getRoleNames(character).contains(hex.getName()))
			g2.setColor(Color.GRAY);
	}
	
	private void darkenIfAvailable(RoleHex hex, Graphics2D g2) {
		if(character != null && getAvailableRoles(character).contains(hex.getName())) {
			g2.setColor(g2.getColor().darker());
			/**g2.setColor(Color.BLACK);
			g2.setStroke(new BasicStroke(hexLineThickness));
			g2.drawPolygon(createMiniHexagon(hex.getHexagon()));*/
		}
	}
	
	private Polygon createMiniHexagon(Polygon bigPolygon) {
		int bigSideLength = getHexagonSideLength(bigPolygon);
		int bigX = bigPolygon.xpoints[0];
		int bigY = bigPolygon.ypoints[0];
		int bigDiagonalY = getHexagonDiagonalY(bigSideLength);
		
		int sideLength = (int) Math.round(bigSideLength * 0.9);
		int diagonalX = getHexagonDiagonalX(sideLength);
		int diagonalY = getHexagonDiagonalY(sideLength);
		int x = bigX + (bigSideLength - sideLength) / 2;
		int y = bigY + bigDiagonalY - diagonalY;
		
		int[] xCoordinates = {x, x + sideLength, x + sideLength + diagonalX, x + sideLength, x, x - diagonalX};
		int[] yCoordinates = {y, y, y + diagonalY, y + 2 * diagonalY, y + 2 * diagonalY, y + diagonalY};
		
		Polygon polygon = new Polygon(xCoordinates, yCoordinates, 6);
		return polygon;
	}
	
	private ArrayList<RoleName> getRoleNames(Character character) {
		ArrayList<RoleName> roleNames = new ArrayList<RoleName>();
		for(Role role: character.getRoles())
			roleNames.add(role.getRoleName());
		return roleNames;
	}
	
	public void display(JPanel jPanel) {
		jPanel.add(this);
		jPanel.revalidate();
		jPanel.repaint();
	}
	
	public void inputAccept() {
		if(getAvailableRoles(character).contains(selectedHex.getName()))
			character.addRole(selectedHex.getName());
		repaint();
	}
	
	public void inputInfo() {
		
	}
	
	public void inputCancel() {
		remove();
	}
	
	private void moveUp() {
		selectHex(selectedHex.getRelationship(Direction.UP));
	}
	
	private void moveDown() {
		selectHex(selectedHex.getRelationship(Direction.DOWN));
	}

	private void moveLeft() {
		selectHex(selectedHex.getRelationship(Direction.LEFT));
	}

	private void moveRight() {
		selectHex(selectedHex.getRelationship(Direction.RIGHT));
	}
	
	private Polygon createHexagon(int x, int y, int sideLength) {
		if(sideLength == 0)
			return new Polygon();
		int diagonalX = getHexagonDiagonalX(sideLength);
		int diagonalY = getHexagonDiagonalY(sideLength);
		
		int[] xCoordinates = {x, x + sideLength, x + sideLength + diagonalX, x + sideLength, x, x - diagonalX};
		int[] yCoordinates = {y, y, y + diagonalY, y + 2 * diagonalY, y + 2 * diagonalY, y + diagonalY};
		
		Polygon polygon = new Polygon(xCoordinates, yCoordinates, 6);
		return polygon;
	}
	
	private Polygon createTransformedHexagon(Polygon polygon, HexDirection direction) {
		int xChange = 0;
		int yChange = 0;
		int sideLength = getHexagonSideLength(polygon);
		switch(direction) {
		case TOP:
			yChange = -2 * getHexagonDiagonalY(sideLength);
			return createShiftedHexagon(xChange, yChange, polygon);
		case BOTTOM:
			yChange = 2 * getHexagonDiagonalY(sideLength);
			return createShiftedHexagon(xChange, yChange, polygon);
		case TOP_LEFT:
			xChange = -(sideLength + getHexagonDiagonalX(sideLength));
			yChange = -getHexagonDiagonalY(sideLength);
			return createShiftedHexagon(xChange, yChange, polygon);
		case TOP_RIGHT:
			xChange = sideLength + getHexagonDiagonalX(sideLength);
			yChange = -getHexagonDiagonalY(sideLength);
			return createShiftedHexagon(xChange, yChange, polygon);
		case BOTTOM_LEFT:
			xChange = -(sideLength + getHexagonDiagonalX(sideLength));
			yChange = getHexagonDiagonalY(sideLength);
			return createShiftedHexagon(xChange, yChange, polygon);
		case BOTTOM_RIGHT:
			xChange = sideLength + getHexagonDiagonalX(sideLength);
			yChange = getHexagonDiagonalY(sideLength);
			return createShiftedHexagon(xChange, yChange, polygon);
		default:
			return polygon;
		}
	}
	
	private Polygon createShiftedHexagon(int xChange, int yChange, Polygon polygon) {
		int lastX = polygon.xpoints[0];
		int lastY = polygon.ypoints[0];
		int sideLength = getHexagonSideLength(polygon);
		
		int newX = lastX + xChange;
		int newY = lastY + yChange;
		return createHexagon(newX, newY, sideLength);
	}
	
	private int getHexagonSideLength(Polygon polygon) {
		int x1 = polygon.xpoints[0];
		int x2 = polygon.xpoints[1];
		
		return Math.abs(x2 - x1);
	}
	
	private int getHexagonDiagonalX(int sideLength) {
		return sideLength / 2;
	}
	
	private int getHexagonDiagonalY(int sideLength) {
		return (int) Math.round(sideLength * Math.sqrt(3.0) / 2);
	}
	
	private RoleHex getRoleHex(RoleName name) {
		for(RoleHex hex: hexes)
			if(hex.getName() == name)
				return hex;
		return null;
	}
	
	private RoleHex createRoleHex(RoleName name, int x, int y) {
		Polygon hexagon = createHexagon(x, y, hexSize);
		return new RoleHex(hexagon, name);
	}
	
	private RoleHex createRoleHex(RoleName name, RoleHex adjacentHex, HexDirection direction) {
		Polygon hexagon = createTransformedHexagon(adjacentHex.getHexagon(), direction);
		return new RoleHex(hexagon, name);
	}
	
	private void setRelationships() {
		setRelationships(RoleName.GENERAL, null, RoleName.BERSERKER, RoleName.PURGER, RoleName.WARRIOR);
		setRelationships(RoleName.WARRIOR, RoleName.GENERAL, RoleName.BERSERKER, RoleName.PURGER, RoleName.CHRONICLER);
		setRelationships(RoleName.BERSERKER, RoleName.GENERAL, RoleName.SYLVAN, RoleName.WARRIOR, null);
		setRelationships(RoleName.PURGER, RoleName.GENERAL, RoleName.WARRIOR, RoleName.FATE, null);
		
		setRelationships(RoleName.NECROMANCER, RoleName.FATE, RoleName.MAGE, null, RoleName.WARLOCK);
		setRelationships(RoleName.MAGE, RoleName.NECROMANCER, RoleName.CHRONICLER, RoleName.WARLOCK, RoleName.PSION);
		setRelationships(RoleName.WARLOCK, RoleName.NECROMANCER, RoleName.MAGE, null, RoleName.PSION);
		setRelationships(RoleName.PSION, RoleName.MAGE, RoleName.ARTIFICER, RoleName.WARLOCK, null);
		
		setRelationships(RoleName.WARP, RoleName.ROGUE, RoleName.ASSASSIN, RoleName.ARTIFICER, null);
		setRelationships(RoleName.ROGUE, RoleName.RANGER, RoleName.ASSASSIN, RoleName.CHRONICLER, RoleName.WARP);
		setRelationships(RoleName.ASSASSIN, RoleName.RANGER, null, RoleName.ROGUE, RoleName.WARP);
		setRelationships(RoleName.RANGER, RoleName.SYLVAN, null, RoleName.ROGUE, RoleName.ASSASSIN);
		
		setRelationships(RoleName.FATE, null, RoleName.PURGER, null, RoleName.NECROMANCER);
		setRelationships(RoleName.ARTIFICER, null, RoleName.WARP, RoleName.PSION, null);
		setRelationships(RoleName.SYLVAN, null, null, RoleName.BERSERKER, RoleName.RANGER);
		
		setRelationships(RoleName.CHRONICLER, RoleName.WARRIOR, RoleName.ROGUE, RoleName.MAGE, null);
	}
	
	private void selectHex(RoleHex hex) {
		if(hex != null)
			selectedHex = hex;
	}
	
	private void setRelationships(RoleName name, RoleName top, RoleName left, RoleName right, RoleName bottom) {
		if(getRoleHex(name) != null)
			getRoleHex(name).setRelationships(getRoleHex(top), getRoleHex(left), getRoleHex(right), getRoleHex(bottom));
	}
	
	private ArrayList<RoleName> getAvailableRoles(Character character) {
		ArrayList<RoleName> roles = new ArrayList<RoleName>();
		if(!character.canAddNewRole())
			return roles;
		
		for(RoleName sourceRoleName: getRoleNames(character))
			for(RoleHex targetRoleHex: getRoleHex(sourceRoleName).getRelationships())
				if(targetRoleHex != null)
					roles.add(targetRoleHex.getName());
		
		roles.removeAll(getRoleNames(character));
		
		return roles;
	}
	
	public void build() {
		UIStack.push(this);
		revalidate();
		repaint();
	}
	
	private enum HexDirection {
		TOP, BOTTOM, TOP_LEFT, TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT;
	}
}
