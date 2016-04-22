package ui.grid;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import mapLogic.Entity;
import mapLogic.GridLogic;
import ui.CharacterInterface;
import ui.Cursor;
import utilities.Logger;
import utilities.Position;
import enums.Direction;

public class MoveMode implements GridMode {
	
	private Cursor cursor;
	private CharacterInterface character;
	private EncounterGrid grid;
	
	public MoveMode(CharacterInterface character, EncounterGrid grid) {
		cursor = new Cursor();
		cursor.setX(character.getX());
		cursor.setY(character.getY());
		try {
			cursor.setImage(getTranslucentImage(character.getImage()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.grid = grid;
		this.character = character;
	}

	public void inputDirection(Direction direction) {
		if(checkMoveValidity(direction) && checkMoveSpeed(direction))
			cursor.move(direction, 1);
	}
	
	private boolean checkMoveValidity(Direction direction) {
		Position position = new Position(cursor.getX(), cursor.getY());
		position.move(direction);
		if(position.equalsPosition(character.getPosition()))
			return true;
		return GridLogic.checkMove(cursor, direction, 1);
	}
	
	private boolean checkMoveSpeed(Direction direction) {
		Position position = new Position(cursor.getX(), cursor.getY());
		position.move(direction);
		return GridLogic.getPathDistance((Entity) character, position) <= character.getMoveSpeed();
	}

	public void inputAccept() {
		if(!withinSpeed(GridLogic.getPathDistance((Entity) character, cursor.getPosition()))) {
			Logger.print("You can't move that far.", 1);
			return;
		}
		if(!character.tryToMove(cursor.getX() - character.getX(), cursor.getY() - character.getY())) {
			Logger.print("There's something in the way.", 1);
			return;
		}
		grid.combatMenu(character);
	}
	
	public boolean withinSpeed(int i) {
		return i <= character.getMoveSpeed();
	}

	public void inputCancel() {
		character.extraTurn();
		grid.setMode(new CursorMode(grid));
	}
	
	public Entity getModeEntity() {
		return cursor;
	}
	
	public BufferedImage getTranslucentImage(BufferedImage startImage) {
		BufferedImage tmpImg = new BufferedImage(startImage.getWidth(), startImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = (Graphics2D) tmpImg.getGraphics();
		g2d.setComposite(AlphaComposite.SrcOver.derive(0.5f)); 
		// set the transparency level in range 0.0f - 1.0f 
		g2d.drawImage(startImage, 0, 0, null);
		return tmpImg;
	}
}
