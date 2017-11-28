package tricata.model;

/**
 *
 */
public class Player {

	public String name;
	private Card[] hand = new Card[3];
	private final int id;
	private int score;

	private static int nextID = 0;

	public Player(String name) {
		this.name = name;
		id = nextID++;
	}

	public boolean hasWon() {
		int count = 0;
		boolean numChanged = false, typeChanged = false, colorChanged = false;
		Card.Type initialType = hand[0].getType();
		Card.Color initialColor = hand[0].getColor();
		int initialNumber = hand[0].getNumber();
		for (int i = 0; i < 3; i++) {
			if (!initialType.equals(hand[i].getType())) {
				typeChanged = true;
			}
			if (!initialColor.equals(hand[i].getColor())) {
				colorChanged = true;
			}
			if (initialNumber != hand[i].getNumber()) {
				numChanged = true;
			}
		}
		if (!numChanged) {
			count++;
		}
		if (!typeChanged) {
			count++;
		}
		if (!colorChanged) {
			count++;
		}
		return count >= 2;
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

	public Card peekCard(int index) {
		return hand[index];
	}

	public void incrementScore() {
		this.score++;
	}

	public int getScore() {
		return this.score;
	}

}
