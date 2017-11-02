package tricata.draw;

import tricata.model.Tricata;

import javax.swing.*;
import java.awt.*;
import javax.swing.GroupLayout.Alignment;

/**
 * create game menu gui
 *
 * @author Kristian Hansen
 */
public class CreateGameMenu extends JFrame {

	private static final Font TITLE_FONT = new Font("Candara", Font.PLAIN, 24);
	private static final Font TEXT_FONT = new Font("Candara", Font.PLAIN, 16);

	public CreateGameMenu() {
		initComponents();
	}

	private void initComponents() {
		setTitle("Create New Game");
		JLabel titleLabel = new JLabel("Create New Game");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(TITLE_FONT);

		JLabel gameNameLabel = new JLabel("Game Name:");
		gameNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		gameNameLabel.setFont(TEXT_FONT);

		JTextField gameNameField = new JTextField();
		gameNameField.setColumns(10);
		gameNameField.setFont(TEXT_FONT);
		gameNameField.setText("New Game 1");

		JLabel numPlayersLabel = new JLabel("Number of Players:");
		numPlayersLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		numPlayersLabel.setFont(TEXT_FONT);

		JComboBox<String> numPlayersSelector = new JComboBox<>(new String[] {"2 Players", "3 Players", "4 Players"});
		numPlayersSelector.setSelectedIndex(0);
		numPlayersSelector.setFont(TEXT_FONT);

		JLabel numRoundsLabel = new JLabel("Rounds:");
		numRoundsLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		numRoundsLabel.setFont(TEXT_FONT);

		JSpinner numRoundsSpinner = new JSpinner();
		numRoundsSpinner.setFont(TEXT_FONT);
		numRoundsSpinner.setValue(1);

		JCheckBox onlineCheckbox = new JCheckBox("Online Game");
		onlineCheckbox.setHorizontalAlignment(SwingConstants.CENTER);
		onlineCheckbox.setFont(TEXT_FONT);

		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		passwordLabel.setFont(TEXT_FONT);

		JPasswordField passwordField = new JPasswordField();
		passwordField.setFont(TEXT_FONT);

		JLabel gamemodeLabel = new JLabel("Game Modes:");
		gamemodeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		gamemodeLabel.setFont(TEXT_FONT);

		JCheckBox royalChaosCheckbox = new JCheckBox("Royal Chaos");
		royalChaosCheckbox.setHorizontalAlignment(SwingConstants.CENTER);
		royalChaosCheckbox.setFont(TEXT_FONT);

		JCheckBox upTheAnteCheckbox = new JCheckBox("Up The Ante");
		upTheAnteCheckbox.setHorizontalAlignment(SwingConstants.CENTER);
		upTheAnteCheckbox.setFont(TEXT_FONT);

		JButton createGameButton = new JButton("Create Game");
		createGameButton.setFont(TEXT_FONT);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.setFont(TEXT_FONT);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
								.addContainerGap()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(titleLabel, GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
										.addGroup(groupLayout.createSequentialGroup()
												.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
														.addComponent(numPlayersLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(gameNameLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(numRoundsLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(passwordLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(gamemodeLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
												.addGap(18)
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
														.addComponent(gameNameField, GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
														.addGroup(groupLayout.createSequentialGroup()
																.addComponent(numRoundsSpinner, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
																.addGap(18)
																.addComponent(onlineCheckbox, GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE))
														.addComponent(numPlayersSelector, 0, 259, Short.MAX_VALUE)
														.addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)))
										.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
												.addComponent(upTheAnteCheckbox, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
												.addGap(18)
												.addComponent(royalChaosCheckbox, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE))
										.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
												.addComponent(cancelButton)
												.addGap(18)
												.addComponent(createGameButton, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)))
								.addContainerGap())
		);
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
								.addContainerGap()
								.addComponent(titleLabel)
								.addGap(18)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(gameNameLabel)
										.addComponent(gameNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(numPlayersLabel)
										.addComponent(numPlayersSelector, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(numRoundsLabel)
										.addComponent(numRoundsSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(onlineCheckbox))
								.addGap(18)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(passwordLabel)
										.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addComponent(gamemodeLabel)
								.addGap(18)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(royalChaosCheckbox)
										.addComponent(upTheAnteCheckbox))
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(createGameButton)
										.addComponent(cancelButton))
								.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);
		pack();
		setLocationRelativeTo(null);

		createGameButton.addActionListener((event) -> {
			GameConfiguration configuration = new GameConfiguration();
			configuration.name = gameNameField.getText();
			configuration.numPlayers = numPlayersSelector.getSelectedIndex() + 2;
			configuration.numRounds = (int)numRoundsSpinner.getValue();
			if (royalChaosCheckbox.isSelected() && upTheAnteCheckbox.isSelected()) {
				configuration.mode = Tricata.Mode.ALL;
			} else if (royalChaosCheckbox.isSelected()) {
				configuration.mode = Tricata.Mode.ROYAL_CHAOS;
			} else if (upTheAnteCheckbox.isSelected()) {
				configuration.mode = Tricata.Mode.UP_THE_ANTE;
			} else {
				configuration.mode = Tricata.Mode.NORMAL;
			}
			configuration.online = onlineCheckbox.isSelected();
			if (configuration.online) {
				configuration.password = new String(passwordField.getPassword());
			}
			new GameWindow(configuration).setVisible(true);
			setVisible(false);
		});
		cancelButton.addActionListener((event) -> setVisible(false));
	}
}
