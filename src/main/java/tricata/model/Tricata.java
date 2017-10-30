package tricata.model;

import java.util.*;

/**
 * Game model. Interacts with observer
 */
public class Tricata extends Observable {

	private List<Player> players = new ArrayList<>();
	private Stack<Card> deck = new Stack<Card>();
	private Stack<Card> bin = new Stack<Card>();

	private int currentTurn = 0;

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
		for (int i = 1; i <= numplayers; i++) {
			players.add(new Player("Player " + i));
		}
		deck.addAll(cards);
	}

	public void deal() {
		bin.push(deck.pop());
		for (int i = 0; i < 3; i++) {
			for (Player p : players) {
				p.setCard(i, deck.pop());
			}
		}
		setChanged();
		notifyObservers();
	}

	public Card peekNextCardInDeck() {
		return deck.peek();
	}

	public Card peekNextCardInBin() {
		if (bin.isEmpty()) {
			return null;
		}
		return bin.peek();
	}
}
