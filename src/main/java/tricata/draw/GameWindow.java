package tricata.draw;

import org.jdesktop.core.animation.timing.Animator;
import org.jdesktop.swing.animation.timing.sources.SwingTimerTimingSource;
import tricata.model.Tricata;
import tricata.network.TricataServer;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.TimeUnit;

public class GameWindow extends JFrame implements Observer{

	private BoardPanel board;
	private JLabel infoLabel;
	private boolean isOnline = false, isHost = false;
	private GameConfiguration config;

	private Tricata game;

	public GameWindow(GameConfiguration configuration) {
		setTitle(configuration.name);
		game = new Tricata(this, configuration.numPlayers, configuration.mode, 3, configuration.name);
		if (configuration.online) {
			try {
				isOnline = true;
				isHost = true;
				new TricataServer(this, configuration.name, configuration.password, configuration.port).start();
			} catch (Exception t) {
				JOptionPane.showMessageDialog(this, "An error occured while setting up the server. \n" +
						t.getMessage());
				dispose();
			}
		}
		config = configuration;
		initComponents();
	}

	private void initComponents() {
		JSplitPane splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		getContentPane().add(splitPane, BorderLayout.CENTER);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		board = new BoardPanel(this, game);
		splitPane.setRightComponent(board);


		JToolBar toolBar = new JToolBar();
		splitPane.setLeftComponent(toolBar);

		if (isOnline && isHost) {
			JButton btnExtIP = new JButton("Get External IP Address");
			toolBar.add(btnExtIP);

			JButton btnIntIP = new JButton("Get Internal IP Address");
			toolBar.add(btnIntIP);

			btnExtIP.addActionListener(event -> {
				try {
					URL local = new URL("http://checkip.amazonaws.com");
					BufferedReader in = new BufferedReader(new InputStreamReader(local.openStream()));
					String ip = in.readLine();
					JOptionPane.showMessageDialog(this, "Your IP Address: " + ip);
				} catch (Exception exc) {
					JOptionPane.showMessageDialog(this, "An error occured while trying to retrieve "
						+ "your exernal IP address.\n Cause: " + exc.getMessage());
				}
			});
			btnIntIP.addActionListener(event -> {

			});
		}

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
		if (board != null) {
			board.refreshSprites();
			board.repaint();
			refreshInfo();
		}
		if (arg != null) {
			String name = (String)arg;
			if (name.contains("has won")) {
				JOptionPane.showMessageDialog(this, name.replace("has won", "") + " has won the game!");
				System.exit(0);
			} else {
				JOptionPane.showMessageDialog(this, name + "has won the round!");
				game.deal();
			}
		}
	}

	private void refreshInfo() {
		StringBuilder sb = new StringBuilder();
		sb.append("Game Name: ");
		sb.append(game.name);
		sb.append("  ");
		if (isOnline && isHost) {
			sb.append("Port: ");
			sb.append(config.port);
			sb.append("   ");
		}
		sb.append(game.getCurrentPlayer().name);
		sb.append("'s turn");
		infoLabel.setText(sb.toString());
	}
}
