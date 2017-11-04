package tricata.draw;

import tricata.model.Card;
import tricata.model.Tricata;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BoardPanel extends JPanel implements MouseListener, MouseMotionListener {

	private static final double FACTOR = 0.7; // scale factor
	private static final int BASE_WIDTH = (int)(150 * FACTOR);
	private static final int BASE_HEIGHT = (int)(200 * FACTOR); // 4:3 height:width ratio
	private static final Font INFO_TEXT = new Font("Calibri", 0, 24);

	private final GameWindow master;
	private Tricata game;

	private Sprite deck, bin;
	private Sprite[] first = new Sprite[3];
	private Sprite[] second = new Sprite[3];
	private Sprite[] third = new Sprite[3];
	private Sprite[] fourth = new Sprite[3];

	private boolean cardPickedUp = false;
	private Card selectedCard;
	private Sprite selectedCardSprite;

	public BoardPanel(GameWindow master, Tricata game) {
		super();
		this.master = master;
		this.game = game;
		setPreferredSize(new Dimension(1024, 768));

		addComponentListener(new ComponentListener() {
			@Override
			public void componentResized(ComponentEvent e) {
				rescaleSprites();
				repaint();
			}

			public void componentMoved(ComponentEvent e) {}
			public void componentShown(ComponentEvent e) {}
			public void componentHidden(ComponentEvent e) {}
		});
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	void constructSprites() {
		int width = getPreferredSize().width;
		int height = getPreferredSize().height;

		int largeWidth = (int)(BASE_WIDTH * 1.4);
		int largeHeight = (int)(BASE_HEIGHT * 1.4);

		int count = game.getPlayers().size();

		deck = new Sprite(game.peekNextCardInDeck()).setBounds(new ScaledRectangle((width / 2) - largeWidth - 20, (height / 2) - (largeHeight / 2),
				largeWidth, largeHeight)).setFlipped(true).build();
		bin = new Sprite(game.peekNextCardInBin()).setBounds(new ScaledRectangle((width / 2) + 20, (height / 2) - (largeHeight / 2),
				largeWidth, largeHeight)).build();

		final int initialOffset = 20 + BASE_WIDTH + (BASE_WIDTH / 2);

		for (int i = 0; i < 3; i++) {
			switch (count) {
				case 2:
					first[i] = new Sprite(game.getPlayer(0).peekCard(i)).setBounds(new ScaledRectangle((width / 2) - initialOffset +
							((BASE_WIDTH * i) + (20 * i)), height - BASE_HEIGHT - 20, BASE_WIDTH, BASE_HEIGHT)).build();
					second[i] = new Sprite(game.getPlayer(1).peekCard(i)).setBounds(new ScaledRectangle((width / 2) - initialOffset
							+ ((BASE_WIDTH * i) + (20 * i)), 20, BASE_WIDTH, BASE_HEIGHT)).build();
					break;
				case 3:
					first[i] = new Sprite(game.getPlayer(0).peekCard(i)).setBounds(new ScaledRectangle((width / 2) - initialOffset +
							((BASE_WIDTH * i) + (20 * i)), height - BASE_HEIGHT - 20, BASE_WIDTH, BASE_HEIGHT)).build();
					second[i] = new Sprite(game.getPlayer(1).peekCard(i)).setBounds(new ScaledRectangle(50, (height / 2) - initialOffset
							+ ((BASE_HEIGHT * i) + (20 * i)), BASE_WIDTH, BASE_HEIGHT)).build();
					third[i] = new Sprite(game.getPlayer(2).peekCard(i)).setBounds(new ScaledRectangle(width - 20 - BASE_HEIGHT, (height / 2) - initialOffset
							+ ((BASE_HEIGHT * i) + (20 * i)), BASE_WIDTH, BASE_HEIGHT)).build();
					break;
				case 4:
					first[i] = new Sprite(game.getPlayer(0).peekCard(i)).setBounds(new ScaledRectangle((width / 2) - initialOffset +
							((BASE_WIDTH * i) + (20 * i)), height - BASE_HEIGHT - 20, BASE_WIDTH, BASE_HEIGHT)).build();
					second[i] = new Sprite(game.getPlayer(1).peekCard(i)).setBounds(new ScaledRectangle(50, (height / 2) - initialOffset
							+ ((BASE_HEIGHT * i) + (20 * i)), BASE_WIDTH, BASE_HEIGHT)).build();
					third[i] = new Sprite(game.getPlayer(2).peekCard(i)).setBounds(new ScaledRectangle((width / 2) - initialOffset
							+ ((BASE_WIDTH * i) + (20 * i)), 20, BASE_WIDTH, BASE_HEIGHT)).build();
					fourth[i] = new Sprite(game.getPlayer(3).peekCard(i)).setBounds(new ScaledRectangle(width - 20 - BASE_HEIGHT, (height / 2) - initialOffset
						+ ((BASE_HEIGHT * i) + (20 * i)), BASE_WIDTH, BASE_HEIGHT)).build();
					break;
				default:
					throw new IllegalArgumentException("Invalid number of players");
			}
		}
	}

	void refreshSprites() {
		if (deck == null || bin == null) { // board hasn't been set up yet!
			return;
		}
		deck.setCard(game.peekNextCardInDeck()).build();
		bin.setCard(game.peekNextCardInBin()).build();
		int count = game.getPlayers().size();
		for (int i = 0; i < 3; i++) {
			first[i].setCard(game.getPlayer(0).peekCard(i)).build();
			second[i].setCard(game.getPlayer(1).peekCard(i)).build();
			if (count >= 3) {
				third[i].setCard(game.getPlayer(2).peekCard(i)).build();
			}
			if (count == 4) {
				fourth[i].setCard(game.getPlayer(3).peekCard(i)).build();
			}
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D gr = (Graphics2D)g;
		deck.draw(gr);
		bin.draw(gr);
		int count = game.getPlayers().size();
		for (int i = 0; i < 3; i++) {
			first[i].draw(gr);
			second[i].draw(gr);
			if (count >= 3) {
				third[i].draw(gr);
			}
			if (count == 4) {
				fourth[i].draw(gr);
			}
		}
		gr.setFont(INFO_TEXT);
		gr.drawString(game.getPlayer(0).name, (int)(first[0].bounds.getX()), (int)(first[0].bounds.getY() - INFO_TEXT.getSize()));
		switch (count) {
			case 2:
				gr.drawString(game.getPlayer(1).name, (int)(second[0].bounds.getX()), (int)(second[0].bounds.getY() + second[0].bounds.getHeight() + INFO_TEXT.getSize()));
				break;
			case 3:
				gr.drawString(game.getPlayer(1).name, (int)(second[0].bounds.getX()), (int)(second[0].bounds.getY() - INFO_TEXT.getSize()));
				gr.drawString(game.getPlayer(2).name, (int)(third[0].bounds.getX()), (int)(third[0].bounds.getY() - INFO_TEXT.getSize()));
				break;
			case 4:
				gr.drawString(game.getPlayer(1).name, (int)(second[0].bounds.getX()), (int)(second[0].bounds.getY() - INFO_TEXT.getSize()));
				gr.drawString(game.getPlayer(2).name, (int)(third[0].bounds.getX()), (int)(third[0].bounds.getY() + third[0].bounds.getHeight() + INFO_TEXT.getSize()));
				gr.drawString(game.getPlayer(3).name, (int)(fourth[0].bounds.getX()), (int)(fourth[0].bounds.getY() - INFO_TEXT.getSize()));
				break;
			default:
				break;
		}
		if (cardPickedUp) {
			selectedCardSprite.draw(gr);
		}
	}

	private void rescaleSprites() {
		final Dimension now = getSize();
		final Dimension preferred = getPreferredSize();
		double scaleX = now.getWidth() / preferred.getWidth();
		double scaleY = now.getHeight() / preferred.getHeight();
		deck.bounds.setScale(scaleX, scaleY);
		bin.bounds.setScale(scaleX, scaleY);
		for (int i = 0; i < 3; i++) {
			first[i].bounds.setScale(scaleX, scaleY);
			second[i].bounds.setScale(scaleX, scaleY);
			if (game.getPlayers().size() >= 3) {
				third[i].bounds.setScale(scaleX, scaleY);
			}
			if (game.getPlayers().size() == 4) {
				fourth[i].bounds.setScale(scaleX, scaleY);
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int x = e.getX(), y = e.getY();
		if (deck.contains(x, y) && !cardPickedUp && game.peekNextCardInDeck() != null) {
			cardPickedUp = true;
			selectedCard = game.pickupCard(true);
			selectedCardSprite = new Sprite(selectedCard).setBounds(new ScaledRectangle(x - (BASE_WIDTH / 2),
					y - (BASE_HEIGHT / 2), BASE_WIDTH, BASE_HEIGHT)).build();
			repaint();
			return;
		}
		if (bin.contains(x, y) && !cardPickedUp && game.peekNextCardInBin() != null) {
			cardPickedUp = true;
			selectedCard = game.pickupCard(false);
			System.out.println(selectedCard.toString());
			selectedCardSprite = new Sprite(selectedCard).setBounds(new ScaledRectangle(x - (BASE_WIDTH / 2),
					y - (BASE_HEIGHT / 2), BASE_WIDTH, BASE_HEIGHT)).build();
			repaint();
			return;
		}
		if (cardPickedUp) {
			boolean anythingSelected = false;
			if (bin.contains(x, y)) {
				anythingSelected = true;
				game.placeCard(selectedCard, -1);
			} else {
				int count = game.getPlayers().size();
				for (int i = 0; i < 3; i++) {
					if (first[i].contains(x, y) && game.getCurrentPlayer().getID() == 0) {
						anythingSelected = true;
						game.placeCard(selectedCard, i);
					} else if (second[i].contains(x, y) && game.getCurrentPlayer().getID() == 1) {
						anythingSelected = true;
						game.placeCard(selectedCard, 3 + i);
					} else if (count >= 3 && third[i].contains(x, y) && game.getCurrentPlayer().getID() == 2) {
						anythingSelected = true;
						game.placeCard(selectedCard, 6 + i);
					} else if (count == 4 && fourth[i].contains(x, y) && game.getCurrentPlayer().getID() == 3) {
						anythingSelected = true;
						game.placeCard(selectedCard, 9 + i);
					}
				}
			}
			if (anythingSelected) {
				selectedCard = null;
				cardPickedUp = false;
				selectedCardSprite = null;
			}
		}
	}

	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseDragged(MouseEvent e) {}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (cardPickedUp) {
			selectedCardSprite.bounds.x = e.getX() - (BASE_WIDTH / 2);
			selectedCardSprite.bounds.y = e.getY() - (BASE_HEIGHT / 2);
			repaint();
		}
	}
}
