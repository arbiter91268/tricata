package tricata.draw;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

public class Sprite {

	public Rectangle bounds;
	private Image image;

	public Sprite(Rectangle rectangle, Image image) {
		bounds = rectangle;
		this.image = image;
	}

	public void draw(Graphics2D g) {
		g.drawImage(image, bounds.x, bounds.y, bounds.width, bounds.height, null);
	}

	public boolean contains(int x, int y) {
		return bounds.contains(x, y);
	}
}