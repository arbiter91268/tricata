package tricata.draw;

import org.jdesktop.core.animation.timing.Animator;
import org.jdesktop.swing.animation.timing.sources.SwingTimerTimingSource;
import tricata.model.Card;
import tricata.model.Tricata;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.TimeUnit;

public class GameWindow extends JFrame implements Observer{

	private BoardPanel board;

	private Tricata game;

	public GameWindow() {
		initComponents();
	}

	private void initComponents() {
		JSplitPane splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		getContentPane().add(splitPane, BorderLayout.CENTER);
		setTitle("Tricata");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		game = new Tricata(this, 2);

		board = new BoardPanel(this, game);
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
		board.repaint();
	}
}
