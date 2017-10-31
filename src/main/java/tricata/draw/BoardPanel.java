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

		deck = new Sprite(game.peekNextCardInDeck()).setBounds(new Rectangle((width / 2) - largeWidth - 20, (height / 2) - (largeHeight / 2),
				largeWidth, largeHeight)).setFlipped(true).build();
		bin = new Sprite(game.peekNextCardInBin()).setBounds(new Rectangle((width / 2) + 20, (height / 2) - (largeHeight / 2),
				largeWidth, largeHeight)).build();

		final int initialOffset = 20 + BASE_WIDTH + (BASE_WIDTH / 2);

		for (int i = 0; i < 3; i++) {
			first[i] = new Sprite(game.getPlayer(0).peekCard(i)).setBounds(new Rectangle((width / 2) - initialOffset +
					((BASE_WIDTH * i) + (20 * i)), height - BASE_HEIGHT - 20, BASE_WIDTH, BASE_HEIGHT)).build();
			second[i] = new Sprite(game.getPlayer(1).peekCard(i)).setBounds(new Rectangle((width / 2) - initialOffset
					+ ((BASE_WIDTH * i) + (20 * i)), 20, BASE_WIDTH, BASE_HEIGHT)).setRotation(180).build();
		}
	}

	void refreshSprites() {
		if (deck == null || bin == null) { // board hasn't been set up yet!
			return;
		}
		deck.setCard(game.peekNextCardInDeck()).build();
		bin.setCard(game.peekNextCardInBin()).build();
		for (int i = 0; i < 3; i++) {
			first[i].setCard(game.getPlayer(0).peekCard(i)).build();
			second[i].setCard(game.getPlayer(1).peekCard(i)).build();
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D gr = (Graphics2D)g;
		deck.draw(gr);
		bin.draw(gr);
		for (int i = 0; i < 3; i++) {
			first[i].draw(gr);
			second[i].draw(gr);
		}
		gr.setFont(INFO_TEXT);
		gr.drawString(game.getPlayer(0).name, (int)(getPreferredSize().getWidth() / 2) - 20 - BASE_WIDTH - (BASE_WIDTH / 2),
				(int)(getPreferredSize().getHeight() - 30 - BASE_HEIGHT));
		gr.drawString(game.getPlayer(1).name, (int)(getPreferredSize().getWidth() / 2) - 20 - BASE_WIDTH - (BASE_WIDTH / 2),
				BASE_HEIGHT + 40);
		if (cardPickedUp) {
			selectedCardSprite.draw(gr);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int x = e.getX(), y = e.getY();
		if (deck.contains(x, y) && !cardPickedUp && game.peekNextCardInDeck() != null) {
			cardPickedUp = true;
			selectedCard = game.pickupCard(true);
			selectedCardSprite = new Sprite(selectedCard).setBounds(new Rectangle(x - (BASE_WIDTH / 2),
					y - (BASE_HEIGHT / 2), BASE_WIDTH, BASE_HEIGHT)).build();
		} else if (bin.contains(x, y) && !cardPickedUp && game.peekNextCardInBin() != null) {
			cardPickedUp = true;
			selectedCard = game.pickupCard(false);
			selectedCardSprite = new Sprite(selectedCard).setBounds(new Rectangle(x - (BASE_WIDTH / 2),
					y - (BASE_HEIGHT / 2), BASE_WIDTH, BASE_HEIGHT)).build();
		}
		if (cardPickedUp) {
			boolean anythingSelected = false;
			if (bin.contains(x, y)) {
				anythingSelected = true;
				game.placeCard(selectedCard, -1);
			} else {
				for (int i = 0; i < 3; i++) {
					if (first[i].contains(x, y) && game.getCurrentPlayer().getID() == 0) {
						anythingSelected = true;
						game.placeCard(selectedCard, i);
					} else if (second[i].contains(x, y) && game.getCurrentPlayer().getID() == 1) {
						anythingSelected = true;
						game.placeCard(selectedCard, 3 + i);
					}
				}
			}
			if (anythingSelected) {
				selectedCard = null;
				cardPickedUp = false;
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
