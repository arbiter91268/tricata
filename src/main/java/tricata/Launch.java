package tricata;

import tricata.draw.GameWindow;
import tricata.draw.MainMenu;

import javax.swing.*;

/**
 * Launcher class
 *
 * @author Kristian
 */
public final class Launch implements Runnable {

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		SwingUtilities.invokeLater(new Launch());
	}

	@Override
	public void run() {
		new MainMenu().setVisible(true);
	}
}
