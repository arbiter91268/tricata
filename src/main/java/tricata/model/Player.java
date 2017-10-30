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

	public boolean hasWon() {
		int first = hand[0].getSimilarityDegree(hand[1]);
		int second = hand[0].getSimilarityDegree(hand[2]);
		return first == second && first == 2;
	}

	public int getID() {
		return this.id;
	}

	public Card setCard(int index, Card newCard) {
		assert index >= 0 && index <= 2;
		Card temp = hand[index];
		hand[index] = newCard;
		return temp;
	}

}
