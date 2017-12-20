package tricata.draw;

import tricata.network.TricataClient;

import javax.swing.*;

import static tricata.draw.CreateGameMenu.TITLE_FONT;
import static tricata.draw.CreateGameMenu.TEXT_FONT;

public class JoinGameMenu extends JFrame {

	public JoinGameMenu() {
		initComponents();
	}

	private void initComponents() {
		JLabel titleLabel = new JLabel("Join Game");
		titleLabel.setFont(TITLE_FONT);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel hostLabel = new JLabel("Host address:");
		hostLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		hostLabel.setFont(TEXT_FONT);

		JTextField hostField = new JTextField();
		hostField.setColumns(10);
		hostField.setFont(TEXT_FONT);

		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		passwordLabel.setFont(TEXT_FONT);

		JPasswordField passwordField = new JPasswordField();
		passwordField.setFont(TEXT_FONT);

		JButton joinButton = new JButton("Join");
		joinButton.setFont(TEXT_FONT);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.setFont(TEXT_FONT);

		JButton helpButton = new JButton("Help");
		helpButton.setFont(TEXT_FONT);

		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setBackground(UIManager.getColor("Button.background"));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
								.addContainerGap()
								.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addComponent(textArea, GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
										.addComponent(titleLabel, GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE)
										.addGroup(groupLayout.createSequentialGroup()
												.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
														.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
																.addComponent(passwordLabel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																.addComponent(hostLabel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE))
														.addComponent(helpButton))
												.addGap(18)
												.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
														.addGroup(GroupLayout.Alignment.TRAILING, groupLayout.createSequentialGroup()
																.addComponent(cancelButton, GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
																.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(joinButton, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE))
														.addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
														.addComponent(hostField, GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE))))
								.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
								.addContainerGap()
								.addComponent(titleLabel)
								.addGap(18)
								.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(hostLabel)
										.addComponent(hostField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(passwordLabel)
										.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(textArea, GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(joinButton)
										.addComponent(helpButton)
										.addComponent(cancelButton))
								.addContainerGap()));
		getContentPane().setLayout(groupLayout);
		pack();
		setLocationRelativeTo(null);
		joinButton.addActionListener(event -> {
			String[] components = hostField.getText().split(":");
			try {
				new TricataClient(components[0], Short.parseShort(components[1])).connect();
				textArea.setText("Connected successfully!");
			} catch (Exception ex) {
				textArea.setText("An error occured while trying to connect:\n" + ex.toString());
			}
		});
		cancelButton.addActionListener(event -> dispose());
	}
}
