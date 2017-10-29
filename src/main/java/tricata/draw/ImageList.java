package tricata.draw;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

/**
 * Storage class for loading images and assignment
 */
public enum ImageList {
	CARD_BACK("card_cover"),
	GREEN_DEMON("g_d"),
	GREEN_GENTLEMAN("g_g"),
	GREEN_LADY("g_l"),
	PURPLE_DEMON("p_d"),
	PURPLE_GENTLEMAN("p_g"),
	PURPLE_LADY("p_l"),
	RED_DEMON("r_d"),
	RED_GENTLEMAN("r_g"),
	RED_LADY("r_l");

	private final Image icon;

	ImageList(String name) {
		name = '/' + name + ".png";
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
