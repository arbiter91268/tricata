package tricata.model;

import java.util.*;

/**
 * Game model. Interacts with observer
 */
public class Tricata extends Observable {

	private List<Player> players = new ArrayList<>();
	private Stack<Card> deck = new Stack<Card>();
	private Stack<Card> bin = new Stack<Card>();

	public Tricata(Observer observer, int numplayers) {
		addObserver(observer);
		List<Card> cards = new ArrayList<>();
		for (Card.Type type : Card.Type.values()) {
			for (Card.Color color : Card.Color.values()) {
				for (byte i = 1; i <= 3; i++) {
					cards.add(new Card(i, type, color));
				}
			}
		}
		assert cards.size() == 27;
		Collections.shuffle(cards);
		Collections.shuffle(cards); // double shuffle for more randomness
		deck.addAll(cards);
		setChanged();
		notifyObservers();
	}

	public void deal() {

	}
}
