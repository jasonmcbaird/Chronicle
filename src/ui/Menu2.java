package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import utilities.Logger;
import enums.Direction;
import enums.UIPosition;

public class Menu2 extends UIComponent implements InputResponder {
	
	private final int fontSize = 24;
	private final int infoFontSize = 8;
	
	protected ArrayList<MenuItem> items = new ArrayList<MenuItem>();
	protected int selectedIndex = 0;
	protected int xPosition = 0;
	protected int yPosition = 0;
	protected int buttonWidth = 240;
	protected int buttonHeight = 80;
	protected UIPosition position;
	protected BufferedImage topImage;
	protected BufferedImage middleImage;
	protected BufferedImage bottomImage;
	protected boolean displayInfoLabel;
	
	protected MenuResponder responder;
	
	public Menu2(UIPosition position) {
		this.position = position;
		setOpaque(false);
		setImages();
	}
	
	public void setImages() {
		try {
			topImage = ImageIO.read(this.getClass().getResource("/res/ui/topButton.png"));
			middleImage = ImageIO.read(this.getClass().getResource("/res/ui/middleButton.png"));
			bottomImage = ImageIO.read(this.getClass().getResource("/res/ui/bottomButton.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setItems(ArrayList<MenuItem> items) {
		this.items = items;
	}
	
	public ArrayList<MenuItem> getItems() {
		return items;
	}
	
	public MenuItem getSelection() {
		return items.get(selectedIndex);
	}
	
	public void setResponder(MenuResponder responder) {
		this.responder = responder;
	}
	
	public void inputDirection(Direction direction) {
		switch (direction) {
		case DOWN:
			moveDown();
			//Logger.print("Down");
			repaint();
			return;
		case UP:
			moveUp();
			//Logger.print("Up");
			repaint();
			return;
		default:
			return;
		}
	}
	
	private void moveDown() {
		selectedIndex++;
		if(selectedIndex > items.size() - 1)
			selectedIndex = items.size() - 1;
		else
			displayInfoLabel = false;
	}
	
	private void moveUp() {
		selectedIndex--;
		if(selectedIndex < 0)
			selectedIndex = 0;
		else
			displayInfoLabel = false;
	}
	
	public void inputAccept() {
		if(responder != null)
			responder.menuItemSelected(items.get(selectedIndex).getName());
		displayInfoLabel = false;
	}
	
	public void inputInfo() {
		displayInfoLabel = true;
		repaint();
	}
	
	public void inputCancel() {
		responder.inputCancel();
	}
	
	public void display(JPanel jPanel) {
		setPosition(position, jPanel);
		jPanel.add(this);
		jPanel.revalidate();
		jPanel.repaint();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Logger.print("Painting menu: " + items, -1);
		this.removeAll();
		
		int offset = yPosition;
		for(MenuItem item: items) {
			createButton(g, item.getName(), offset);
			offset += buttonHeight;
		}
		if(displayInfoLabel) {
			setBounds(xPosition - buttonWidth, yPosition, buttonWidth * 2 + 1, offset * buttonHeight + 1);
			createInfoLabel(g, items.get(selectedIndex).getInfo(), selectedIndex * buttonHeight);
		} else {
			setBounds(xPosition, yPosition, buttonWidth + 1, offset * buttonHeight + 1);
		}
		drawSelection(g);
	}
	
	private void drawSelection(Graphics g) {
		g.setColor(Color.BLACK);
		int xOffset = 0;
		if(displayInfoLabel)
			xOffset = buttonWidth;
		g.drawRoundRect(xOffset + 4, buttonHeight * selectedIndex + 3, buttonWidth - 8, buttonHeight - 6, 10, 10);
	}
	
	private void drawManualSelection(Graphics g) {
		g.setColor(Color.GRAY);
		g.fillRoundRect(0, buttonHeight * selectedIndex, buttonWidth, buttonHeight, 10, 10);
	}
	
	private void createButton(Graphics g, String string, int offset) {
		drawButton(g, offset);
		int xOffset = 0;
		if(displayInfoLabel)
			xOffset = buttonWidth;
        JLabel jlabel = new JLabel(string, SwingConstants.CENTER);
        jlabel.setBounds(xOffset + 3, offset, buttonWidth - 3, buttonHeight - 3);
        jlabel.setFont(new Font("Verdana", 1, fontSize));
        add(jlabel);
	}
	
	private void createInfoTextArea(Graphics g, String string, int offset) {
		int labelOffset = 0;
		if(xPosition < buttonWidth)
			labelOffset = buttonWidth;
		JTextArea textArea = new JTextArea(string);
		textArea.setLineWrap(true);
		textArea.setOpaque(true);
        textArea.setBounds(labelOffset, offset, buttonWidth - 3, buttonHeight - 3);
        textArea.setFont(new Font("Verdana", 1, infoFontSize));
        add(textArea);
	}
	
	private void createInfoLabel(Graphics g, String string, int offset) {
		int labelOffset = 0;
		if(xPosition < buttonWidth)
			labelOffset = buttonWidth;
		JLabel textArea = new JLabel(string, SwingConstants.CENTER);
		textArea.setOpaque(true);
        textArea.setBounds(labelOffset, offset, buttonWidth - 3, buttonHeight - 3);
        textArea.setFont(new Font("Verdana", 1, infoFontSize));
        add(textArea);
	}
	
	private void drawButton(Graphics g, int offset) {
		int xOffset = 0;
		if(displayInfoLabel)
			xOffset = buttonWidth;
		BufferedImage image = middleImage;
		if(offset == 0)
			image = topImage;
		if(offset == (items.size() - 1) * buttonHeight)
			image = bottomImage;
		g.drawImage(image, xOffset, offset, null);
	}
	
	private void drawManualButton(Graphics g, int offset) {
		g.setColor(Color.WHITE);
        g.fillRoundRect(0, offset, buttonWidth, buttonHeight, 10, 10);
		g.setColor(Color.BLACK);
        g.drawRoundRect(0, offset, buttonWidth, buttonHeight, 10, 10);
	}
	
	private void setPosition(UIPosition position, JPanel jPanel) {
		switch (position) {
		case LEFT:
			xPosition = 0;
			yPosition = 0;
			return;
		case RIGHT:
			xPosition = jPanel.getPreferredSize().width - buttonWidth;
			yPosition = 0;
			return;
		case TOP:
			xPosition = 0;
			yPosition = 0;
			return;
		case BOTTOM:
			xPosition = 0;
			yPosition = jPanel.getPreferredSize().height - buttonHeight;
		case RIGHT_2:
			xPosition = jPanel.getPreferredSize().width - buttonWidth * 2;
			yPosition = 0;
			return;
		case RIGHT_3:
			xPosition = jPanel.getPreferredSize().width - buttonWidth * 3;
			yPosition = 0;
			return;
		default:
			throw new UnsupportedOperationException("Tried to make a Menu with an unsupported UIPosition: " + position);
		}
	}
}
