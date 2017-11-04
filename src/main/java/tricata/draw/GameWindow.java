package tricata.draw;

import org.jdesktop.core.animation.timing.Animator;
import org.jdesktop.swing.animation.timing.sources.SwingTimerTimingSource;
import tricata.model.Card;
import tricata.model.Tricata;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.TimeUnit;

public class GameWindow extends JFrame implements Observer{

	private BoardPanel board;
	private JLabel infoLabel;

	private Tricata game;

	public GameWindow(GameConfiguration configuration) {
		game = new Tricata(this, configuration.numPlayers, configuration.mode, 3, configuration.name);
		initComponents();
	}

	private void initComponents() {
		JSplitPane splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		getContentPane().add(splitPane, BorderLayout.CENTER);
		setTitle("Tricata");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		board = new BoardPanel(this, game);
		splitPane.setRightComponent(board);


		JToolBar toolBar = new JToolBar();
		splitPane.setLeftComponent(toolBar);

		JButton btnNewButton = new JButton("New button");
		toolBar.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("New button");
		toolBar.add(btnNewButton_1);

		JButton exitGameButton = new JButton("Exit Game");
		toolBar.add(exitGameButton);
		exitGameButton.addActionListener(event -> dispose());

		toolBar.addSeparator();
		infoLabel = new JLabel();
		toolBar.add(infoLabel);

		pack();
		setLocationRelativeTo(null);
		game.deal();
		board.constructSprites();
		board.repaint();
		//playDealAnimation();
	}

	private void playDealAnimation() {
		SwingTimerTimingSource timer = new SwingTimerTimingSource();
		timer.init();
		timer.addPostTickListener((a,b) -> board.repaint());
		Animator animator = new Animator.Builder(timer).setDuration(800, TimeUnit.MILLISECONDS).setDisposeTimingSource(true).build();

	}

	@Override
	public void update(Observable o, Object arg) {
		board.refreshSprites();
		board.repaint();
		refreshInfo();
		if (arg != null) {
			String name = (String)arg;
			JOptionPane.showMessageDialog(this, name + " has won the round!");
			System.exit(0);
		}
	}

	private void refreshInfo() {
		StringBuilder sb = new StringBuilder();
		sb.append("Game Name: ");
		sb.append(game.name);
		sb.append('\t');
		sb.append(game.getCurrentPlayer().name);
		sb.append("'s turn");
		infoLabel.setText(sb.toString());
	}
}
