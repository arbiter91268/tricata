package tricata.model;

/**
 *
 */
public class Player {

	public String name;
	private Card[] hand = new Card[3];
	private final int id;

	private static int nextID = 0;

	public Player(String name) {
		this.name = name;
		id = nextID++;
	}


}
