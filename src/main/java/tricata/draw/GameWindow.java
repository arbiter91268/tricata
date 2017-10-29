package tricata.draw;

import org.jdesktop.core.animation.timing.Animator;
import org.jdesktop.swing.animation.timing.sources.SwingTimerTimingSource;
import tricata.model.Card;
import tricata.model.Tricata;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.TimeUnit;

public class GameWindow extends JFrame implements Observer{

	private JPanel board;

	private Sprite deck;
	private Sprite bin;

	private Tricata game;

	public GameWindow() {
		JSplitPane splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		getContentPane().add(splitPane, BorderLayout.CENTER);
		setTitle("Tricata");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		board = new JPanel();
		board.setPreferredSize(new Dimension(800, 600));
		splitPane.setRightComponent(board);

		JToolBar toolBar = new JToolBar();
		splitPane.setLeftComponent(toolBar);

		JButton btnNewButton = new JButton("New button");
		toolBar.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("New button");
		toolBar.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("New button");
		toolBar.add(btnNewButton_2);

		JCheckBox chckbxNewCheckBox = new JCheckBox("New check box");
		toolBar.add(chckbxNewCheckBox);

		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("New check box");
		toolBar.add(chckbxNewCheckBox_1);

		pack();
		setLocationRelativeTo(null);
		playDealAnimation();
	}

	private void playDealAnimation() {
		SwingTimerTimingSource timer = new SwingTimerTimingSource();
		timer.init();
		timer.addPostTickListener((a,b) -> redraw());
		Animator animator = new Animator.Builder(timer).setDuration(800, TimeUnit.MILLISECONDS).setDisposeTimingSource(true).build();

	}

	private void redraw() {
		Graphics2D g = (Graphics2D)board.getGraphics();
		deck.draw(g);
		bin.draw(g);
	}

	@Override
	public void update(Observable o, Object arg) {
		if (board != null) redraw();
	}

	private Image getCardImage(Card card) {
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
