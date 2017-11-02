package tricata.draw;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

/**
 * Storage class for loading images and assignment
 */
public enum ImageList {
	CARD_BACK("card_cover.png"),
	GREEN_DEMON("g_d.jpg"),
	GREEN_GENTLEMAN("g_g.jpg"),
	GREEN_LADY("g_l.jpg"),
	PURPLE_DEMON("p_d.jpg"),
	PURPLE_GENTLEMAN("p_g.jpg"),
	PURPLE_LADY("p_l.jpg"),
	RED_DEMON("r_d.jpg"),
	RED_GENTLEMAN("r_g.jpg"),
	RED_LADY("r_l.jpg");

	private final Image icon;

	ImageList(String name) {
		name = '/' + name;
		try {
			icon = ImageIO.read(ImageList.class.getResource(name));
		} catch (IOException exc) {
			throw new Error();
		}
	}

	public Image getImage() {
		return this.icon;
	}
}
