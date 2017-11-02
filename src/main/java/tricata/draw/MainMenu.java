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

	private static final Font BUTTON_FONT = new Font("Candara", Font.PLAIN, 24);

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
		newGameButton.setFont(BUTTON_FONT);
		joinGameButton.setFont(BUTTON_FONT);
		instructionsButton.setFont(BUTTON_FONT);
		exitButton.setFont(BUTTON_FONT);
		getContentPane().add(newGameButton);
		getContentPane().add(joinGameButton);
		getContentPane().add(instructionsButton);
		getContentPane().add(exitButton);
		pack();
		setSize(new Dimension(400, 500));
		setLocationRelativeTo(null);

		newGameButton.addActionListener((event) -> new CreateGameMenu().setVisible(true));
		exitButton.addActionListener((event) -> System.exit(0));
	}
}
