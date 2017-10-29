package tricata.draw;

import org.jdesktop.swing.animation.timing.sources.SwingTimerTimingSource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class GameWindow extends JFrame implements Observer{

	private JPanel board;

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
	}

	private void redraw() {

	}

	@Override
	public void update(Observable o, Object arg) {
		redraw();
	}
}
