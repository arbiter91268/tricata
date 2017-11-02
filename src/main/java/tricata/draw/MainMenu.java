package tricata.draw;

import javax.swing.*;
import java.awt.*;

/**
 * main menu gui code
 *
 * @author Kristian Hansen
 */
@SuppressWarnings("serial")
public class MainMenu extends JFrame {

	public MainMenu() {
		initComponents();
	}

	private void initComponents() {
		setTitle("Tricata Main Menu");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		getContentPane().setLayout(new GridLayout(5, 0, 5, 5));
		JPanel logo = new JPanel();
		getContentPane().add(logo);
		JButton newGameButton = new JButton("New Game");
		JButton joinGameButton = new JButton("Join Game");
		JButton instructionsButton = new JButton("Instructions");
		JButton exitButton = new JButton("Exit");
		getContentPane().add(newGameButton);
		getContentPane().add(joinGameButton);
		getContentPane().add(instructionsButton);
		getContentPane().add(exitButton);
		pack();
		setSize(new Dimension(350, 500));
		setLocationRelativeTo(null);

		newGameButton.addActionListener((event) -> new CreateGameMenu().setVisible(true));
		exitButton.addActionListener((event) -> System.exit(0));
	}
}
