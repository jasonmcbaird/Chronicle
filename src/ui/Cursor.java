package ui;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import mapLogic.Entity;

public class Cursor extends Entity {
	
	private BufferedImage image;
	
	public Cursor() {
		try {
			image = ImageIO.read(this.getClass().getResource("/res/ui/quillCursor.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
        setX(0);
        setY(0);
        name = "Cursor";
	}

	public boolean getCanOverlap() {
		return true;
	}

	public BufferedImage getImage() {
		return image;
	}
	
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
}
