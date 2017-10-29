package tricata;

import javax.swing.*;

/**
 * Launcher class
 *
 * @author Kristian
 */
public final class Launch {

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
