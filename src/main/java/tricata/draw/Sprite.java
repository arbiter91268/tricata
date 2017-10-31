package tricata.draw;

import tricata.model.Card;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

public class Sprite {

	public Rectangle bounds;
	private Image image;
	private int rotation;
	private boolean flipped;
	private Card card;

	private static final Font NUMBERS = new Font("Calibri", 0, 22);


	public Sprite(Card card) {
		this.card = card;
	}

	public Sprite setRotation(int newRotation) {
		this.rotation = newRotation;
		return this;
	}

	public Sprite setFlipped(boolean flipped) {
		this.flipped = flipped;
		return this;
	}

	public Sprite setBounds(Rectangle bounds) {
		this.bounds = bounds;
		return this;
	}

	public Sprite build() {
		if (card == null) {
			return this;
		}
		if (flipped) {
			image = ImageList.CARD_BACK.getImage();
		} else {
			image = getCardImage(card);
		}
		return this;
	}

	public Sprite setCard(Card card) {
		this.card = card;
		return this;
	}

	void draw(Graphics2D g) {
		if (card == null) {
			g.setColor(Color.darkGray);
			g.drawRect(bounds.x, bounds.y, bounds.width, bounds.height);
			g.setColor(Color.BLACK);
			return;
		}
		g.drawImage(image, bounds.x, bounds.y, bounds.width, bounds.height, null);
		if (!flipped) {
			g.setFont(NUMBERS);
			g.drawString(Integer.toString(card.getNumber()), bounds.x + 20, bounds.y + bounds.height - (bounds.height / 5));
		}
	}

	public boolean contains(int x, int y) {
		return bounds.contains(x, y);
	}



	private static Image getCardImage(Card card) {
		if (card == null) {
			return null;
		}
		ImageList item = null;
		outer : switch (card.getType()) {
			case DEMON:
				switch (card.getColor()) {
					case RED:
						item = ImageList.RED_DEMON;
						break outer;
					case GREEN:
						item = ImageList.GREEN_DEMON;
						break outer;
					case PURPLE:
						item = ImageList.PURPLE_DEMON;
						break outer;
				}
			case GENTLEMAN:
				switch (card.getColor()) {
					case RED:
						item = ImageList.RED_GENTLEMAN;
						break outer;
					case GREEN:
						item = ImageList.GREEN_GENTLEMAN;
						break outer;
					case PURPLE:
						item = ImageList.PURPLE_GENTLEMAN;
						break outer;
				}
			case GIRL:
				switch (card.getColor()) {
					case RED:
						item = ImageList.RED_LADY;
						break outer;
					case GREEN:
						item = ImageList.GREEN_LADY;
						break outer;
					case PURPLE:
						item = ImageList.PURPLE_LADY;
						break outer;
				}
		}
		return item.getImage();
	}
}