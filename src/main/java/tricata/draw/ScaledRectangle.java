package tricata.draw;

import java.awt.*;

/**
 * my extension of rectangle to include 2 dimensions of scaling
 *
 * @author Kristian Hansen
 */
public class ScaledRectangle extends Rectangle {

	private double scaleX, scaleY;

	public ScaledRectangle(int x, int y, int width, int height, double scaleX, double scaleY) {
		super(x, y, width, height);
		this.scaleX = scaleX;
		this.scaleY = scaleY;
	}

	public ScaledRectangle(int x, int y, int width, int height) {
		this(x, y, width, height, 1.0, 1.0);
	}

	public void setScaleX(double scaleX) {
		this.scaleX = scaleX;
	}

	public void setScaleY(double scaleY) {
		this.scaleY = scaleY;
	}

	public void setScale(double scaleX, double scaleY) {
		this.setScaleX(scaleX);
		this.setScaleY(scaleY);
	}

	@Override
	public double getX() {
		return this.x * scaleX;
	}

	@Override
	public double getY() {
		return this.y * scaleY;
	}

	@Override
	public double getWidth() {
		return this.width * scaleX;
	}

	@Override
	public double getHeight() {
		return this.height * scaleY;
	}

	@Override
	public boolean contains(int x, int y) {
		Rectangle resized = new Rectangle((int)getX(), (int)getY(), (int)getWidth(), (int)getHeight());
		return resized.contains(x, y);
	}
}
