package tricata.draw;

import tricata.model.Tricata;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class BoardPanel extends JPanel {

	private static final double FACTOR = 0.7; // scale factor
	private static final int BASE_WIDTH = (int)(150 * FACTOR);
	private static final int BASE_HEIGHT = (int)(200 * FACTOR); // 4:3 height:width ratio

	private final GameWindow master;
	private Tricata game;

	private Sprite deck, bin;
	private Sprite[] first = new Sprite[3];
	private Sprite[] second = new Sprite[3];

	public BoardPanel(GameWindow master, Tricata game) {
		super();
		this.master = master;
		this.game = game;
		setPreferredSize(new Dimension(800, 600));

		addComponentListener(new ComponentListener() {
			@Override
			public void componentResized(ComponentEvent e) {
				repaint();
			}

			public void componentMoved(ComponentEvent e) {}
			public void componentShown(ComponentEvent e) {}
			public void componentHidden(ComponentEvent e) {}
		});
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
		}
		for (int i = 2; i >= 0; i--) {
			second[i] = new Sprite(game.getPlayer(1).peekCard(i)).setBounds(new Rectangle((width / 2) - initialOffset
					+ ((BASE_WIDTH * i) + (20 * i)), 20, BASE_WIDTH, BASE_HEIGHT)).setRotation(180).build();
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
	}
}
